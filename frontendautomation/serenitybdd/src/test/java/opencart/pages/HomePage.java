package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target SEARCH_INPUT = Target.the("search input field")
            .located(By.name("search"));

    public static final Target SEARCH_BUTTON = Target.the("search button")
            .located(By.cssSelector("button.btn.btn-default.btn-lg"));
}
