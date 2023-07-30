import unittest
import datetime
from selenium import webdriver
from time import sleep


class EnvironmentSetup(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()
        print("Test Started at: " + str(datetime.datetime.now()))
        print("Chrome driver started")
        self.driver.implicitly_wait(20)
        self.driver.maximize_window()
        sleep(2)

    def tearDown(self):
        if self.driver is not None:
            self.driver.close()
            self.driver.quit()
            print("Test Completed at: " + str(datetime.datetime.now()))
