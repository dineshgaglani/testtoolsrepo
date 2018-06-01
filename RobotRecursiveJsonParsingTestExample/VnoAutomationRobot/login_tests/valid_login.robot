*** Settings ***
Documentation     A test suite with a single test for valid login.
...
...               This test has a workflow that is created using keywords in
...               the imported resource file.
Resource          resource.robot

*** Test Cases ***
Valid Login
    Open Browser To Login Page
    Input Username    vnoportal_automation
    Input Password    iSPAql1jbpN77qLQlv9l15flskF+YdrvPLPD/o9bC8PZt0DF
    Input Pushcode    push
    Submit Credentials
    Welcome Page Should Be Open
    Menu structure should be displayed
    [Teardown]    Close Browser
