package au.com.woolies.businessLayers;

import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class BaseLib {
    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    GenericMethods genericMethods = new GenericMethods();



}
