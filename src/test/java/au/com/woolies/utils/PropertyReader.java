package au.com.woolies.utils;

import org.junit.Assert;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getSetupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/au/com/woolies/setup.properties"));
        } catch (Exception e) {
            Assert.fail("Failed to read setup properties \n" + e.toString());
        }
        return properties;
    }
}
