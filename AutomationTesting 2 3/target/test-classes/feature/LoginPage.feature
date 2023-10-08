Feature: Validate login file

Scenario: Verify Saucedemo login with valid credentials
Given user is on saucedemo login screen
When user enters credentials
Then user clicks on login button
And user verifies homepage


Scenario: Validate loginfile with invalid username
Given user is on saucedemo login screen
When user enters wrong username
When user enters correct password
Then user clicks on login button
And user verifies homepage

Scenario: Validate loginfile with invalid password
Given user is on saucedemo login screen
When user enters correct username
When user enters wrong password
Then user clicks on login button
And user verifies homepage

Scenario: Validate loginfile with invalid credentials
Given user is on saucedemo login screen
When user enters wrong username
When user enters wrong password
Then user clicks on login button
And user verifies homepage

Scenario: Validate loginfile with No credentials
Given user is on saucedemo login screen
When user leaves username blank
When user leaves password blank
Then user clicks on login button
And user verifies homepage

Scenario: Validate loginfile with invalid username
Given user is on saucedemo login screen
When user enters wrong username
When user enters correct password
Then user clicks on login button
And user verifies homepage




