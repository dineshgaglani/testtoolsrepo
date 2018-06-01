*** Settings ***
Documentation     A resource file with reusable keywords and variables.
...
...               The system specific keywords created here form our own
...               domain specific language. They utilize keywords provided
...               by the imported Selenium2Library.
Library           Selenium2Library
Library          ../menuBarParser.py
Library          ../WebDriverProvider.py

*** Variables ***
${SERVER}         vnoapp01.dev.vnoportal.viasat.io:3000
${BROWSER}        Chrome
${DELAY}          1
${VALID USER}     vnoportal_automation
${VALID PASSWORD}    iSPAql1jbpN77qLQlv9l15flskF+YdrvPLPD/o9bC8PZt0DF
${VALID PUSHCODE}   push
${LOGIN URL}      https://${SERVER}/
${WELCOME URL}    https://${SERVER}/dashboard/vno-portal
${ERROR URL}      https://${SERVER}/error.html

*** Keywords ***
Open Browser To Login Page
    Open Browser    ${LOGIN URL}    ${BROWSER}
    Maximize Browser Window
    Set Selenium Speed    ${DELAY}
    Login Page Should Be Open

Login Page Should Be Open
    Title Should Be    VNO Portal

Go To Login Page
    Go To    ${LOGIN URL}
    Login Page Should Be Open

Input Username
    [Arguments]    ${username}
    Input Text    username    ${username}

Input Password
    [Arguments]    ${password}
    Input Text    password    ${password}

Input Pushcode
    [Arguments]    ${pushcode}
    Input Text    token    ${pushcode}

Submit Credentials
    Click Button    css:[type*="submit"]

Welcome Page Should Be Open
#    Location Should Be    ${WELCOME URL}
    Title Should Be    VNO Portal

Menu structure should be displayed
    Wait Until Element Is Visible  css:div#vno-menubar-collapse>ul>li:nth-child(1)
    ${webdriver}  Get Webdriver Instance
    Log To Console  webdriver: ${webdriver}
    Test Menu Parser Program  ${webdriver}


    
