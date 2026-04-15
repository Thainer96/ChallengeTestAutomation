package opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class CartPage extends PageObject {

    private static final By CART_BUTTON = By.id("cart-total");
    private static final By VIEW_CART_LINK = By.cssSelector("a > strong:nth-child(1)");
    private static final By CART_TABLE_ROWS = By.cssSelector("#content form table tbody tr");
    private static final By CHECKOUT_BUTTON = By.cssSelector("a.btn.btn-primary[href*='checkout']");

    public void abrirCarrito() {
        $(CART_BUTTON).click();
    }

    public void irAlCarrito() {
        $(CART_BUTTON).click();
        withTimeoutOf(java.time.Duration.ofSeconds(5)).waitFor(VIEW_CART_LINK);
        $(VIEW_CART_LINK).click();
    }

    public int obtenerCantidadProductos() {
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(CART_TABLE_ROWS);
        List<WebElementFacade> filas = findAll(CART_TABLE_ROWS);
        return filas.size();
    }

    public void irAlCheckout() {
        $(CHECKOUT_BUTTON).click();
    }
}
