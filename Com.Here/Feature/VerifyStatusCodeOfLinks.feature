Feature: Verifying all the links present on current page are working

  Scenario: Verify the status of all links present on the current page
    Given I navigate to the application url
    When I find all the links present on the current webpage
    And I validate status of all the links present on current page
    Then I close the browser
 