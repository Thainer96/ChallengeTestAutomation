package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.HomePage;
import opencart.pages.SearchResultPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    private static final Logger log = LoggerFactory.getLogger(AddProductToCart.class);

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart withName(String productName) {
        return instrumented(AddProductToCart.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        log.info("Searching for product: {}", productName);
        actor.attemptsTo(
                Clear.field(HomePage.SEARCH_INPUT),
                Enter.theValue(productName).into(HomePage.SEARCH_INPUT),
                Click.on(HomePage.SEARCH_BUTTON)
        );

        log.debug("Waiting for add-to-cart button for: {}", productName);
        actor.attemptsTo(
                WaitUntil.the(SearchResultPage.addToCartButtonFor(productName), isVisible())
                        .forNoMoreThan(8).seconds(),
                Click.on(SearchResultPage.addToCartButtonFor(productName))
        );

        log.debug("Waiting for success alert to appear and dismiss");
        actor.attemptsTo(
                WaitUntil.the(HomePage.SUCCESS_ALERT, isVisible()).forNoMoreThan(5).seconds()
        );
        log.info("Product '{}' added to cart successfully", productName);
    }
}
