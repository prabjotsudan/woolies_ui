package au.com.woolies.steps;

import au.com.woolies.utils.CucumberReport;
import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.util.Properties;

public class Hook {
    private static final Logger log = LogManager.getLogger(Hook.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    private static boolean runOnce = false;


    @Before()
    public void setUp(Scenario scenario) throws Exception {
        log.info("***************** {} *****************",scenario.getName());
        // reportHook adds a hook to generate report at the end of execution
        this.reportHook();
        String url = setupProperties.getProperty("homepageUrl");
        //DriverManager.get().navigate().to(url);
        //DriverManager.get().manage().window().maximize();
    }

    @After()
    public void tearDown(Scenario scenario) {
            DriverManager.quitDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario){
        if (setupProperties.getProperty("captureScreenshotsAfterEveryStep").equals("true")) {
            this.captureAndEmbedScreenshotInReport(scenario);
        }
        else if (scenario.isFailed()) {
            this.captureAndEmbedScreenshotInReport(scenario);
        }
    }

    public void reportHook() {
        if (!runOnce) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    CucumberReport cucumberReport = new CucumberReport();
                    cucumberReport.generateReport();
                }
            });
            runOnce = true;
        }
    }


    public void captureAndEmbedScreenshotInReport(Scenario scenario){
        try {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        catch (WebDriverException e) {
            log.error("Failed to capture and embed screenshot in report");
            e.printStackTrace();
        }
    }

}
