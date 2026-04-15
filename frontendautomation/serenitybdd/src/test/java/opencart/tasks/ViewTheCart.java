package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CartPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewTheCart implements Task {

    public static ViewTheCart items() {
        return instrumented(ViewTheCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPage.CART_BUTTON),
                WaitUntil.the(CartPage.VIEW_CART_LINK, isVisible()).forNoMoreThan(5).seconds(),
                Click.on(CartPage.VIEW_CART_LINK)
        );
    }
}
