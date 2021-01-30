package au.com.woolies.steps;

import au.com.woolies.businessLayers.*;
import au.com.woolies.utils.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseStepDefs {

    private static final Logger log = LogManager.getLogger(BaseStepDefs.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    HomepageLib homepageLib = new HomepageLib();
    SignInLib signInLib = new SignInLib();
    HeaderLib headerLib = new HeaderLib();
    SearchResultsLib searchResultsLib = new SearchResultsLib();
    ProductDetailsLib productDetailsLib = new ProductDetailsLib();

    @Given("I am on the homepage")
    public void iAmOnTheHomepae() {
        if (! homepageLib.navigateToHomepage()){
            Assert.fail("Failed to navigate to homepage");
        }
        else {
            log.info("Homepage loaded successfully");
        }
    }

    @And("I login using credentials")
    public void iLoginUsingCredentials() {
        String username = setupProperties.getProperty("username");
        String password = setupProperties.getProperty("password");
        String customerName = setupProperties.getProperty("customerName");
        homepageLib.openSignInPage();
        signInLib.signIn(username, password, customerName);

    }

    @Given("I search for a product and add it to cart")
    public void iSearchForAProductAndAddItToCart(DataTable data) {
        List<Map<String, String>> dataToUse = data.asMaps(String.class, String.class);
        String productName = dataToUse.get(0).get("productName");
        String productColour = dataToUse.get(0).get("colour");
        String productSize = dataToUse.get(0).get("size");
        String productQuantity = dataToUse.get(0).get("quantity");
        headerLib.searchProduct(productName);
        searchResultsLib.openProductDetails();
        productDetailsLib.enterProductDetails(productQuantity, productSize, productColour);
        productDetailsLib.addToCart();
    }

}
