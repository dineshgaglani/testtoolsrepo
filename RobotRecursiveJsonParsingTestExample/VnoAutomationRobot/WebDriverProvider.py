from Selenium2Library import Selenium2Library
from robot.libraries.BuiltIn import BuiltIn

def get_webdriver_instance():
    se2lib = BuiltIn().get_library_instance('Selenium2Library')
    print("Browser: ", str(se2lib._current_browser()))
    return se2lib._current_browser()
