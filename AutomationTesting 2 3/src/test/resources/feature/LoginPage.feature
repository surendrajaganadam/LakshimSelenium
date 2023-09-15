Feature: Validate login fucntioanlity
Scenario: verify login with valid credentails
Given user is on sauceD login screen
When user Enters name nad password
Then click on login button
And add product to te crt adn navigate to cart screen
Then perform chckout operation
And fill the form and complete the order
Then navigate back to hme and logout from the app