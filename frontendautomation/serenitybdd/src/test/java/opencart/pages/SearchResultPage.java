package opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class SearchResultPage extends PageObject {

    private static final String ADD_TO_CART_XPATH = "//div[@class='product-thumb']//a[contains(text(),'%s')]/../../following-sibling::div//button[1]";

    public void agregarProductoAlCarrito(String nombreProducto) {
        By addToCartButton = By.xpath(String.format(ADD_TO_CART_XPATH, nombreProducto));
        withTimeoutOf(java.time.Duration.ofSeconds(10)).waitFor(addToCartButton);
        $(addToCartButton).click();
    }
}
