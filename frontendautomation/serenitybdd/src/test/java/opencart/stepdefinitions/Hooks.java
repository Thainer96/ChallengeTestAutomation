package opencart.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Before
    public void setUpStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before(order = 1)
    public void maximizeBrowser() {
        try {
            Actor actor = OnStage.theActorInTheSpotlight();
            BrowseTheWeb.as(actor).getDriver().manage().window().maximize();
        } catch (Exception e) {
            log.debug("Browser not yet available for maximize");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            Actor actor = OnStage.theActorInTheSpotlight();
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();

            if (scenario.isFailed() && driver != null) {
                log.error("Scenario failed: {}", scenario.getName());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot-on-failure");
            }
        } catch (Exception e) {
            log.warn("Could not capture screenshot: {}", e.getMessage());
        }
    }
}
