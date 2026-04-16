package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PurchaseTest extends BaseTest {

    @Test
    @DisplayName("Compra exitosa de MacBook e iPhone como invitado")
    void guestCheckoutWithTwoProducts() {
        homePage.navigate(BASE_URL);

        addProductToCart("MacBook");
        addProductToCart("iPhone");

        cartPage.openCart();
        assertEquals(2, cartPage.getProductCount());

        completeGuestCheckout(Map.of(
                "firstName", "Juan",
                "lastName", "Perez",
                "email", "juan@test.com",
                "telephone", "3001234567",
                "address1", "Calle 123",
                "city", "Bogota",
                "postCode", "110111",
                "country", "Colombia",
                "zone", "Bogota D.C."
        ));

        assertTrue(checkoutPage.getConfirmationMessage().contains("Your order has been placed!"));
    }

    @ParameterizedTest
    @DisplayName("Compra exitosa con diferentes datos de facturacion")
    @CsvSource({
            "Carlos, Lopez, carlos@test.com, 3109876543, Carrera 45, Medellin, 050001, Colombia, Antioquia",
            "Maria, Garcia, maria@test.com, 3201234567, Avenida 68, Cali, 760001, Colombia, Valle del Cauca"
    })
    void guestCheckoutWithDifferentBillingData(String firstName, String lastName, String email,
                                                String telephone, String address, String city,
                                                String postCode, String country, String zone) {
        homePage.navigate(BASE_URL);

        addProductToCart("MacBook");
        addProductToCart("iPhone");

        cartPage.openCart();
        assertEquals(2, cartPage.getProductCount());

        completeGuestCheckout(Map.of(
                "firstName", firstName,
                "lastName", lastName,
                "email", email,
                "telephone", telephone,
                "address1", address,
                "city", city,
                "postCode", postCode,
                "country", country,
                "zone", zone
        ));

        assertTrue(checkoutPage.getConfirmationMessage().contains("Your order has been placed!"));
    }
}
