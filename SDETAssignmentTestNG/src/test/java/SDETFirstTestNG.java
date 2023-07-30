import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Test
public class SDETFirstTestNG {
    String makeMyTripUrl = "https://www.makemytrip.com/";
    String driverPath = System.getProperty("user.dir")+ "\\src\\main\\resources\\drivers\\chromedriver.exe";
    public WebDriver driver;
    public String expected = null;
    public String actual = null;
    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching Chrome browser");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("enable-automation");
        options.addArguments("-remote-allow-origins=*");
        options.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver(options);
        driver.get(makeMyTripUrl);
        By onLoadAlert = By.xpath("//iframe[starts-with(@title, 'notification-frame')]");
        By alertClose = By.xpath("//i[@class='wewidgeticon we_close']");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(onLoadAlert));
            wait.until(ExpectedConditions.elementToBeClickable(alertClose)).click();
        }catch(Exception e){
            System.out.println("No Promotional Alert");
        }

        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath("//a[contains(@class,'mmtLogo')]"))).perform();

    }

    @BeforeMethod
    public void verifyHomepageTitle() {
        String expectedTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 0)
    public void hotels() throws InterruptedException {
        driver.findElement(By.xpath("//li[@class='menu_Hotels']//span[text()='Hotels']")).click() ;
        expected = "MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide";
        actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
        Thread.sleep(2000);
    }
    @Test(priority = 1)
    public void stays() throws InterruptedException {
        driver.findElement(By.xpath("//li[@class='menu_Homestays']//span[text()='Homestays']")).click() ;
        expected = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
        actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
        Thread.sleep(2000);
    }
    @AfterMethod
    public void goBackToHomepage ( ) throws InterruptedException {
        driver.findElement(By.xpath("//img[@alt='Make My Trip']")).click() ;
        Thread.sleep(2000);
    }

    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
}