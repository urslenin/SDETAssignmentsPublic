import pytest
from selenium import webdriver
from pageObjects.LoginPage import LoginPage
from utils.readProperties import ReadConfig
from utils.customLogger import LogGen


class Test_001_Login:
    baseURL = ReadConfig.getApplicationURL()
    username = ReadConfig.getUserName()
    password = ReadConfig.getPassword()

    logger = LogGen.logGenerator()

    def test_homePageTitle(self, setup):
        self.logger.info("************* Test_001_Login ********")
        self.logger.info("************* VerifyLoginPageTitle ********")
        self.driver = setup
        self.driver.get(self.baseURL)
        actual_title = self.driver.title

        if actual_title == "Swag Labs":
            assert True
            self.driver.save_screenshot(".\\Screenshots\\" + "test_homePageTitle.png")
            self.logger.info("************* VerifyLoginPageTitle Passed ********")
            self.driver.close()
        else:
            self.driver.save_screenshot(".\\Screenshots\\" + "test_homePageTitle.png")
            self.logger.error("************* VerifyLoginPageTitle Failed ********")
            self.driver.close()
            assert False

    def test_login(self, setup):
        self.logger.info("************* test_login ********")
        self.driver = setup
        self.driver.get(self.baseURL)
        self.loginpage = LoginPage(self.driver)
        self.loginpage.setUserName(self.username)
        self.loginpage.setPassword(self.password)
        self.loginpage.clickLogin()
        expected_logo_name = "Swag Labs"
        actual_logo_name = self.loginpage.getLogo()
        if expected_logo_name == actual_logo_name:
            self.driver.save_screenshot(".\\Screenshots\\" + "test_login.png")
            self.logger.info("************* test_login Passed ********")
            self.driver.close()
            assert True
        else:
            self.driver.save_screenshot(".\\Screenshots\\" + "test_login.png")
            self.logger.error("************* test_login Failed ********")
            self.driver.close()
            assert False
