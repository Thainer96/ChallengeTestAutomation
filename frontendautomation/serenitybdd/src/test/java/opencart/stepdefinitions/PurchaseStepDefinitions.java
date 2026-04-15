package opencart.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import opencart.questions.TheCartItemCount;
import opencart.questions.TheOrderConfirmationMessage;
import opencart.tasks.AddProductToCart;
import opencart.tasks.ConfirmTheOrder;
import opencart.tasks.FillBillingDetails;
import opencart.tasks.OpenTheStore;
import opencart.tasks.ProceedToGuestCheckout;
import opencart.tasks.ViewTheCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PurchaseStepDefinitions {

    @Dado("que el usuario se encuentra en la pagina principal de la tienda")
    public void userIsOnTheStoreFrontPage() {
        Actor user = OnStage.theActorCalled("User");
        user.attemptsTo(
                OpenTheStore.atUrl("http://opencart.abstracta.us/")
        );
    }

    @Cuando("el usuario agrega los siguientes productos al carrito")
    public void userAddsTheFollowingProductsToTheCart(DataTable dataTable) {
        List<String> products = dataTable.asList().subList(1, dataTable.asList().size());
        for (String product : products) {
            theActorInTheSpotlight().attemptsTo(
                    AddProductToCart.withName(product.trim())
            );
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Y("el usuario visualiza el carrito de compras")
    public void userViewsTheShoppingCart() {
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

    @Cuando("el usuario procede al checkout como invitado")
    public void userProceedsToGuestCheckout() {
        theActorInTheSpotlight().attemptsTo(
                ProceedToGuestCheckout.fromCart()
        );
    }

    @Y("el usuario completa los datos de facturacion")
    public void userFillsInBillingDetails(DataTable dataTable) {
        Map<String, String> details = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            details.put(row.get("campo"), row.get("valor"));
        }
        theActorInTheSpotlight().attemptsTo(
                FillBillingDetails.withData(details)
        );
    }

    @Y("el usuario confirma la orden de compra")
    public void userConfirmsTheOrder() {
        theActorInTheSpotlight().attemptsTo(
                ConfirmTheOrder.purchase()
        );
    }

    @Entonces("el usuario debe ver el mensaje de confirmacion {string}")
    public void userShouldSeeTheConfirmationMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheOrderConfirmationMessage.displayed(), containsString(expectedMessage))
        );
    }
}
