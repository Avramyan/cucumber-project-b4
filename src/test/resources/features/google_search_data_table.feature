Feature: Passing multiple parameters to the same step

  @Google_search_data_table @AA_smoke
  Scenario: Searching multiple items
    Given user in on Google search page
    Then user searches the following items
    |loop academy|
    |java        |
    |selenium    |
    |cucumber bdd|
    |sql         |
    |Pavlo       |
    |Cake        |
    And we love loop academy