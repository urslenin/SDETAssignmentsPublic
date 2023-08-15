import pytest
import datetime
from selenium import webdriver
from time import sleep


@pytest.fixture()
def setup():
    if browser == 'chrome':
        driver = webdriver.Chrome()
        print("Test Started at: " + str(datetime.datetime.now()))
        print("Chrome driver started")
    elif browser == 'firefox':
        driver = webdriver.Firefox()
        print("Firefox driver started")
    else:
        driver = webdriver.Chrome()
    sleep(2)
    driver.implicitly_wait(20)
    driver.maximize_window()
    return driver


################# browser runtime #############################

def pytest_addoption(parser):
    parser.addoption("--browser")


@pytest.fixture()
def browser(request):
    return request.config.getoption("--browser")


################ html report #################################
# Add Environment Details#
def pytest_configure(config):
    config._metadata = {
        "Tester": "SDET QA",
        "Project Name": "Capstone SDET",
    }
