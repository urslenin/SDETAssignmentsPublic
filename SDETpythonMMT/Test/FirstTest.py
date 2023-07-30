import datetime
from selenium import webdriver
import unittest
from time import sleep
from datetime import date

from selenium.webdriver import ActionChains, Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait


class FlightSearch(unittest.TestCase):
    urlMMT = "https://www.makemytrip.com/"

    def setUp(self):
        self.driver = webdriver.Chrome()
        print("Test Started at: " + str(datetime.datetime.now()))
        print("Chrome driver started")
        self.driver.implicitly_wait(20)
        self.driver.maximize_window()
        sleep(2)

    def test_searchFlight(self):
        self.driver.get(self.urlMMT)
        self.fromLocation = "MAA"
        self.toLocation = "HYD"
        self.departureDate = str(datetime.datetime.strptime(str(date.today()), "%Y-%m-%d").strftime("%b %d %Y"))
        self.returnDate = str(datetime.datetime.strptime(str(date.today() + datetime.timedelta(10)), "%Y-%m-%d").strftime("%b %d %Y"))
        print("print(self.departureDate)" + self.departureDate)
        self.flightsPath = "//span[text()='Flights']"
        self.fromCityPath = "//input[@id='fromCity']"
        self.fromCityInput = "//input[@placeholder='From']"
        self.toCityXpath = "//input[@id='toCity']"
        self.toCityInput = "//input[@placeholder='To']"
        self.searchBtn = "//a[text()='Search']"
        self.searchCityList = "//li[@role='option']//div[contains(@class,'Right')]"
        self.dateFrom = "//div[contains(@aria-label,'" + self.departureDate + "')]"
        self.dateTo = "//div[contains(@aria-label,'" + self.returnDate + "')]"
        self.returnXpath = "//span[text()='Return']"
        self.searchPageSuccess = "//span[contains(text(),'Flights from')]"
        sleep(3)
        try:
            self.wait = WebDriverWait(self.driver, 10)
            self.wait.until(expected_conditions.frame_to_be_available_and_switch_to_it( (By.XPATH, "//iframe[starts-with(@title, 'notification-frame')]") ))
            self.wait.until(expected_conditions.element_to_be_clickable( (By.XPATH, "//i[@class='wewidgeticon we_close']") )).click()
        except:
            print("Promotional Alert not visible today")

        self.action = ActionChains(self.driver)
        self.action.double_click(self.getElement("//a[contains(@class,'mmtLogo')]")).perform()

        self.getElement(self.flightsPath).click()
        self.waitForElementClickable(self.fromCityPath, 20)
        self.getElement(self.fromCityPath).click()
        self.getElement(self.fromCityInput).send_keys(self.fromLocation)
        sleep(2)
        self.clickElementFromList(self.searchCityList, self.fromLocation)
        sleep(2)
        self.getElement(self.fromCityPath).send_keys(Keys.TAB)
        sleep(2)
        self.getElement(self.toCityInput).send_keys(self.toLocation)
        sleep(2)
        self.clickElementFromList(self.searchCityList, self.toLocation)
        self.getElement(self.dateFrom).click()
        sleep(2)
        self.getElement(self.returnXpath).click()
        self.getElement(self.dateTo).click()
        sleep(2)
        self.getElement(self.searchBtn).click()
        sleep(5)
        self.waitForElementClickable(self.searchPageSuccess, 30)
        if self.getElement(self.searchPageSuccess).is_displayed():
            print("Search Page loaded with Flight Details")
        else:
            print("Search Page not loaded")

    def getElement(self, element_locator):
        return self.driver.find_element(By.XPATH, element_locator)

    def tearDown(self):
        if self.driver is not None:
            self.driver.close()
            self.driver.quit()
            print("Test Completed at: " + str(datetime.datetime.now()))

    def clickElementFromList(self, elements_path, element_text):
        self.elements = self.driver.find_elements(By.XPATH, elements_path)
        for element in self.elements:
            if element.text == element_text:
                element.click()
                break

    def waitForElementClickable(self, element_locator, sec):
        try:
            self.wait = WebDriverWait(self.driver, sec)
            self.wait.until(expected_conditions.element_to_be_clickable(By.XPATH, element_locator))
        except:
            print("Exception during wait for an element")
