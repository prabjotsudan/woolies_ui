package au.com.woolies.businessLayers;


import au.com.woolies.pages.ProductDetailsPage;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ProductDetailsLib {

    private static final Logger log = LogManager.getLogger(ProductDetailsLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    ProductDetailsPage productDetailsPage = new ProductDetailsPage();

    /**
     * Function to enter product details before adding it to cart
     * @param quantity Product quantity required
     * @param size Product size required
     * @param colour Product colour required
     */
    public void enterProductDetails(String quantity, String size, String colour){
        log.info("Selecting product details as per order");
        productDetailsPage.enterProductQuantity(quantity);
        productDetailsPage.selectProductSize(size);
        productDetailsPage.selectProductColour(colour);
    }

    /**
     * Function to add product to cart
     */
    public void addToCart(){
        productDetailsPage.addToCart();
    }

}
