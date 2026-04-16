package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultPage extends BasePage {

    private final Locator successAlert;

    public SearchResultPage(Page page) {
        super(page);
        this.successAlert = page.locator(".alert-success");
    }

    public void addToCart(String productName) {
        Locator addToCartButton = page.locator(
                "//div[@class='product-thumb']//h4/a[text()='" + productName + "']" +
                "/ancestor::div[@class='product-thumb']//button[contains(@onclick,'cart.add')]"
        );
        addToCartButton.click();
        successAlert.waitFor();
        successAlert.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
    }
}
