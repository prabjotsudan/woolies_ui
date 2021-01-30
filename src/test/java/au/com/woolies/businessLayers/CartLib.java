package au.com.woolies.businessLayers;


import au.com.woolies.pages.CartPage;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Properties;

public class CartLib {

    private static final Logger log = LogManager.getLogger(CartLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    CartPage cartPage = new CartPage();
    HeaderLib headerLib = new HeaderLib();

    /**
     * Function to validate product details in shopping cart
     * @param name Name of the product
     * @param quantity Expected quantity of the product
     * @param size Expected size of the product
     * @param colour Expected colour of the product
     * @param price Expected unit price of the product
     */
    public void validateProductDetails(String name, String quantity, String size, String colour, String price){
        log.info("Validating product details in shopping cart");
        boolean failure = false;
        float totalPrice = Float.parseFloat(price) * Float.parseFloat(quantity);

        // Fetching product details from shopping cart page and comparing them with expected values
        HashMap<String, String> productDetails = cartPage.getProductDetailsFromCart(name);

        if (productDetails.get("productName").equals(name)){
            log.info("Product {} found in cart", name);
        }
        else{
            failure = true;
            log.error("Failed to verify product name in shopping cart, expected value '"+name+"', actual value '"+productDetails.get("productName")+"'");
        }
        if (productDetails.get("productQuantity").equals(quantity)){
            log.info("Product  quantity {} found in cart", quantity);
        }
        else{
            failure = true;
            log.error("Failed to verify product quantity in shopping cart, expected value '"+quantity+"', actual value '"+productDetails.get("productQuantity")+"'");
        }
        if (productDetails.get("productSize").equals(size)){
            log.info("Product size {} found in cart", size);
        }
        else{
            failure = true;
            log.error("Failed to verify product size in shopping cart, expected value '"+size+"', actual value '"+productDetails.get("productSize")+"'");
        }
        if (productDetails.get("productColour").equals(colour)){
            log.info("Product colour {} found in cart", colour);
        }
        else{
            failure = true;
            log.error("Failed to verify product colour in shopping cart, expected value '"+colour+"', actual value '"+productDetails.get("productColour")+"'");
        }
        if (productDetails.get("productUnitPrice").equals(price)){
            log.info("Product unit price {} found in cart", price);
        }
        else{
            failure = true;
            log.error("Failed to verify product unit price in shopping cart, expected value '"+price+"', actual value '"+productDetails.get("productUnitPrice")+"'");
        }
        if (Float.parseFloat(productDetails.get("productTotalPrice")) == totalPrice){
            log.info("Product total price {} found in cart", totalPrice);
        }
        else{
            failure = true;
            log.error("Failed to verify product total price in shopping cart, expected value '"+totalPrice+"', actual value '"+productDetails.get("productTotalPrice")+"'");
        }

        if (failure){
            Assert.fail("Failed to verify product details from shopping cart page");
        }
        else{
            log.info("Verified all the product details from shopping cart for '{}' product", name);
        }
    }

    /**
     * Function to proceed to checkout. After cliking on proceed button, it verifies if user is landed on
     * expected page
     * @param nextPageIndicatorString Expected navigation string for the landing page
     */
    public void proceedToCheckout(String nextPageIndicatorString){
        log.info("Proceeding to checkout");
        cartPage.clickProceedToCheckout();
        if (headerLib.getCurrentPageIndicator().equals(nextPageIndicatorString)){
            log.info("Successfully navigated to '{}' section", nextPageIndicatorString);
        }
        else{
            Assert.fail("Failed to navigate to '"+nextPageIndicatorString+"' section during checkout");
        }
    }

    /**
     * Function to add comment during checkout procedure
     * @param comment Comment to be added
     */
    public void addComment(String comment){
        cartPage.addComment(comment);
    }

    /**
     * Function to accept Terms and Conditions during checkout
     */
    public void acceptTerms(){
        cartPage.selectTermsCheckbox();
    }

    /**
     * Function to select payment method during checkout and confirm order
     * @param paymentMethod Payment method to be selected, allowed values are
     *                      "bankwire" and "cheque"
     */
    public void selectPaymentMethodAndConfirm(String paymentMethod){
        cartPage.selectPaymentMethod(paymentMethod);
        cartPage.confirmOrder(paymentMethod);
    }






}
