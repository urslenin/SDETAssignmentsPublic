package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


@RunWith(Cucumber.class)
public class stepDefinition extends CommonFunctions{
    public static String makeMyTripUrl = "https://www.makemytrip.com/";

    @Given("^User is on MakeMyTrip landing page$")
    public void user_is_on_MakeMyTrip_LandingPage() throws Throwable {
            driver.get(makeMyTripUrl);
    }
    @When("^User Enters \"(.*)\" and \"(.*)\"$")
    public void user_enters_FromLocation_ToLocation(String fromLocation, String toLocation) throws Throwable{
        By onLoadAlert = By.xpath("//iframe[starts-with(@title, 'notification-frame')]");
        By alertClose = By.xpath("//i[@class='wewidgeticon we_close']");
        By flightsPath   = By.xpath("//span[text()='Flights']");
        By fromCityPath  = By.xpath("//input[@id='fromCity']");
        By fromCityInput = By.xpath("//input[@placeholder='From']");
        By toCityInput   = By.xpath("//input[@placeholder='To']");
        By searchCityList= By.xpath("//li[@role='option']//div[contains(@class,'Right')]");
        driverWait(3);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(onLoadAlert));
        wait.until(ExpectedConditions.elementToBeClickable(alertClose)).click();

        Actions action = new Actions(driver);
        action.doubleClick(getElement(By.xpath("//a[contains(@class,'mmtLogo')]"))).perform();

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
    }
    @And("^User Selects DepartureDate and ReturnDate$")
    public void User_Selects_DepartureDate_ReturnDate() throws Throwable{
        String departureDate = getDate("MMM dd yyyy", 0);
        String returnDate = getDate("MMM dd yyyy", 2);
        By dateFrom      = By.xpath("//div[contains(@aria-label,'"+ departureDate+"')]");
        By dateTo        = By.xpath("//div[contains(@aria-label,'"+ returnDate+"')]");
        By returnXpath   = By.xpath("//span[text()='Return']");
        getElement(dateFrom).click();
        driverWait(2);
        getElement(returnXpath).click();
        getElement(dateTo).click();
        driverWait(2);
    }
    @And("^User Clicks on Search Button$")
    public void cards_are_displayed() throws Throwable{
        By searchBtn     = By.xpath("//a[text()='Search']");
        driverWait(2);
        getElement(searchBtn).click();
    }
    @Then("^Search page is displayed$")
    public void Flights_SearchPage_Display() throws Throwable{
        By searchPageSuccess = By.xpath("//span[contains(text(),'Flights from')]");
        waitForElementClickable(searchPageSuccess, 30);
        if(getElement(searchPageSuccess).isDisplayed())
            System.out.println("Search Page loaded with Flight Details");
        else
            System.out.println("Search Page not loaded");
    }
    @And("^User Close the WebPage$")
    public void close_Browser() throws Throwable{
        quitDriver();
    }

}
