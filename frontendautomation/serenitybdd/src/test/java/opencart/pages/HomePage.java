package opencart.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://opencart.abstracta.us/")
public class HomePage extends PageObject {

    private static final By SEARCH_INPUT = By.name("search");
    private static final By SEARCH_BUTTON = By.cssSelector("button.btn.btn-default.btn-lg");

    public void buscarProducto(String nombreProducto) {
        $(SEARCH_INPUT).clear();
        $(SEARCH_INPUT).type(nombreProducto);
        $(SEARCH_BUTTON).click();
    }
}
