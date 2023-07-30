from selenium.webdriver.common.by import By
from FlightsWebAutomation.Test.PageObject.Locators import Locators


class Home(object):

    def __init__(self, driver):
        self.driver = driver

        self.flightsPath = driver.find_element(By.XPATH, Locators.flightsPath)
        self.mmtLogo = driver.find_element(By.XPATH, Locators.mmtLogo)

    def getFlightsPath(self):
        return self.flightsPath

    def getMMTLogo(self):
        return self.mmtLogo

