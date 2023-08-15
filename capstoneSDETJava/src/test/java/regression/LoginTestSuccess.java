package regression;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import testbase.BaseClass;
import utils.Log;

public class LoginTestSuccess extends BaseClass {

    @Parameters({"username_valid","password_valid"})
    @Test(priority = 1)
    public void testLoginSuccess(String username, String password){
        Log.startTestCase("TestLoginSuccess");
        loginAs(username, password);
        Log.info("Login Completed");
        driverWait(4);
        String expected_Title = "Swag Labs";
        String actual_Title = getTitle();
        Log.info("Verify Title: " + actual_Title);

        if(actual_Title.equals(expected_Title))
            Assert.assertTrue(true);
        else
            Assert.fail();
        reportInformation("TestLoginSuccess_");
        Log.endTestCase("TestLoginSuccess");
    }

}
