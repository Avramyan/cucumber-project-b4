Feature: Google Search Functionality Title Verification
  User Story: As user, when I am on a Google search page
  I should be able to search whatever I want and see the relevant information

  @google_search @AA_smoke
  Scenario: Search functionality result title verification
    Given user in on Google search page
    When user types Loop Academy in the google search box and clicks enter
    Then user should see Loop Academy - Google search in the google title

  @google_search @AA_smoke
  Scenario: Search functionality result title verification
    Given user in on Google search page
    When user types "Loop academy" in google searchBox and clicks enter
    Then user should see "Loop academy - Google Search" in the google title