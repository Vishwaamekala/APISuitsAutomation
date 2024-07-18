Feature: Validate all end point

  Background:  Setup domain for suitshealthdev
    Given set the base domain "https://suitsbackenddevelopment.azurewebsites.net/router/"

  Scenario: create new user with Signup end point
    Given  I set post new signup service end point "signup"
    Then I receive valid response

  Scenario:  sign inc with existing user
    Given  I set post new signin service end point "signin"
    Then I receive valid response

  Scenario:  sign with existing user
    Given  I set post addbloodrequestio service end point "bloodrequisition"
    Then I receive valid response



#  Scenario: sign with existing user
#  Given I set get getProductList service and end point "getProductList?limit=1&offset=0"
#  Then I receive valid response



#  #https://suitsbackenddevelopment.azurewebsites.net/router/signup
#  Scenario: Create new user with signup end point
#    Given as user connect to domain "https://suitsbackenddevelopment.azurewebsites.net/router/"
#    And  provide user details and user connect to sign up end point "/signup"
#    Then  Validate the status code 201
#
#
#
#
#
#    Scenario Outline:  Validate all end points
#      Given  as user connect to domain "https://suitsbackenddevelopment.azurewebsites.net/router/"
#      And as user provide new signup details "<js
