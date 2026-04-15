package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target SEARCH_INPUT = Target.the("campo de busqueda")
            .located(By.name("search"));

    public static final Target SEARCH_BUTTON = Target.the("boton de busqueda")
            .located(By.cssSelector("button.btn.btn-default.btn-lg"));

    public static final Target SUCCESS_ALERT = Target.the("alerta de producto agregado")
            .located(By.cssSelector(".alert-success"));
}
