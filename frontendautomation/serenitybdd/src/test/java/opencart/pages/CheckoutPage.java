package opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class CheckoutPage extends PageObject {

    private static final By GUEST_RADIO = By.cssSelector("input[value='guest']");
    private static final By CONTINUE_ACCOUNT = By.id("button-account");

    private static final By FIRST_NAME = By.id("input-payment-firstname");
    private static final By LAST_NAME = By.id("input-payment-lastname");
    private static final By EMAIL = By.id("input-payment-email");
    private static final By TELEPHONE = By.id("input-payment-telephone");
    private static final By ADDRESS_1 = By.id("input-payment-address-1");
    private static final By CITY = By.id("input-payment-city");
    private static final By POST_CODE = By.id("input-payment-postcode");
    private static final By COUNTRY = By.id("input-payment-country");
    private static final By ZONE = By.id("input-payment-zone");
    private static final By CONTINUE_BILLING = By.id("button-guest");

    private static final By CONTINUE_SHIPPING = By.id("button-shipping-method");
    private static final By TERMS_CHECKBOX = By.name("agree");
    private static final By CONTINUE_PAYMENT = By.id("button-payment-method");
    private static final By CONFIRM_ORDER = By.id("button-confirm");

    private static final By ORDER_SUCCESS_HEADING = By.cssSelector("#content h1");

    public void seleccionarGuestCheckout() {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(GUEST_RADIO);
        $(GUEST_RADIO).click();
        $(CONTINUE_ACCOUNT).click();
    }

    public void completarDatosFacturacion(Map<String, String> datos) {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(FIRST_NAME);

        $(FIRST_NAME).clear();
        $(FIRST_NAME).type(datos.get("firstName"));

        $(LAST_NAME).clear();
        $(LAST_NAME).type(datos.get("lastName"));

        $(EMAIL).clear();
        $(EMAIL).type(datos.get("email"));

        $(TELEPHONE).clear();
        $(TELEPHONE).type(datos.get("telephone"));

        $(ADDRESS_1).clear();
        $(ADDRESS_1).type(datos.get("address1"));

        $(CITY).clear();
        $(CITY).type(datos.get("city"));

        $(POST_CODE).clear();
        $(POST_CODE).type(datos.get("postCode"));

        seleccionarPais(datos.get("country"));
        esperarYSeleccionarZona(datos.get("zone"));

        $(CONTINUE_BILLING).click();
    }

    private void seleccionarPais(String pais) {
        new Select(getDriver().findElement(COUNTRY)).selectByVisibleText(pais);
    }

    private void esperarYSeleccionarZona(String zona) {
        withTimeoutOf(java.time.Duration.ofSeconds(5)).waitFor(ZONE);
        waitABit(1000);
        new Select(getDriver().findElement(ZONE)).selectByVisibleText(zona);
    }

    public void continuarMetodoEnvio() {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(CONTINUE_SHIPPING);
        $(CONTINUE_SHIPPING).click();
    }

    public void aceptarTerminosYContinuarPago() {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(TERMS_CHECKBOX);
        $(TERMS_CHECKBOX).click();
        $(CONTINUE_PAYMENT).click();
    }

    public void confirmarOrden() {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(CONFIRM_ORDER);
        $(CONFIRM_ORDER).click();
    }

    public String obtenerMensajeConfirmacion() {
        withTimeoutOf(java.time.Duration.ofSeconds(15)).waitFor(ORDER_SUCCESS_HEADING);
        return $(ORDER_SUCCESS_HEADING).getText();
    }
}
