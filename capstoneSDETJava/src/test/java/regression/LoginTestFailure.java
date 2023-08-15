package regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import testbase.BaseClass;
import utils.Log;

public class LoginTestFailure extends BaseClass {

    @Parameters({"username_invalid","password_invalid"})
    @Test(priority = 0)
    public void testLoginFailure(String username, String password){
        Log.startTestCase("TestLoginFailure");
        loginAs(username, password);
        Log.info("Login with invalid credentials completed");
        driverWait(4);
        String actual_ErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        LoginPage loginPage = new LoginPage();
        String expected_ErrorMsg = loginPage.loginErrorDetails();
        Log.info("Verify Login Error :"  +expected_ErrorMsg);
        if(actual_ErrorMsg.equals(expected_ErrorMsg))
            Assert.assertTrue(true);
        else
            Assert.fail();
        reportInformation("TestLoginFailure_");
        Log.endTestCase(
                "TestLoginFailure");
    }

}
