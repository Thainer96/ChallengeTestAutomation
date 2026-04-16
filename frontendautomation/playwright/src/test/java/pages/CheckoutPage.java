package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.Map;

public class CheckoutPage extends BasePage {

    private final Locator guestRadio;
    private final Locator continueAccountBtn;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator telephoneInput;
    private final Locator addressInput;
    private final Locator cityInput;
    private final Locator postCodeInput;
    private final Locator countrySelect;
    private final Locator zoneSelect;
    private final Locator continueBillingBtn;
    private final Locator continueShippingBtn;
    private final Locator termsCheckbox;
    private final Locator continuePaymentBtn;
    private final Locator confirmOrderBtn;
    private final Locator orderSuccessHeading;

    public CheckoutPage(Page page) {
        super(page);
        this.guestRadio = page.locator("input[value='guest']");
        this.continueAccountBtn = page.locator("#button-account");
        this.firstNameInput = page.locator("#input-payment-firstname");
        this.lastNameInput = page.locator("#input-payment-lastname");
        this.emailInput = page.locator("#input-payment-email");
        this.telephoneInput = page.locator("#input-payment-telephone");
        this.addressInput = page.locator("#input-payment-address-1");
        this.cityInput = page.locator("#input-payment-city");
        this.postCodeInput = page.locator("#input-payment-postcode");
        this.countrySelect = page.locator("#input-payment-country");
        this.zoneSelect = page.locator("#input-payment-zone");
        this.continueBillingBtn = page.locator("#button-guest");
        this.continueShippingBtn = page.locator("#button-shipping-method");
        this.termsCheckbox = page.locator("[name='agree']");
        this.continuePaymentBtn = page.locator("#button-payment-method");
        this.confirmOrderBtn = page.locator("#button-confirm");
        this.orderSuccessHeading = page.locator("#content h1");
    }

    public void selectGuestCheckout() {
        guestRadio.waitFor();
        guestRadio.click();
        continueAccountBtn.click();
    }

    public void fillBillingDetails(Map<String, String> data) {
        firstNameInput.waitFor();
        firstNameInput.fill(data.get("firstName"));
        lastNameInput.fill(data.get("lastName"));
        emailInput.fill(data.get("email"));
        telephoneInput.fill(data.get("telephone"));
        addressInput.fill(data.get("address1"));
        cityInput.fill(data.get("city"));
        postCodeInput.fill(data.get("postCode"));

        countrySelect.selectOption(new SelectOption().setLabel(data.get("country")));
        zoneSelect.waitFor();
        page.waitForTimeout(1500);
        zoneSelect.selectOption(new SelectOption().setLabel(data.get("zone")));

        continueBillingBtn.click();
    }

    public void confirmShipping() {
        continueShippingBtn.waitFor();
        continueShippingBtn.click();
    }

    public void acceptTermsAndContinuePayment() {
        termsCheckbox.waitFor();
        termsCheckbox.click();
        continuePaymentBtn.click();
    }

    public void confirmOrder() {
        confirmOrderBtn.waitFor();
        confirmOrderBtn.click();
    }

    public String getConfirmationMessage() {
        orderSuccessHeading.waitFor();
        return orderSuccessHeading.textContent();
    }
}
