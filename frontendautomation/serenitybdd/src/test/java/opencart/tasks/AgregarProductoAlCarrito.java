package opencart.tasks;

import net.serenitybdd.annotations.Step;
import opencart.pages.HomePage;
import opencart.pages.SearchResultPage;

public class AgregarProductoAlCarrito {

    private final HomePage homePage;
    private final SearchResultPage searchResultPage;

    public AgregarProductoAlCarrito(HomePage homePage, SearchResultPage searchResultPage) {
        this.homePage = homePage;
        this.searchResultPage = searchResultPage;
    }

    @Step("Buscar y agregar el producto '{0}' al carrito")
    public void ejecutar(String nombreProducto) {
        homePage.buscarProducto(nombreProducto);
        searchResultPage.agregarProductoAlCarrito(nombreProducto);
    }
}
