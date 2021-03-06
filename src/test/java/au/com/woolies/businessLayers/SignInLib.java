package au.com.woolies.businessLayers;


import au.com.woolies.pages.HeaderPage;
import au.com.woolies.pages.SignInPage;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.Properties;

public class SignInLib {

    private static final Logger log = LogManager.getLogger(SignInLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    SignInPage signInPage = new SignInPage();
    HeaderPage headerPage = new HeaderPage();

    /**
     * Function to login
     * @param userName Username to be used for login
     * @param password Password to be used for login
     * @param customerName Customer name displayed in header after login
     */
    public void signIn(String userName, String password, String customerName){
        signInPage.enterUsername(userName);
        signInPage.enterPassword(password);
        signInPage.submit();
        String loggedInUser = headerPage.getLoggedInUser();
        if (loggedInUser.equals(customerName)){
            log.info("Signed in successfully as '{}' user", customerName);
        }
        else{
            Assert.fail("Failed to validate logged in user, expected customer name :'"+customerName+"', actual customer name: '"+loggedInUser+"'");
        }
    }



}
