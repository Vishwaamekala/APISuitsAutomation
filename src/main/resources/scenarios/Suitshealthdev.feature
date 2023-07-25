Feature:  Validate login page of Suitehealthdev


@test1
  Scenario: Validate login process
    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
    When As a user i need enter "admin@suitsadmin.com"  "Admin@123"  and click on login button
    Then As a user i need to verify landingPage


#  Scenario: Verify the MasterData cateogory from dashboard
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com"  "Admin@123"  and click on login button
#    Then As user i need verify "MasterData" menu item and click
#    And As user i need verify MasterData details
#    And Add rows more then 50 to the lis
#    And validate Total Row count Per Page 50
#    And validate  Navigate Next Page verify page number
#    And validate Navigate First Page verify page number
#    And validate  Navigate Last Page verify page number
#    And validate Navigate Previous Page verify page number

#    Scenario: Verify the MasterData menu from maserdata category
#      Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#      When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#      And  Click on login button
#      Then As user i need verify "MasterData" menu item and click
#      And Verify and click on "+MasterData" button
#      And validate  State District city drop list
#
#  Scenario:  Verify Add State functionality from Masterdata menu
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#    And  Click on login button
#    Then As user i need verify "MasterData" menu item and click
#    And Verify and click on "+MasterData" button
#    And validate and I select 1 item from list State District city drop list
#
#  Scenario: Verify added state is showing up in Blood Bank services of suits
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#    And  Click on login button
#    And validate and expand Blood bank and click on  AllBloodBank
#    And validate and click on button +BloodBank
#    And Get list of state from AddBloodBank  Page
#    And As user i need verify "MasterData" menu item and click
#    And As user add new state not from the currect list
#    And validate and expand Blood bank and click on  AllBloodBank
#    And validate and click on button +BloodBank
#    And verify new state display from the list
#
#
#  Scenario: Verify added state is showing up in Network Hospitals services of suits
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#    And  Click on login button
#    And validate and expand Network Hospital and click on  All NetWork Hospital
#    And validate and click on button +NetWork Hospital
#    And Get list of state from Network Hospital Page
#    And As user i need verify "MasterData" menu item and click
#    And As user add new state not from the currect list
#    And validate and expand Network Hospital and click on  All NetWork Hospital
#    And validate and click on button +NetWork Hospital
#    And verify new state display from the list
#
#
#  Scenario: Verify added state is showing up inAmbulance services of suits
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#    And  Click on login button
#    And validate and expand Ambulance and click on  All Ambulance
#    And validate and click on button +Ambulance
#    And Get list of state from Add Ambulance Page
#    And As user i need verify "MasterData" menu item and click
#    And As user add new state not from the currect list
#    And validate and expand Ambulance and click on  All Ambulance
#    And validate and click on button +Ambulance
#    And verify new state display from the list
#
#  Scenario: Verify added state is showing up in S Care Team services of suits
#    Given  As a admin user i need to open the url "https://suitshealthdev.azurewebsites.net/"
#    When As a user i need enter "admin@suitsadmin.com" and "Admin@123"
#    And  Click on login button
#    And validate and expand Ambulance and click on  All Ambulance
#    And validate and click on button +Ambulance
#    And Get list of state from Add Ambulance Page
#    And As user i need verify "MasterData" menu item and click
#    And As user add new state not from the currect list
#    And validate and expand Ambulance and click on  All Ambulance
#    And validate and click on button +Ambulance
#    And verify new state display from the list




