package au.com.woolies.businessLayers;



import au.com.woolies.pages.HeaderPage;
import au.com.woolies.utils.GenericMethods;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HomepageLib {

    private static final Logger log = LogManager.getLogger(HomepageLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    HeaderPage headerPage = new HeaderPage();

    /**
     * Function to navigate to homepage
     */
    public boolean navigateToHomepage(){
        GenericMethods.navigateTo(setupProperties.getProperty("homepageUrl"));

        if (GenericMethods.getPageTitle().equals("My Store")){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Function to open sign in page
     */
    public void openSignInPage(){
        headerPage.clickSignInLink();
        log.debug("Clicked signIn link from homepage");
    }

}
