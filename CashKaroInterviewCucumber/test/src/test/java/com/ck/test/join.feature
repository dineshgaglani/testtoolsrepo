Feature: Joining

Scenario: Join Free Now page navigation check from top bar
	When I navigate to homepage
	And I click on Join Free on Cashback section
	Then I verify that I am on the Join Free Now page


Scenario: Join Free Now page navigation check from home page 
	When I navigate to homepage
	And I click on Join Free on Top Bar section
	Then I verify that I am on the Join Free Now page

Scenario: 
	When I navigate to Join Now page
	And I enter "Dinesh" in full name
	And I enter "dinesh_gaglani@hotmail.com" in email address
	And I enter "dinesh_gaglani@hotmail.com" in confirm email
	And I enter "password123" in password


Scenario: Join using facebook on Join Free Now page
	Given I am logged in my facebook account
	When I navigate to Join Now page
	And I click on Join With Facebook
	Then I verify that the login with facebook page popsup
	
Scenario: Join using facebook on Login page
	Given I am logged in my facebook account
	When I navigate to Join Now page
	And I click on Sign In
	Then I verify that the login dialog pops up
	And I click on Join With Facebook on login dialog
	Then I verify that the login with facebook page popsup



