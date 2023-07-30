package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonFunctions {
    public WebDriver driver = null;
    String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe";
    String browserType = "chrome";

    public CommonFunctions() {
        this.driver = launchDriver();
    }


    public WebDriver launchDriver() {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("enable-automation");
            options.addArguments("-remote-allow-origins=*");
            options.addArguments("start-maximized");
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
            return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getDate(String format, int noOfDays) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, noOfDays);
        Date modifiedDate = cal.getTime();
        String date = dateFormat.format(modifiedDate);
        return date;
    }

    public void clickElementFromList(By elements, String elementText) {
        List<WebElement> webElements = driver.findElements(elements);
        for (WebElement element : webElements) {
            if (element.getText().equals(elementText))
                element.click();
            break;
        }

    }

    public WebElement getElement(By elementLocator) {
        return driver.findElement(elementLocator);
    }

    public void driverWait(int sec) {
        try {
            int secs = sec * 1000;
            Thread.sleep(secs);
        } catch (Exception e) {

        }

    }

    public void waitForElementClickable(By element, int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println(" Exception during wait for element" + e.getStackTrace());
        }

    }


}
