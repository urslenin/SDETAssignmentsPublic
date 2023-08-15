from selenium import webdriver
from selenium.webdriver.common.by import By


class LoginPage:
    username_xpath = "//input[@id='user-name']"
    password_xpath = "//input[@id='password']"
    login_xpath = "//input[@id='login-button']"
    logo_xpath = "//div[@class='app_logo']"

    def __init__(self, driver):
        self.driver = driver

    def setUserName(self, username):
        self.driver.find_element(By.XPATH, self.username_xpath).clear()
        self.driver.find_element(By.XPATH, self.username_xpath).send_keys(username)

    def setPassword(self, password):
        self.driver.find_element(By.XPATH, self.password_xpath).clear()
        self.driver.find_element(By.XPATH, self.password_xpath).send_keys(password)

    def clickLogin(self):
        self.driver.find_element(By.XPATH, self.login_xpath).click()

    def getLogo(self):
        return self.driver.find_element(By.XPATH, self.logo_xpath).text
