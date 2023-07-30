package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BSDETAssignmentSearchFlight {

    public WebDriver driver = null;
    public static String makeMyTripUrl = "https://www.makemytrip.com/";
    String chromePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\drivers\\chromedriver.exe";

    public static void main(String[] args) throws InterruptedException {
        BSDETAssignmentSearchFlight searchFlight = new BSDETAssignmentSearchFlight();
        searchFlight.killChromeDriverInstance();
        searchFlight.launchDriver("chrome");
        searchFlight.navigateToPage(makeMyTripUrl);
        String dateDeparture = searchFlight.getDate("MMM dd yyyy", 0);
        String dateReturn = searchFlight.getDate("MMM dd yyyy", 2);
        searchFlight.searchFlight("MAA", "HYD", dateDeparture, dateReturn);
        searchFlight.quitDriver();
    }
    public void launchDriver(String browserType){

        if( browserType.equalsIgnoreCase("chrome") ){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("enable-automation");
            options.addArguments("-remote-allow-origins=*");
            options.addArguments("start-maximized");
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else if( browserType.equalsIgnoreCase("edge") ){
            driver = new EdgeDriver();
        }
    }
    public void quitDriver(){
        if(driver != null){
            driver.quit();
        }
    }
    public String getDate(String format, int noOfDays){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, noOfDays);
        Date modifiedDate = cal.getTime();
        String date = dateFormat.format(modifiedDate);
        return date;
    }
    public void navigateToPage(String url){
        driver.get(url);
    }
    public void searchFlight(String fromLocation, String toLocation, String departureDate, String returnDate)throws InterruptedException{
        By flightsPath   = By.xpath("//span[text()='Flights']");
        By fromCityPath  = By.xpath("//input[@id='fromCity']");
        By fromCityInput = By.xpath("//input[@placeholder='From']");
        By toCityXpath   = By.xpath("//input[@id='toCity']");
        By toCityInput   = By.xpath("//input[@placeholder='To']");
        By searchBtn     = By.xpath("//a[text()='Search']");
        By searchCityList= By.xpath("//li[@role='option']//div[contains(@class,'Right')]");
        By dateFrom      = By.xpath("//div[contains(@aria-label,'"+ departureDate+"')]");
        By dateTo        = By.xpath("//div[contains(@aria-label,'"+ returnDate+"')]");
        By returnXpath   = By.xpath("//span[text()='Return']");
        By searchPageSuccess = By.xpath("//span[contains(text(),'Flights from')]");
        driverWait(3);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@title, 'notification-frame')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='wewidgeticon we_close']"))).click();

        Actions action = new Actions(driver);
        action.doubleClick(getElement(By.xpath("//a[contains(@class,'mmtLogo')]"))).perform();
        //getElement(By.xpath("//a[contains(@class,'mmtLogo')]")).click();

        getElement(flightsPath).click();
        waitForElementClickable(fromCityPath, 20);
        getElement(fromCityPath).click();
        getElement(fromCityInput).sendKeys(fromLocation);
        driverWait(2);
        clickElementFromList(searchCityList, fromLocation);
        driverWait(2);
        getElement(fromCityPath).sendKeys(Keys.TAB);
        driverWait(2);
        getElement(toCityInput).sendKeys(toLocation);
        driverWait(2);
        clickElementFromList(searchCityList, toLocation);
        getElement(dateFrom).click();
        driverWait(2);
        getElement(returnXpath).click();
        getElement(dateTo).click();
        driverWait(2);
        getElement(searchBtn).click();
        waitForElementClickable(searchPageSuccess, 30);
        if(getElement(searchPageSuccess).isDisplayed())
            System.out.println("Search Page loaded with Flight Details");
        else
            System.out.println("Search Page not loaded");
    }

    public void clickElementFromList(By elements, String elementText){
        List<WebElement> webElements = driver.findElements(elements);
        for(WebElement element : webElements){
            if(element.getText().equals(elementText))
                element.click();
            break;
        }

    }

    public WebElement getElement(By elementLocator){
        return driver.findElement(elementLocator);
    }

    public void driverWait(int sec){
        try{
            int secs = sec*1000;
            Thread.sleep(secs);
        }catch (Exception e){

        }

    }
    public void waitForElementClickable(By element, int sec){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 10 );
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            System.out.println(" Exception during wait for element" + e.getStackTrace());
        }

    }
    private void killChromeDriverInstance() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        }catch(Exception e) {

        }
    }
}
