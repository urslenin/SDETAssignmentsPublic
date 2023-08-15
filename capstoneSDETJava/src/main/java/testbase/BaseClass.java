package testbase;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeTest;
import utils.CommonFunctions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import utils.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass extends CommonFunctions {
    ConfigFileReader configReader = ConfigFileReader.getInstance();
    String browserType = configReader.getBrowserType();
    String driverPath = System.getProperty("user.dir") + configReader.getDriverPath();
    String applicationUrl = configReader.getApplicationUrl();
    String screenshotFolder = System.getProperty("user.dir") + configReader.getReportsPath();
    String log4jPath = System.getProperty("user.dir") + configReader.getLog4jPath();

    @BeforeSuite
    public void setup() {
        killChromeDriverInstance();
        String dateYYYYMMMDD = new SimpleDateFormat("yyyy-MMM-dd").format(new Date());
        String dateHHMMSS = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date());
        String screenShotPath = screenshotFolder + dateYYYYMMMDD + "\\" + dateHHMMSS + "\\ScreenShots\\";
        screenPrintSetup(screenShotPath);
        logSetup();
        Log.info("Log4j initiated");
    }

    @BeforeTest
    public void launchDriver() {
        if (driver == null) {
            startDriver(browserType, driverPath);
            setApplicationURL(applicationUrl);
        }
    }

    @AfterTest
    public void quitDriver() {
        if (driver != null)
            driver.quit();
    }

    private void killChromeDriverInstance() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (Exception e) {

        }
    }

    public void logSetup() {
        PropertyConfigurator.configure(log4jPath);
    }

}
