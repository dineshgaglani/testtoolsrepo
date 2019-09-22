Feature: cnn search

  Scenario: Go to valid search results page from homepage
    Given I navigate to the cnn homepage
    Then I validate all the sections in the sections ribbon
    When I search for "machine learning"
    Then I see results "1-10" for "machine learning" on search results page
    Then I validate total results are more than "30"
    Then I close the browser

  Scenario: Go to invalid search results page from homepage
    Given I navigate to the cnn homepage
    When I search for "3d172d51-6621-42e3-9325-6447b3fef512"
    Then I see no results
    Then I close the browser

  Scenario: Go directly search results page
    When I navigate to cnn search results page "2" for "machine learning"
    Then I see results "11-20" for "machine learning" on search results page
    Then I close the browser

  Scenario: Search results should sort by date when sorting criteria is date
    When I navigate to cnn search results page "2" for "machine learning"
    Then I see results "11-20" for "machine learning" on search results page
    When I change the sorting criteria to "Date"
    Then I see results "1-10" for "machine learning" on search results page
    Then I should the result at index "1" should be newer than the result at "2"
    Then I close the browser