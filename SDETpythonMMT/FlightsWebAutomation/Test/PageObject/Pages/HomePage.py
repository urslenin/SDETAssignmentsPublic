from selenium.webdriver.common.by import By
from FlightsWebAutomation.Test.PageObject.Locators import Locators


class Home(object):

    def __init__(self, driver):
        self.driver = driver

    def getFlightsPath(self):
        return self.driver.find_element(By.XPATH, Locators.flightsPath)

    def getMMTLogo(self):
        return self.driver.find_element(By.XPATH, Locators.mmtLogo)

