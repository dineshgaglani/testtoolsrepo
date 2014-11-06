Feature: Contact seller via search results page

  Scenario: Go to Search Results page from home page
    Given I navigate to the roofandfloor homepage
    When I search for property in area "Thiruvanmiyur"
    Then I verify that I am on the Search Results page

  Scenario: Go to detailed property listing page from Search Results page
    Given I am on the Search Results page
    And I click on property at index "1"
    Then I verify that I am on the Detailed Property Listing page

  Scenario: Go to Contact seller page from detailed property listing page
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

  Scenario: Go to Search Results page from Seller Details page
    Given I am on the Seller Details page
    And I close the Seller details page
    Then I verify that I am on the Search Results page
