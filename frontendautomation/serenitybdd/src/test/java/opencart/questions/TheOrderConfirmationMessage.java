package opencart.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CheckoutPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheOrderConfirmationMessage implements Question<String> {

    public static TheOrderConfirmationMessage displayed() {
        return new TheOrderConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(CheckoutPage.ORDER_SUCCESS_HEADING, isVisible()).forNoMoreThan(15).seconds()
        );
        return Text.of(CheckoutPage.ORDER_SUCCESS_HEADING).answeredBy(actor);
    }
}
