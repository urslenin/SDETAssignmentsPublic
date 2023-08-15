import pytest
from selenium import webdriver
from pageObjects.LoginPage import LoginPage
from pageObjects.AddToCart import AddToCart
from utils.readProperties import ReadConfig
from utils.customLogger import LogGen
from time import sleep


class Test_002_AddToCart:
    baseURL = ReadConfig.getApplicationURL()
    username = ReadConfig.getUserName()
    password = ReadConfig.getPassword()
    logger = LogGen.logGenerator()

    def test_AddToCart(self, setup):
        self.logger.info("************* Test_002_AddToCart ********")
        self.logger.info("************* AddToCartCompletion ********")
        self.driver = setup
        self.driver.get(self.baseURL)
        self.loginpage = LoginPage(self.driver)
        self.loginpage.setUserName(self.username)
        self.loginpage.setPassword(self.password)
        self.loginpage.clickLogin()
        sleep(2)
        self.AddToCartPage = AddToCart(self.driver)
        self.AddToCartPage.clickAddToCartBackpack()
        sleep(2)
        badgeCount = self.AddToCartPage.getShoppingCartBadgeDetails()
        if badgeCount == "1":
            assert True
            self.driver.save_screenshot(".\\Screenshots\\" + "test_AddToCart.png")
            self.logger.info("************* VerifyAddToCart Passed ********")
        else:
            assert False
            self.driver.save_screenshot(".\\Screenshots\\" + "test_AddToCart.png")
            self.logger.error("************* VerifyAddToCart Failed ********")
        self.AddToCartPage.clickMenu()
        self.AddToCartPage.clickLogOut()
        self.driver.close()


