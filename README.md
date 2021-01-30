# woolies_ui
UI automation framework for woolies coding challenge

Technologies used:
  - Java 1.8 (Junit)
  - Maven
  - Selenium
  - Cucumber 
  
## Installation instructions:
  1. Clone repo using 
  2. Open the project in your IDE (Eclipse / intelliJ)
  3. You will see the following structure:
```
├── README.md                                                       # You are reading this now :-)
├── pom.xml                                                         # Maven file 
├── src                   
│   └── test
│       ├── java                                                    # Test folder containing all the test code
│       │   └── au
│       │       └── com
│       │           └── woolies                                     # Project structure
│       │               ├── businessLayers                          # Package containing all the business layer files (library files)
│       │               │   ├── CartLib.java
│       │               │   ├── HeaderLib.java
│       │               │   ├── HomepageLib.java
│       │               │   ├── ProductDetailsLib.java
│       │               │   ├── SearchResultsLib.java
│       │               │   └── SignInLib.java
│       │               ├── features                                # Package containing all the feature files
│       │               │   ├── placeOrderWithBankWire.feature      # Scenario which places an order with 2 products
│       │               │   └── placeOrderWithCheque.feature        # Scenario which places an order with 1 product
│       │               ├── pages                                   # Package containing POM files
│       │               │   ├── BasePage.java
│       │               │   ├── CartPage.java
│       │               │   ├── HeaderPage.java
│       │               │   ├── ProductDetailsPage.java
│       │               │   ├── SearchResultsPage.java
│       │               │   └── SignInPage.java
│       │               ├── runners                                 # Package containing runner file 
│       │               │   └── TestRunnerBDD.java
│       │               ├── steps                                   # Package with step defs and the hook file for setup, teardown, and html report generation
│       │               │   ├── BaseStepDefs.java
│       │               │   ├── CartStepDefs.java
│       │               │   └── Hook.java
│       │               └── utils                                   # Package with all the utility classes
│       │                   ├── CucumberReport.java
│       │                   ├── DriverManager.java
│       │                   ├── GenericMethods.java
│       │                   └── PropertyReader.java
│       └── resources                                               # This folder stores the setup.properties file which is used for configuration 
│           ├── au
│           │   └── com
│           │       └── woolies
│           │           └── setup.properties                        # This is the config file that can be used to control framework behavior
│           └── log4j2.properties                                   # Properties file for log4j2 configuration
├── target                                                          # (Not committed to source code) Output folder for test reports and debug logs

```

## Test execution instructions:
  1. Open **src/test/resources/au/com/woolies/setup.properties** file 
  2. This is the configuration file for project, it contains all the configurable parameters
  3. Some of the crucial parameters are username, password, and customerName, where customer name is the name displayed in the header of the page. After login, this is used to verify if login is successful
  4. This framework supports browserstack execution, so there is a dedicated section for BS parameters
  5. To start test execution, there are 2 ways:
      1. **Sequential execution** : Navigate to **src/test/java/au/com/woolies/runners/TestRunnerBDD.java** and start execution
      2. **Parallel execution** : Navigate to the directory containing project in the terminal and execute **"mvn clean install"** command
      
## Logs and Reports:
  * This framework has log4j2 implementation, so it supports rollover logs which can be found at **target/logs/debug.log** location
  * HTML reports with embedded screenshots are located at **target/cucumber-html-reports/overview-features.html**

## Framework Features:
  * **Automated driver handling**: Framework has the feature to automatically handle multiple drivers, be it firefox, chrome, IE, and on top of that it automatically manages different browser versions
  * **Parallel Execution**: Framework supports parallel execution of feature files, which reduces the execution time 
  * **BrowserStack integration**: Framework has browserstack integration which compliments the parallel execution as the system bottleneck to support a large number of threads is removed by browserstack
  * **Embeddded screenshots in reports**: Framework has a configurable feature to capture and embed screenshots after every step in HTML reports, this can be turned on or off by changing the **captureScreenshotsAfterEveryStep** parameter value in **src/test/resources/au/com/woolies/setup.properties** file
  * **Failure screenshots**: In case of any failure, a screenshot is captured and embedded in HTML report irrespective of **captureScreenshotsAfterEveryStep** setting
  * **Rollover logs**: Framework has a rollover logging mechanism, to make it scalable, the rollover mechanism is configured to rollover in the case log file size reaches 5MB
