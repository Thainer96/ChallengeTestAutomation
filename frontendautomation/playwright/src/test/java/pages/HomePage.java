package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    private final Locator searchInput;
    private final Locator searchButton;

    public HomePage(Page page) {
        super(page);
        this.searchInput = page.locator("[name='search']");
        this.searchButton = page.locator("button.btn.btn-default.btn-lg");
    }

    public void searchProduct(String productName) {
        searchInput.fill(productName);
        searchButton.click();
    }
}
