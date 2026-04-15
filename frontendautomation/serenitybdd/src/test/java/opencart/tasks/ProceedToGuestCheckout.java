package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CartPage;
import opencart.pages.CheckoutPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ProceedToGuestCheckout implements Task {

    public static ProceedToGuestCheckout fromCart() {
        return instrumented(ProceedToGuestCheckout.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPage.CHECKOUT_BUTTON),
                WaitUntil.the(CheckoutPage.GUEST_RADIO, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.GUEST_RADIO),
                Click.on(CheckoutPage.CONTINUE_ACCOUNT)
        );
    }
}
