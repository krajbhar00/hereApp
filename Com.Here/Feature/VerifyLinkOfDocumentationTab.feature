Feature: Verifying the links in ducumentation tab are working


  Scenario: Verify the page loaded and angular is initialized for documentation tab links
    Given I navigate to the application URL
    When I validate the internal links of Documentation tab
    Then I check the page load and anglur is initialized for the links 
    And I quit the browser
    
    
    




