from selenium import webdriver
from selenium.webdriver.common.by import By


class AddToCart:
    addToCartBackpack_xpath = "//button[@id='add-to-cart-sauce-labs-backpack']"
    shoppingCartBadge_xpath = "//span[@class='shopping_cart_badge']"
    menu_xpath = "//button[@id='react-burger-menu-btn']"
    logout_xpath = "//a[@id='logout_sidebar_link']"

    def __init__(self, driver):
        self.driver = driver

    def clickAddToCartBackpack(self):
        self.driver.find_element(By.XPATH, self.addToCartBackpack_xpath).click()

    def getShoppingCartBadgeDetails(self):
        return self.driver.find_element(By.XPATH, self.shoppingCartBadge_xpath).text

    def clickMenu(self):
        self.driver.find_element(By.XPATH, self.menu_xpath).click()

    def clickLogOut(self):
        self.driver.find_element(By.XPATH, self.logout_xpath).click()


