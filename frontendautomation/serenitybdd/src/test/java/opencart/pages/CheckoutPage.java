package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final Target GUEST_RADIO = Target.the("opcion checkout como invitado")
            .located(By.cssSelector("input[value='guest']"));

    public static final Target CONTINUE_ACCOUNT = Target.the("boton continuar cuenta")
            .located(By.id("button-account"));

    public static final Target FIRST_NAME = Target.the("campo nombre")
            .located(By.id("input-payment-firstname"));

    public static final Target LAST_NAME = Target.the("campo apellido")
            .located(By.id("input-payment-lastname"));

    public static final Target EMAIL = Target.the("campo correo electronico")
            .located(By.id("input-payment-email"));

    public static final Target TELEPHONE = Target.the("campo telefono")
            .located(By.id("input-payment-telephone"));

    public static final Target ADDRESS_1 = Target.the("campo direccion")
            .located(By.id("input-payment-address-1"));

    public static final Target CITY = Target.the("campo ciudad")
            .located(By.id("input-payment-city"));

    public static final Target POST_CODE = Target.the("campo codigo postal")
            .located(By.id("input-payment-postcode"));

    public static final Target COUNTRY = Target.the("selector de pais")
            .located(By.id("input-payment-country"));

    public static final Target ZONE = Target.the("selector de departamento")
            .located(By.id("input-payment-zone"));

    public static final Target CONTINUE_BILLING = Target.the("boton continuar facturacion")
            .located(By.id("button-guest"));

    public static final Target CONTINUE_SHIPPING = Target.the("boton continuar envio")
            .located(By.id("button-shipping-method"));

    public static final Target TERMS_CHECKBOX = Target.the("checkbox terminos y condiciones")
            .located(By.name("agree"));

    public static final Target CONTINUE_PAYMENT = Target.the("boton continuar pago")
            .located(By.id("button-payment-method"));

    public static final Target CONFIRM_ORDER = Target.the("boton confirmar orden")
            .located(By.id("button-confirm"));

    public static final Target ORDER_SUCCESS_HEADING = Target.the("titulo confirmacion de orden")
            .located(By.cssSelector("#content h1"));
}
