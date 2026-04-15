package opencart.pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final Target GUEST_RADIO = Target.the("guest checkout option")
            .located(By.cssSelector("input[value='guest']"));

    public static final Target CONTINUE_ACCOUNT = Target.the("continue account button")
            .located(By.id("button-account"));

    public static final Target FIRST_NAME = Target.the("first name field")
            .located(By.id("input-payment-firstname"));

    public static final Target LAST_NAME = Target.the("last name field")
            .located(By.id("input-payment-lastname"));

    public static final Target EMAIL = Target.the("email field")
            .located(By.id("input-payment-email"));

    public static final Target TELEPHONE = Target.the("telephone field")
            .located(By.id("input-payment-telephone"));

    public static final Target ADDRESS_1 = Target.the("address field")
            .located(By.id("input-payment-address-1"));

    public static final Target CITY = Target.the("city field")
            .located(By.id("input-payment-city"));

    public static final Target POST_CODE = Target.the("post code field")
            .located(By.id("input-payment-postcode"));

    public static final Target COUNTRY = Target.the("country selector")
            .located(By.id("input-payment-country"));

    public static final Target ZONE = Target.the("zone selector")
            .located(By.id("input-payment-zone"));

    public static final Target CONTINUE_BILLING = Target.the("continue billing button")
            .located(By.id("button-guest"));

    public static final Target CONTINUE_SHIPPING = Target.the("continue shipping button")
            .located(By.id("button-shipping-method"));

    public static final Target TERMS_CHECKBOX = Target.the("terms and conditions checkbox")
            .located(By.name("agree"));

    public static final Target CONTINUE_PAYMENT = Target.the("continue payment button")
            .located(By.id("button-payment-method"));

    public static final Target CONFIRM_ORDER = Target.the("confirm order button")
            .located(By.id("button-confirm"));

    public static final Target ORDER_SUCCESS_HEADING = Target.the("order confirmation heading")
            .located(By.cssSelector("#content h1"));
}
