package opencart.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenTheStore implements Task {

    private final String url;

    public OpenTheStore(String url) {
        this.url = url;
    }

    public static OpenTheStore atUrl(String url) {
        return instrumented(OpenTheStore.class, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }
}
