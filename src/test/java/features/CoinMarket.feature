Feature: To verify the Coin Market results on dashboard with different filters

  @FrontendTask1
  Scenario: To verify the list of cryptocurrencies based on dorpdown number criteria
    Given user is on CoinMarket homepage
    When user selects show rows dropdown number as "100"
    Then user should see only "100" number of results got displayed

  @FrontendTask2
  Scenario: To verify the list of cryptocurrencies based on dorpdown number criteria
    Given user is on CoinMarket homepage
    When user selects Filters option and click on "+" Add Filter
    Then user should see an option to filter based on "Market Cap" with market cap range "$1B - $10B"
    And user selects "Price" filter with price range of "$101 - $1,000"
    When user click on apply filter and show results buttons
    Then user should be navigated to home page where the results displayed should be in between applied market cap range