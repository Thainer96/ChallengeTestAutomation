package opencart.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;
import opencart.questions.TheCartItemCount;
import opencart.questions.TheOrderConfirmationMessage;
import opencart.tasks.AddProductToCart;
import opencart.tasks.ConfirmTheOrder;
import opencart.tasks.FillBillingDetails;
import opencart.tasks.OpenTheStore;
import opencart.tasks.ProceedToGuestCheckout;
import opencart.tasks.ViewTheCart;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PurchaseStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Dado("que {string} se encuentra en la pagina principal de la tienda")
    public void actorIsOnTheStoreFrontPage(String actorName) {
        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("base.url");
        OnStage.theActorCalled(actorName).attemptsTo(
                OpenTheStore.atUrl(baseUrl)
        );
    }

    @Cuando("agrega los siguientes productos al carrito")
    public void addsTheFollowingProductsToTheCart(DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            theActorInTheSpotlight().attemptsTo(
                    AddProductToCart.withName(product.trim())
            );
        }
    }

    @Y("visualiza el carrito de compras")
    public void viewsTheShoppingCart() {
        theActorInTheSpotlight().attemptsTo(
                ViewTheCart.items()
        );
    }

    @Entonces("el carrito debe contener {int} productos")
    public void theCartShouldContainProducts(int expectedCount) {
        theActorInTheSpotlight().should(
                seeThat(TheCartItemCount.value(), equalTo(expectedCount))
        );
    }

    @Cuando("procede al checkout como invitado")
    public void proceedsToGuestCheckout() {
        theActorInTheSpotlight().attemptsTo(
                ProceedToGuestCheckout.fromCart()
        );
    }

    @Y("completa los datos de facturacion")
    public void fillsInBillingDetails(DataTable dataTable) {
        Map<String, String> details = dataTable.asMap(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(
                FillBillingDetails.withData(details)
        );
    }

    @Y("confirma la orden de compra")
    public void confirmsTheOrder() {
        theActorInTheSpotlight().attemptsTo(
                ConfirmTheOrder.purchase()
        );
    }

    @Entonces("debe ver el mensaje de confirmacion {string}")
    public void shouldSeeTheConfirmationMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheOrderConfirmationMessage.displayed(), containsString(expectedMessage))
        );
    }
}
