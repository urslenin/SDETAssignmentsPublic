from selenium.webdriver.common.by import By
from FlightsWebAutomation.Test.PageObject.Locators import Locators


class FlightSearch(object):

    def __init__(self, driver):
        self.driver = driver

    def getFromCityInput(self):
        return self.driver.find_element(By.XPATH, Locators.fromCityInput)

    def getFromCityPath(self):
        return self.driver.find_element(By.XPATH, Locators.fromCityPath)

    def getToCityXpath(self):
        return self.driver.find_element(By.XPATH, Locators.toCityXpath)

    def getToCityInput(self):
        return self.driver.find_element(By.XPATH, Locators.toCityInput)

    def getSearchBtn(self):
        return self.driver.find_element(By.XPATH, Locators.searchBtn)

    def getReturnXpath(self):
        return self.driver.find_element(By.XPATH, Locators.returnXpath)
