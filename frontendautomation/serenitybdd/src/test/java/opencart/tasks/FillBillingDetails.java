package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CheckoutPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class FillBillingDetails implements Task {

    private static final Logger log = LoggerFactory.getLogger(FillBillingDetails.class);

    private final Map<String, String> details;

    public FillBillingDetails(Map<String, String> details) {
        this.details = details;
    }

    public static FillBillingDetails withData(Map<String, String> details) {
        return instrumented(FillBillingDetails.class, details);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        log.info("Filling billing details for: {} {}", details.get("firstName"), details.get("lastName"));

        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Clear.field(CheckoutPage.FIRST_NAME),
                Enter.theValue(details.get("firstName")).into(CheckoutPage.FIRST_NAME),
                Clear.field(CheckoutPage.LAST_NAME),
                Enter.theValue(details.get("lastName")).into(CheckoutPage.LAST_NAME),
                Clear.field(CheckoutPage.EMAIL),
                Enter.theValue(details.get("email")).into(CheckoutPage.EMAIL),
                Clear.field(CheckoutPage.TELEPHONE),
                Enter.theValue(details.get("telephone")).into(CheckoutPage.TELEPHONE),
                Clear.field(CheckoutPage.ADDRESS_1),
                Enter.theValue(details.get("address1")).into(CheckoutPage.ADDRESS_1),
                Clear.field(CheckoutPage.CITY),
                Enter.theValue(details.get("city")).into(CheckoutPage.CITY),
                Clear.field(CheckoutPage.POST_CODE),
                Enter.theValue(details.get("postCode")).into(CheckoutPage.POST_CODE)
        );

        log.debug("Selecting country: {}", details.get("country"));
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(details.get("country")).from(CheckoutPage.COUNTRY)
        );

        log.debug("Waiting for zone dropdown to load after country selection");
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.ZONE, isEnabled()).forNoMoreThan(5).seconds()
        );

        log.debug("Selecting zone: {}", details.get("zone"));
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(details.get("zone")).from(CheckoutPage.ZONE),
                Click.on(CheckoutPage.CONTINUE_BILLING)
        );
        log.info("Billing details submitted");
    }
}
