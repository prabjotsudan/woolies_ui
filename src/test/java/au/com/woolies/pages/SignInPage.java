package au.com.woolies.pages;

import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SignInPage extends BasePage{
    private static final Logger log = LogManager.getLogger(SignInPage.class);


    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement submitButton;


    public void enterUsername(String userName){
        GenericMethods.sendKeys(emailInput, userName);
    }

    public void enterPassword(String password){
        GenericMethods.sendKeys(passwordInput, password);
    }

    public void submit(){
        GenericMethods.clickElement(submitButton);
    }









}
