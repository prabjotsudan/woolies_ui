package au.com.woolies.pages;

import au.com.woolies.utils.DriverManager;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(DriverManager.get(), this);
    }
}
