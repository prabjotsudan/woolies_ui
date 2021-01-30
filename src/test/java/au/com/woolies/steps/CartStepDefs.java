package au.com.woolies.steps;

import au.com.woolies.businessLayers.*;
import au.com.woolies.utils.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CartStepDefs {

    private static final Logger log = LogManager.getLogger(CartStepDefs.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    HeaderLib headerLib = new HeaderLib();
    CartLib cartLib = new CartLib();

    @Then("I verify that added product is there in cart")
    public void iVerifyThatAddedProductIsThereInCart(DataTable data) {
        List<Map<String, String>> dataToUse = data.asMaps(String.class, String.class);
        String productName = dataToUse.get(0).get("productName");
        String productColour = dataToUse.get(0).get("colour");
        String productSize = dataToUse.get(0).get("size");
        String productQuantity = dataToUse.get(0).get("quantity");
        String productPrice = dataToUse.get(0).get("price");
        headerLib.navigateToMyCart();
        cartLib.validateProductDetails(productName, productQuantity, productSize, productColour, productPrice);
    }

    @And("I navigate to cart and proceed to checkout")
    public void iNavigateToCartAndProceedToCheckout() {
        headerLib.navigateToMyCart();
        cartLib.proceedToCheckout("Addresses");
    }

    @And("I add comment on the Address screen and proceed to checkout")
    public void iAddCommentOnTheAddressScreenAndProceedToCheckout(DataTable data) {
        List<Map<String, String>> dataToUse = data.asMaps(String.class, String.class);
        cartLib.addComment(dataToUse.get(0).get("commentText"));
        cartLib.proceedToCheckout("Shipping");
    }

    @And("I agree to Terms of Service and proceed to checkout")
    public void iAgreeToTermsOfServiceAndProceedToCheckout() {
        cartLib.acceptTerms();
        cartLib.proceedToCheckout("Your payment method");
    }

    @Then("I select {string} payment method and confirm my order")
    public void iSelectPaymentMethodAndConfirmMyOrder(String paymentMethod) {
        cartLib.selectPaymentMethodAndConfirm(paymentMethod);
    }
}
