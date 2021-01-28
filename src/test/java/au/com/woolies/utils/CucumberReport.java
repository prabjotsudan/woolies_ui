package au.com.woolies.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReport {

    public void generateReport() {
        File reportOutputDirectory = new File("target/");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/CucumberTestReport.json");

        String projectName = "Author Automation";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        configuration.addClassifications("Browser", "Chrome");
        configuration.setSortingMethod(SortingMethod.NATURAL);
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
