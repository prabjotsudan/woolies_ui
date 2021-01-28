package au.com.woolies.businessLayers;



import au.com.woolies.pages.HeaderPage;
import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import au.com.woolies.pages.Homepage;

import java.util.Properties;

public class HomepageLib {

    private static final Logger log = LogManager.getLogger(HomepageLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    //GenericMethods genericMethods = new GenericMethods();
    Homepage homepage = new Homepage();
    HeaderPage headerPage = new HeaderPage();

    /**
     * Function to navigate to homepage
     */
    public boolean navigateToHomepage(){
        GenericMethods.navigateTo(setupProperties.getProperty("homepageUrl"));

        if (DriverManager.get().getTitle().equals("My Store")){
            return true;
        }
        else {
            return false;
        }
    }

    public void openSignInPage(){
        headerPage.clickSignInLink();
    }





}
