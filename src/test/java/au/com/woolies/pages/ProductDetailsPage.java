package au.com.woolies.pages;

import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductDetailsPage extends BasePage{
    private static final Logger log = LogManager.getLogger(ProductDetailsPage.class);


    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private WebElement quantityInput;

    @FindBy(xpath = "//select[@id='group_1']")
    private WebElement sizeSelectBox;

    @FindBy(xpath = "//p[@id='add_to_cart']/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//p[@id='quantity_wanted_p']/label")
    private WebElement quantityLabel;

    @FindBy(xpath = "//div[@id='layer_cart']/div/div[1]/h2")
    private WebElement productAddedSuccessMessage;

    @FindBy(xpath = "//span[@title='Close window']")
    private WebElement closeSuccessPopUp;




    public void enterProductQuantity(String productQuantity){

        GenericMethods.clearElement(quantityInput);
        GenericMethods.sendKeys(quantityInput, productQuantity);

        // Clicking on quantity label after entering quantity value
        // otherwise the control does not come out of the quantity input box
        GenericMethods.clickElement(quantityLabel);
        log.debug("Entered product quantity as {}", productQuantity);
    }

    public void selectProductSize(String productSize){
        GenericMethods.selectByValue(sizeSelectBox, productSize);
        log.debug("Selected product size as {}", productSize);
    }

    public void selectProductColour(String productColour){
        WebElement colour = DriverManager.get().findElement(By.xpath("//a[@name='"+productColour+"']"));
        GenericMethods.clickElement(colour);
        log.debug("Selected product colour as {}", productColour);

    }

    public void addToCart(){
        GenericMethods.clickElement(addToCartButton);
        GenericMethods.assertElementIsDisplayed(productAddedSuccessMessage);
        GenericMethods.clickElement(closeSuccessPopUp);
        log.info("Added product to cart");

    }

//    public String getProductPrice(){
//        return GenericMethods.getText(productPrice);
//    }


}
