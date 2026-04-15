package opencart.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.annotations.Steps;
import opencart.pages.CartPage;
import opencart.pages.HomePage;
import opencart.tasks.AgregarProductoAlCarrito;
import opencart.tasks.CompletarCheckout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompraStepDefinitions {

    @Steps
    private AgregarProductoAlCarrito agregarProducto;

    @Steps
    private CompletarCheckout completarCheckout;

    private final HomePage homePage = new HomePage();
    private final CartPage cartPage = new CartPage();

    @Dado("que el usuario se encuentra en la pagina principal de la tienda")
    public void queElUsuarioSeEncuentraEnLaPaginaPrincipalDeLaTienda() {
        homePage.open();
    }

    @Cuando("el usuario agrega los siguientes productos al carrito")
    public void elUsuarioAgregaLosSiguientesProductosAlCarrito(DataTable dataTable) {
        List<String> productos = dataTable.asList().subList(1, dataTable.asList().size());
        for (String producto : productos) {
            agregarProducto.ejecutar(producto.trim());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Y("el usuario visualiza el carrito de compras")
    public void elUsuarioVisualizaElCarritoDeCompras() {
        cartPage.irAlCarrito();
    }

    @Entonces("el carrito debe contener {int} productos")
    public void elCarritoDebeContenerProductos(int cantidadEsperada) {
        int cantidadActual = cartPage.obtenerCantidadProductos();
        assertEquals(cantidadEsperada, cantidadActual,
                "La cantidad de productos en el carrito no coincide");
    }

    @Cuando("el usuario procede al checkout como invitado")
    public void elUsuarioProcedeAlCheckoutComoInvitado() {
        cartPage.irAlCheckout();
        completarCheckout.seleccionarGuestCheckout();
    }

    @Y("el usuario completa los datos de facturacion")
    public void elUsuarioCompletaLosDatosDeFacturacion(DataTable dataTable) {
        Map<String, String> datos = new HashMap<>();
        List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> fila : filas) {
            datos.put(fila.get("campo"), fila.get("valor"));
        }
        completarCheckout.completarDatosFacturacion(datos);
    }

    @Y("el usuario confirma la orden de compra")
    public void elUsuarioConfirmaLaOrdenDeCompra() {
        completarCheckout.confirmarOrden();
    }

    @Entonces("el usuario debe ver el mensaje de confirmacion {string}")
    public void elUsuarioDebeVerElMensajeDeConfirmacion(String mensajeEsperado) {
        String mensajeActual = completarCheckout.obtenerMensajeConfirmacion();
        assertTrue(mensajeActual.contains(mensajeEsperado),
                "Se esperaba: " + mensajeEsperado + " pero se obtuvo: " + mensajeActual);
    }
}
