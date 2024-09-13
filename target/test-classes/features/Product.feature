@Jatango_ProductCreatePage
Feature: Spring 2 Jatango project scenario-2
  Scenario: Verify whether User is able to access the application
    Given Enter application URL in address bar
    When  Enter Username
    Then  Enter Password
    And   Click Sing_In
    Then User should be redirected to the homepage

  Scenario: Successfully create a new product
    Given User on the product creation page
    When User fill in the product details with valid information
    And User submit the product creation form
    And the new product should be listed in the product inventory

