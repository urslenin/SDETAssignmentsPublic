package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import pageobjects.LoginPage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class CommonFunctions {
    static public WebDriver driver;

    static String screenShotPath="";
    static String latestScreenshotPath;

    public void setApplicationURL(String url){
        if(url != null && !url.isEmpty())
            driver.get(url);
        else
            System.out.println("Given URL is empty or null");
    }
    public void startDriver(String browserType, String driverPath){
        if( browserType.equalsIgnoreCase("chrome") ){
            System.out.println("launching Chrome browser");
            Log.info("Test ");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("enable-automation");
            options.addArguments("-remote-allow-origins=*");
            options.addArguments("start-maximized");
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(ConfigFileReader.getInstance().getImplicitlyWait(), TimeUnit.SECONDS);
        } else if( browserType.equalsIgnoreCase("edge") ){
            driver = new EdgeDriver();
        }
    }
    public static void takeScreenshot(String testCaseName){
        if(driver == null)
            return;
        String snapFile;
        try {
            String dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss_SSS").format(new GregorianCalendar().getTime());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            snapFile = screenShotPath+testCaseName+dateFormat+".png";
            FileHandler.copy(screenshot, new File(snapFile));
            latestScreenshotPath = snapFile;
        }catch(Exception e){
            e.getMessage();
        }
    }

    public void reportInformation(String testCaseName){
        takeScreenshot(testCaseName);
        String path = "<a href='"+latestScreenshotPath+"'><img src='"+latestScreenshotPath+"' ;height='100' width='100'/a>";
        Reporter.log(path);
    }

    protected void screenPrintSetup(String screenShotPath) {
        try {
            this.screenShotPath = screenShotPath;
            File screenshotFile = new File(screenShotPath);
            if (!(screenshotFile.exists() && screenshotFile.isDirectory()))
                screenshotFile.mkdirs();
        }catch(Exception e){
            e.getStackTrace();
        }

    }

    public void loginAs(String username, String password){
        LoginPage loginpage = new LoginPage();
        loginpage.enterUsername(username);
        loginpage.enterPassword(password);
        reportInformation("TestLoginBefore_");
        loginpage.clickLogin();
    }

    public void driverWait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        }catch(InterruptedException e){
            e.getStackTrace();
        }
    }

    public String getTitle() {
        try {
            return driver.getTitle();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public String getText(String xpath_identifier) {
        try {
            return driver.findElement(By.xpath(xpath_identifier)).getText();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
    }

    public String getText(By identifier) {
        try {
            return driver.findElement(identifier).getText();
        }catch(Exception e){
            e.getStackTrace();
        }
        return null;
    }
}