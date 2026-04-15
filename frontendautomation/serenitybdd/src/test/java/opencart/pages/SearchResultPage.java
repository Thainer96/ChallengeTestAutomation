package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;

public class SearchResultPage {

    private static final String ADD_TO_CART_XPATH =
            "//div[@class='product-thumb']//a[contains(text(),'{0}')]/../../following-sibling::div//button[1]";

    public static Target addToCartButtonFor(String productName) {
        return Target.the("add to cart button for " + productName)
                .locatedBy(ADD_TO_CART_XPATH.replace("{0}", productName));
    }
}
