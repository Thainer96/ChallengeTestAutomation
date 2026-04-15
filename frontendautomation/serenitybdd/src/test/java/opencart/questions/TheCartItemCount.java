package opencart.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;
import opencart.pages.CartPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TheCartItemCount implements Question<Integer> {

    public static TheCartItemCount value() {
        return new TheCartItemCount();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(CartPage.CART_TABLE_ROWS, isVisible()).forNoMoreThan(10).seconds()
        );
        return CartPage.CART_TABLE_ROWS.resolveAllFor(actor).size();
    }
}
