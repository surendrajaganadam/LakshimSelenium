Feature: Validate E2E saucedemo order process

Background:
Given user is on saucedemo login screen
When user enters credentials
Then user clicks on login button
And user verifies homepage


Scenario: Verify the process for placing an order
Given User is on saucedemo inventory page
Then select products to buy
And add selected products to cart
Then go to cart to verify the products
And click on checkout
Then goto checkout screen
And enter the details
Then proceed with the order
And confirm the order
Then press back the homepage


