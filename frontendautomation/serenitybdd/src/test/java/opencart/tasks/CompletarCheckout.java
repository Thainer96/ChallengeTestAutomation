package opencart.tasks;

import net.serenitybdd.annotations.Step;
import opencart.pages.CheckoutPage;

import java.util.Map;

public class CompletarCheckout {

    private final CheckoutPage checkoutPage;

    public CompletarCheckout(CheckoutPage checkoutPage) {
        this.checkoutPage = checkoutPage;
    }

    @Step("Seleccionar checkout como invitado")
    public void seleccionarGuestCheckout() {
        checkoutPage.seleccionarGuestCheckout();
    }

    @Step("Completar datos de facturación")
    public void completarDatosFacturacion(Map<String, String> datos) {
        checkoutPage.completarDatosFacturacion(datos);
    }

    @Step("Confirmar la orden de compra")
    public void confirmarOrden() {
        checkoutPage.continuarMetodoEnvio();
        checkoutPage.aceptarTerminosYContinuarPago();
        checkoutPage.confirmarOrden();
    }

    @Step("Obtener mensaje de confirmación")
    public String obtenerMensajeConfirmacion() {
        return checkoutPage.obtenerMensajeConfirmacion();
    }
}
