class Locators(object):
    # flight search locator
    flightsPath = "//li[@class='menu_Flights']//span[text()='Flights']"
    fromCityPath = "//input[@id='fromCity']"
    fromCityInput = "//input[@placeholder='From']"
    toCityXpath = "//input[@id='toCity']"
    toCityInput = "//input[@placeholder='To']"
    searchBtn = "//a[text()='Search']"
    searchCityList = "//li[@role='option']//div[contains(@class,'Right')]"
    returnXpath = "//span[text()='Return']"
    searchPageSuccess = "//span[contains(text(),'Flights from')]"
    promotionAlert = "//iframe[starts-with(@title, 'notification-frame')]"
    alertClose = "//i[@class='wewidgeticon we_close']"
    mmtLogo = "//a[contains(@class,'mmtLogo')]"
