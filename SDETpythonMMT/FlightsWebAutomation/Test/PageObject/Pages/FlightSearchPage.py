from selenium.webdriver.common.by import By
from FlightsWebAutomation.Test.PageObject.Locators import Locators


class FlightSearch(object):

    def __init__(self, driver):
        self.driver = driver

        self.fromCityInput = driver.find_element(By.XPATH, Locators.fromCityInput)
        self.fromCityPath = driver.find_element(By.XPATH, Locators.fromCityPath)
        self.toCityXpath = driver.find_element(By.XPATH, Locators.toCityXpath)
        self.toCityInput = driver.find_element(By.XPATH, Locators.toCityInput)
        self.searchBtn = driver.find_element(By.XPATH, Locators.searchBtn)
        self.returnXpath = driver.find_element(By.XPATH, Locators.returnXpath)

    def getFromCityInput(self):
        return self.fromCityInput

    def getFromCityPath(self):
        return self.fromCityPath

    def getToCityXpath(self):
        return self.toCityXpath

    def getToCityInput(self):
        return self.toCityInput

    def getSearchBtn(self):
        return self.searchBtn

    def getReturnXpath(self):
        return self.returnXpath
