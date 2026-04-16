package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;

public class SearchResultPage {

    private static final String ADD_TO_CART_XPATH =
            "//div[@class='product-thumb']//h4/a[text()='{0}']/ancestor::div[@class='product-thumb']//button[contains(@onclick,'cart.add')]";

    public static Target addToCartButtonFor(String productName) {
        return Target.the("boton agregar al carrito de " + productName)
                .locatedBy(ADD_TO_CART_XPATH.replace("{0}", productName));
    }
}
