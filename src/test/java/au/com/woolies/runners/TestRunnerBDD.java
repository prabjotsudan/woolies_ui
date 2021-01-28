package au.com.woolies.runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/cucumber-html-report",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/Cucumber.xml"
        },
        features = {"src/test/java/au/com/woolies/features"},
        tags = {"@placeOrder"},
        glue = "au/com/woolies/steps",
        monochrome = false
)
public class TestRunnerBDD {
}





