Feature: Login

Scenario: Valid login check
	When I navigate to homepage
	And I click on Sign In on top bar
	Then I verify that the login dialog pops up
	And I enter "goxodzh@sharklasers.com" in Email on login dialog
	And I enter "password123" in Password on login dialog
	And I click on SignIn on login dialog
	Then I verify I am logged in


Scenario: Invalid login check
	When I navigate to homepage
	And I click on Sign In on top bar
	Then I verify that the login dialog pops up
	And I enter "goxodzh@sharklasers.com" in Email on login dialog
	And I enter "password12" in Password on login dialog
	And I click on SignIn Invalid on login dialog
	Then I verify I the Invalid Login Message appears on the login dialog
	



