from selenium.webdriver.common.by import By
from FlightsWebAutomation.Test.PageObject.Locators import Locators


class FlightSuccess(object):

    def __init__(self, driver):
        self.driver = driver

        self.searchPageSuccess = driver.find_element(By.XPATH, Locators.searchPageSuccess)

    def getSearchPageSuccess(self):
        return self.searchPageSuccess

