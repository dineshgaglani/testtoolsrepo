Feature: Stock price api comparison

Scenario: Compare AAPL and AMZN stock price
    When I query for "AMZN" and "AAPL"
    Then I validate the price of "AMZN" is higher than that of "AAPL"