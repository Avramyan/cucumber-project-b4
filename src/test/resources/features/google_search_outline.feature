Feature: Scenario Outline Practice

  @google_search_outline
  Scenario Outline:
    Given user in on Google search page
    When user search for "<country>"
    Then user should see the "<capital>" in the results

    Examples:
      | country     | capital    |
      | Azerbaijan  | Baku       |
      | Ukraine     | Kyiv       |
      | Afghanistan | Kabul      |
      | USA         | Washington, D.C. |
      | Turkiye     | Ankara     |
      | Uzbekistan  | Tashkent   |
      | Georgia     | Tbilisi    |
