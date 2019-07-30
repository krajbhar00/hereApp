Feature: Verifying the links in ducumentation tab are working


  Scenario: Verify the page loaded and angular is initialized for documentation tab links
    Given I navigate to the application URL
    When I validate the internal links of Documentation tab
    Then I wait to load the page and anglur is initialized
    And I quit the browser
    
    
    




