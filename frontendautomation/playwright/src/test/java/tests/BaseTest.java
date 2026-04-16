package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SearchResultPage;

public abstract class BaseTest {

    protected static final String BASE_URL = System.getProperty("base.url", "http://opencart.abstracta.us/");
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected Page page;
    protected HomePage homePage;
    protected SearchResultPage searchResultPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    @BeforeEach
    void setUp() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setArgs(java.util.List.of("--ignore-certificate-errors"))
        );
        context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
        page = context.newPage();
        page.setViewportSize(1920, 1080);

        homePage = new HomePage(page);
        searchResultPage = new SearchResultPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
    }

    @AfterEach
    void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    protected void addProductToCart(String productName) {
        homePage.searchProduct(productName);
        searchResultPage.addToCart(productName);
        log.info("Product '{}' added to cart", productName);
    }

    protected void completeGuestCheckout(java.util.Map<String, String> billingData) {
        cartPage.goToCheckout();
        checkoutPage.selectGuestCheckout();
        checkoutPage.fillBillingDetails(billingData);
        checkoutPage.confirmShipping();
        checkoutPage.acceptTermsAndContinuePayment();
        checkoutPage.confirmOrder();
    }
}
