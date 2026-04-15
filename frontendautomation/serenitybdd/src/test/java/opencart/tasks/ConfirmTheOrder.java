package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CheckoutPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmTheOrder implements Task {

    private static final Logger log = LoggerFactory.getLogger(ConfirmTheOrder.class);

    public static ConfirmTheOrder purchase() {
        return instrumented(ConfirmTheOrder.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        log.info("Confirming shipping method");
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.CONTINUE_SHIPPING, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.CONTINUE_SHIPPING)
        );

        log.info("Accepting terms and selecting payment method");
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.TERMS_CHECKBOX, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.TERMS_CHECKBOX),
                Click.on(CheckoutPage.CONTINUE_PAYMENT)
        );

        log.info("Placing the order");
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.CONFIRM_ORDER, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.CONFIRM_ORDER)
        );
    }
}
