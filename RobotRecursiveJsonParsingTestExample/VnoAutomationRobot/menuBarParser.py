import json
import time
from robot.libraries.BuiltIn import BuiltIn

def get_company_menu(companyMenuList, requiredCompany):
    for companyDict in companyMenuList:
        	for company, companyMenu in companyDict.items():
        	    if company == requiredCompany:
        	        return companyMenu

def parse_company_dict(menuList, menusToLocatorsMap, rootPath, iterationNumber):
    for menu in menuList:
        iterationNumber = iterationNumber + 1
        for menuName, subMenus in menu.items():
            rootPathReplaced = rootPath.replace("<IterNum>", str(iterationNumber))
            menusToLocatorsMap[menuName] = rootPathReplaced
            parse_company_dict(subMenus, menusToLocatorsMap, rootPathReplaced + ">ul>li:nth-child(<IterNum>)", 0)

def test_menu_bar_for_company(menuList, menusToLocatorsMap, webDriver):
    for menu in menuList:
        for menuName, subMenus in menu.items():
           print("verifying content at locator ", menusToLocatorsMap[menuName], " is ", menuName)
           mainMenu = webDriver.find_element("css selector", menusToLocatorsMap[menuName])
           BuiltIn().run_keyword("Element Should Contain", "css:" + menusToLocatorsMap[menuName], menuName)
           print("menu element text is: ", mainMenu.text)
           if( len(subMenus) > 0 ):
                menuElement = webDriver.find_element("css selector", menusToLocatorsMap[menuName])
                elementClass = menuElement.get_attribute("class")

                if(elementClass.find("dropdown-submenu") == -1):
                    print("Main menu locator, clicking on: ", menuElement.text)
                    menuElement.click()
                else:
                    print("Sub menu locator, setting focus!")
                    BuiltIn().run_keyword("Mouse Over", "css:" + menusToLocatorsMap[menuName])

           test_menu_bar_for_company(subMenus, menusToLocatorsMap, webDriver)

jsonStr = """[
               {
               "Exede": [
                  {"Dashboards":[]},
                  {"Network": [ {"VNO Alarms":[ {"Alarm Viewer": [] } ]}, {"VNO Performance":[ { "SMAC Exede Residential VNO Performance":[] }, { "VASN Exede Residential VNO Performance":[] }, { "VWA Server side Exede Residential VNO Performance":[] }, { "VWA Client side Exede Residential VNO Performance":[] } ] } ]
                  },
                  {"Beams": [ {"Beam Performance":[ { "SMAC Exede Residential VNO Bean Performance": [] }, { "SMAC Exede Residential VNO Carrier Domain Performance":[] }, { "VWA Server side Exede Residential VNO Bean Performance": [] }, { "VWA Client side Exede Residential VNO Bean Performance": [] } ] }, {"Beam Map": []}]
                  },
                  {"Platform": [ {"Platform Performance":[ { "VASN Exede VNO Platform Metrics":[] }, { "SMAC Platform Performance":[] }, { "VWA Platform Performance": [] } ] }, {"SAN Dashboard": []}]
                  },
                  {"Subscriber Dashboard": [ {"Subscriber Dashboard View":[] } ]
                  }
                 ]
               },
               { "org2":[] }
             ]"""

jsonObj = json.loads(jsonStr)

def test_menu_parser_program(webDriver):
    menusToLocatorsMap = {}
    companyMenuDict = get_company_menu(jsonObj, "Exede")
    parse_company_dict(companyMenuDict, menusToLocatorsMap, "div#vno-menubar-collapse>ul>li:nth-child(<IterNum>)", 0)
    print(menusToLocatorsMap)
    test_menu_bar_for_company(companyMenuDict, menusToLocatorsMap, webDriver)



