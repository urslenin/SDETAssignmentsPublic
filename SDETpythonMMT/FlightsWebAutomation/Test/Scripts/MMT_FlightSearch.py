import datetime
from time import sleep
from datetime import date

from selenium.webdriver import ActionChains, Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from FlightsWebAutomation.Test.PageObject.Pages.FlightSearchPage import FlightSearch
from FlightsWebAutomation.Test.PageObject.Pages.FlightSuccesPage import FlightSuccess
from FlightsWebAutomation.Test.PageObject.Pages.HomePage import Home
from FlightsWebAutomation.Test.TestBase.EnvironmentSetup import EnvironmentSetup


class MMT_FlightSearch(EnvironmentSetup):
    urlMMT = "https://www.makemytrip.com/"

    def test_FlightSearch(self):
        driver = self.driver
        self.driver.get(self.urlMMT)
        self.driver.set_page_load_timeout(20)

        self.fromLocation = "MAA"
        self.toLocation = "HYD"
        self.departureDate = str(datetime.datetime.strptime(str(date.today()), "%Y-%m-%d").strftime("%b %d %Y"))
        self.returnDate = str(datetime.datetime.strptime(str(date.today() + datetime.timedelta(10)), "%Y-%m-%d").strftime("%b %d %Y"))
        self.dateFrom = "//div[contains(@aria-label,'" + self.departureDate + "')]"
        self.dateTo = "//div[contains(@aria-label,'" + self.returnDate + "')]"
        self.searchPageSuccess = "//span[contains(text(),'Flights from')]"
        self.searchCityList = "//li[@role='option']//div[contains(@class,'Right')]"
        self.fromCityPath = "//input[@id='fromCity']"
        self.promotionAlert = "//iframe[starts-with(@title, 'notification-frame')]"
        self.alertClose = "//i[@class='wewidgeticon we_close']"
        sleep(3)

        # Verify Promotional Alert
        try:
            self.wait = WebDriverWait(self.driver, 5)
            self.wait.until(expected_conditions.frame_to_be_available_and_switch_to_it((By.XPATH, self.promotionAlert)))
            self.wait.until(expected_conditions.element_to_be_clickable((By.XPATH, self.alertClose))).click()
        except:
            print("Promotional Alert not visible today")
        # Close SignUp Page
        home = Home(self.driver)
        self.action = ActionChains(self.driver)
        self.action.double_click(home.getMMTLogo()).perform()
        sleep(2)
        home.getFlightsPath().click()
        # Enter Search Flight
        flightSearch = FlightSearch(self.driver)
        self.waitForElementClickable(self.fromCityPath, 20)
        flightSearch.getFromCityPath().click()
        flightSearch.getFromCityInput().send_keys(self.fromLocation)
        sleep(2)
        self.clickElementFromList(self.searchCityList, self.fromLocation)
        sleep(2)
        flightSearch.getFromCityPath().send_keys(Keys.TAB)
        sleep(2)
        flightSearch.getToCityInput().send_keys(self.toLocation)
        sleep(2)
        self.clickElementFromList(self.searchCityList, self.toLocation)
        self.getElement(self.dateFrom).click()
        sleep(2)
        flightSearch.getReturnXpath().click()
        self.getElement(self.dateTo).click()
        sleep(2)
        flightSearch.getSearchBtn().click()
        sleep(5)
        # Verify Search Flight Success Page
        flightSuccess = FlightSuccess(driver)
        self.waitForElementClickable(self.searchPageSuccess, 30)
        if flightSuccess.getSearchPageSuccess().is_displayed():
            print("Search Page loaded with Flight Details")
        else:
            print("Search Page not loaded")

    def getElement(self, element_locator):
        return self.driver.find_element(By.XPATH, element_locator)

    def clickElementFromList(self, elements_path, element_text):
        self.elements = self.driver.find_elements(By.XPATH, elements_path)
        for element in self.elements:
            if element.text == element_text:
                element.click()
                break

    def waitForElementClickable(self, element_locator, sec):
        try:
            self.wait = WebDriverWait(self.driver, sec)
            self.wait.until(expected_conditions.element_to_be_clickable((By.XPATH, element_locator)))
        except:
            print("Exception during wait for an element")
