Feature: Contact seller via detailed property listing page

  Scenario: Go to detailed property listing from homepage
    Given I navigate to the roofandfloor homepage
    When I click on the "All Projects" tab
    And I click on a property at the co-ordinate "1" and "1"
    Then I verify that I am on the Detailed Property Listing page

  Scenario: Go to Contact seller page from Detailed Property Listing page
    Given I am on the Detailed Property Listing page
    When I click on contact seller
    Then I verify that I am on the Contact Seller page

  Scenario: Go to Email Verification page from Contact Seller Page
    Given I am on the Contact Seller page
    And enter the number "" and email id ""
    And click on Send Email
    Then I verify that I am on the Email Verification page

  Scenario: Go to Seller Details page from Email Verification page
    Given I am on the Email Verification page
    And I click on Skip verification
    Then I verify that I am on the Seller Details page

  Scenario: Go to Detailed Property page from Seller Details page
    Given I am on the Seller Details page
    And I close the Seller details page
    Then I verify that I am on the Detailed Property Listing page
