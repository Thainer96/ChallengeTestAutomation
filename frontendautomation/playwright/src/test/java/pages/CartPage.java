package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends BasePage {

    private final Locator cartButton;
    private final Locator viewCartLink;
    private final Locator cartRows;
    private final Locator checkoutButton;

    public CartPage(Page page) {
        super(page);
        this.cartButton = page.locator("#cart-total");
        this.viewCartLink = page.locator("//a[contains(@href,'checkout/cart')]//strong");
        this.cartRows = page.locator("#content form table tbody tr");
        this.checkoutButton = page.locator("a.btn.btn-primary[href*='checkout/checkout']");
    }

    public void openCart() {
        cartButton.click();
        viewCartLink.waitFor();
        viewCartLink.click();
    }

    public int getProductCount() {
        cartRows.first().waitFor();
        return cartRows.count();
    }

    public void goToCheckout() {
        checkoutButton.click();
    }
}
