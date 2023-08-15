package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testbase.BaseClass;

public class LoginPage extends BaseClass {

    By userNameXpath = By.xpath("//input[@id='user-name']");
    By passwordXpath = By.xpath("//input[@id='password']");
    By loginBtnXpath = By.xpath("//input[@id='login-button']");
    By loginErrorXpath = By.xpath("//div/h3[@data-test='error']");

    public void enterUsername(String username){
        driver.findElement(userNameXpath).clear();
        driver.findElement(userNameXpath).sendKeys(username);
    }
    public void enterPassword(String password){
        driver.findElement(passwordXpath).clear();
        driver.findElement(passwordXpath).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(loginBtnXpath).click();
    }
    public String loginErrorDetails(){
        return getText(loginErrorXpath);
    }
}
