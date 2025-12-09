@webdriveruniversityOne
Feature: WebdriverUniversity.com - Contact Us Page

  Scenario: Valid Contact Us Form Submission
    Given navigate to hotelbooker application
    When enter username and password
    And click login button
    Then validate successful login
    When Select the client "Test QA Client(Sabre)"
    And Click on Tools Link
    And Select Agency Admin Link
    And click on Travel Policy Tab
    And Disable Travel Policy
    And Click on update button
    Then Validate Travel Policy should be disabled successfully
    And Click on Search Button

  Scenario: Valid New Contact Us Form Submission
    Given navigate to hotelbooker application
    When enter username and password
    And click login button
    Then validate successful login
    When Select the client "Test QA Client(Sabre)"
    And Click on Tools Link
    And Select Agency Admin Link
    And click on Travel Policy Tab
    And Disable Travel Policy
    And Click on update button
    Then Validate Travel Policy should be disabled successfully
    And Click on Search Button