package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final Target CART_BUTTON = Target.the("boton del carrito")
            .located(By.id("cart-total"));

    public static final Target VIEW_CART_LINK = Target.the("enlace ver carrito")
            .locatedBy("//a[contains(@href,'checkout/cart')]//strong");

    public static final Target CART_TABLE_ROWS = Target.the("filas de productos en el carrito")
            .located(By.cssSelector("#content form table tbody tr"));

    public static final Target CHECKOUT_BUTTON = Target.the("boton de checkout")
            .located(By.cssSelector("a.btn.btn-primary[href*='checkout/checkout']"));
}
