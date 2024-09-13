@Jatango_Login
Feature: Spring 1 Jatango project scenario-1

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

        Scenario: Successfully create a new show
                Given the user is on the shows tab
                When the user navigates to the shows creation page
                And the user fills in the show details with valid information
                And the user submits the show creation form
                And the user adds the product to the show
                And the user copies the show's link
                When the user pastes the link in the browser
                Then the user should see the newly created show page

