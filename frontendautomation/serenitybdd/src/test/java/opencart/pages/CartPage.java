package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final Target CART_BUTTON = Target.the("cart button")
            .located(By.id("cart-total"));

    public static final Target VIEW_CART_LINK = Target.the("view cart link")
            .locatedBy("//a[contains(@href,'checkout/cart')]//strong");

    public static final Target CART_TABLE_ROWS = Target.the("cart product rows")
            .located(By.cssSelector("#content form table tbody tr"));

    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .located(By.cssSelector("a.btn.btn-primary[href*='checkout/checkout']"));
}
