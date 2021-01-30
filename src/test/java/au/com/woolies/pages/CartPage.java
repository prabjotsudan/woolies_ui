package au.com.woolies.pages;

import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.HashMap;
import java.util.List;


public class CartPage extends BasePage{
    private static final Logger log = LogManager.getLogger(CartPage.class);
    HeaderPage headerPage = new HeaderPage();


    @FindBy(xpath = "//p[@class='product-name']/a")
    private WebElement productName;

    @FindBy(xpath = "//td[@class='cart_description']/small[2]/a")
    private WebElement productSizeAndColour;

    @FindBy(xpath = "//p[contains(@class, 'cart_navigation')]//span[contains(text(), 'Proceed to checkout')]")
    private WebElement proceedToCheckoutButton;

    @FindBys(@FindBy(xpath="//table[@id='cart_summary']/tbody/tr"))
    public List<WebElement> cartItems;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement commentArea;

    @FindBy(xpath = "//div[@class='checker']/span/input")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[@type='submit']/span[contains(text(), 'I confirm my order')]")
    private WebElement confirmOrderButton;

    @FindBy(xpath = "//div[@class='box']/p/strong[contains(text(),'complete')]")
    private WebElement orderConfirmationSuccessBankWire;

    @FindBy(xpath = "//p[contains(@class, 'alert-success')]")
    private WebElement orderConfirmationSuccessCheque;


    public HashMap<String, String> getProductDetailsFromCart(String name){
        boolean productFound = false;
        HashMap<String, String> productDetails = new HashMap<String, String>();

        for (WebElement cartItem : cartItems){

            // Fetching product name from each row of cart and matching it with the required product name
            WebElement productName = cartItem.findElement(By.xpath("./td[@class='cart_description']/p/a"));

            if (GenericMethods.getText(productName).equals(name)){
                // If a match is found, fetching all the required product properties and storing it in hashmap
                productFound = true;
                productDetails.put("productName", name);
                productDetails.put("productColour", this.getProductColour(cartItem));
                productDetails.put("productSize", this.getProductSize(cartItem));
                productDetails.put("productQuantity", this.getProductQuantity(cartItem));
                productDetails.put("productUnitPrice", this.getProductUnitPrice(cartItem));
                productDetails.put("productTotalPrice", this.getProductTotalPrice(cartItem));
            }
        }
        if (productFound){
            log.debug("Product details fetched {}", productDetails);
            return productDetails;
        }
        else{
            Assert.fail("Product '"+name+"' not found in cart");
            return null;
        }
    }

    private String getProductTotalPrice(WebElement cartItem){
        try{
            String totalPrice = GenericMethods.getText(cartItem.findElement(By.xpath("./td[@class = 'cart_total']/span")));

            return totalPrice.replace("$", "").trim();
        }
        catch (NoSuchElementException e){
            log.error("Exception raised in getProductTotalPrice : ", e);
            Assert.fail("Unable to find product total price in shopping cart");
            return null;
        }
    }

    private String getProductUnitPrice(WebElement cartItem){
        try{
            String unitPrice = GenericMethods.getText(cartItem.findElement(By.xpath("./td[@class = 'cart_unit']/span/span")));

            return unitPrice.replace("$", "").trim();
        }
        catch (NoSuchElementException e){
            log.error("Exception raised in getProductUnitPrice : ", e);
            Assert.fail("Unable to find product unit price in shopping cart");
            return null;
        }
    }

    private String getProductQuantity(WebElement cartItem){
        try{
            return GenericMethods.getAttribute(cartItem.findElement(By.xpath("./td[contains(@class,'cart_quantity')]/input[2]")), "value");
        }
        catch (NoSuchElementException e){
            log.error("Exception raised in getProductQuantity : ", e);
            Assert.fail("Unable to find product quantity in shopping cart");
            return null;
        }
    }

    private String getProductSize(WebElement cartItem){
        try{
            String sizeAndColour = GenericMethods.getText(cartItem.findElement(By.xpath("./td[@class='cart_description']/small[2]/a")));

            // Parsing "Color : Orange, Size : M" to get size value
            return sizeAndColour.split(":")[2].trim();
        }
        catch (NoSuchElementException e){
            log.error("Exception raised in getProductSize : ", e);
            Assert.fail("Unable to find product size in shopping cart");
            return null;
        }
    }

    private String getProductColour(WebElement cartItem){
        try{
            String sizeAndColour = GenericMethods.getText(cartItem.findElement(By.xpath("./td[@class='cart_description']/small[2]/a")));

            // Parsing "Color : Orange, Size : M" to get colour value
            return sizeAndColour.split(",")[0].trim().split(":")[1].trim();
        }
        catch (NoSuchElementException e){
            log.error("Exception raised in getProductColour : ", e);
            Assert.fail("Unable to find product colour in shopping cart");
            return null;
        }
    }

    public void clickProceedToCheckout(){
        GenericMethods.clickElement(proceedToCheckoutButton);
        log.debug("Clicked proceed to checkout button in cart page");
    }

    public void addComment(String comment){
        GenericMethods.sendKeys(commentArea, comment);
        log.debug("Entered comment in textarea during checkout");
    }

    public void selectTermsCheckbox(){
        GenericMethods.selectCheckbox(termsCheckbox);
        log.debug("Accept terms checkbox selected during checkout");
    }

    public void selectPaymentMethod(String paymentMethod){
        try {
            WebElement element = DriverManager.get().findElement(By.xpath("//a[@class='"+paymentMethod+"']"));
            GenericMethods.clickElement(element);

            if ((paymentMethod.equals("bankwire") && headerPage.getCurrentPageIndicator().equals("Bank-wire payment.")) ||
                    (paymentMethod.equals("cheque") && headerPage.getCurrentPageIndicator().equals("Check payment"))){

                log.info("Selected {} payment method", paymentMethod);
            }
            else{
                Assert.fail("Expected screen not loaded after selecting '"+paymentMethod+"' payment method");
            }
        } catch (NoSuchElementException e){
            log.error("Exception raised in selectPaymentMethod : ", e);
            Assert.fail("Unable to find '"+paymentMethod+"' payment method in shopping cart");
        }

    }

    public void confirmOrder(String paymentMethod){
        GenericMethods.clickElement(confirmOrderButton);
        log.debug("Clicked confirm order button");
        if (paymentMethod.equals("bankwire")) {
            GenericMethods.assertElementIsDisplayed(orderConfirmationSuccessBankWire);
        }
        else if(paymentMethod.equals("cheque")) {
            GenericMethods.assertElementIsDisplayed(orderConfirmationSuccessCheque);
        }
        else{
            Assert.fail("Invalid payment method '"+paymentMethod+"'");
        }
        log.info("Order placed successfully");

    }


}
