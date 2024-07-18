Feature: Validate all end point

  Background:  Setup domain for suitshealthdev
    Given set the base domain "https://suitsbackenddevelopment.azurewebsites.net/router/"

  # mobile api automation test cases

#  "https://suitsbackenddevelopment.azurewebsites.net/router/signin",
  Scenario: User able to signin with userid and password
    Given I set post to signin appliction service end point "signin"
    Then I receive valid response
    And I verify response mobile "9951991196" name "Vishwaa" message "Successfully Logged In"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/signup",
  Scenario:  A Create new user with Signup end point
    Given  I set post new signup service end point "signup"
    Then I receive valid response
    And I verify message for new user signup "Successfully registered"
#  "https://suitsbackenddevelopment.azurewebsites.net/router/changepassword",
    #idv value not displayed in signup we are unable do change password
#  Scenario: B Existing user able to reset existing password
#    Given I set post reset existing password service end point "changepassword"
#    Then I receive valid response
#    And I verify message for reset password "Successfully registered"
#  "https://suitsbackenddevelopment.azurewebsites.net/router/changeforgotpassword",
  Scenario: Existing user able to forgot existing password
    Given I set post to forgot password service end point "changeforgotpassword"
    Then I receive valid response
    And I verify message for change forgot password "Successfully Updated"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/bloodrequisition",
  Scenario: get add blood requestion
    Given  I set post addbloodrequestio service end point "bloodrequisition"
    Then I receive valid response


#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateprofile",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/searchblooddonor",
  Scenario:  Validate API endpoint for search for blood donor
    Given I get search for blood donor service end point "searchblooddonor"
    Then I receive valid response
    And I verify message for getbloodbanklist "Successfully Searched"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/searchbloodbank",
  Scenario:  Validate API endpoint for search blood bank based on
    Given I get list of blood bank with search condition service end point "searchbloodbank"
    Then I receive valid response
    And I verify message for getbloodbanklist "Successfully Searched"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/addemergencycontact",
  Scenario:  Validate API endpoint for add emergency contact details
    Given I set post add new emergency contact service end point "addemergencycontact"
    Then I receive valid response
    And I verify message for addemergencycontact "Successfully Added"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateemergencycontact",
  Scenario:  Validate API endpoint for update emergency contact details
    Given I set post update new emergency contact service end point "updateemergencycontact"
    And I verify message for updateemergencycontact "Successfully Updated"
    Then I receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getemergencylist",
  Scenario:  Validate API endpoint for get emergency contact details
    Given I set get emergency contact list service end point "getemergencycontact"
    Then I receive valid response
    And I verify message for getemergencycontact "Successfully Searched"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/sendEmergencyAlert",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/checkmobile",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/gettheme",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/gethospitals",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getbloodbanks",
  Scenario:  Validate API endpoint for get Blood Bank list
    Given I get list of blood bank service end point "getbloodbanks"
    Then I receive valid response
    And I verify message for getbloodbanklist "Successfully Searched"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getcareteam",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getOrder",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getbloodbanks",
  # Repeated scenario below
  Scenario:  Validate API endpoint for get Blood Bank list
    Given I get list of blood bank service end point "getbloodbanks"
    Then I receive valid response
    And I verify message for getbloodbanklist "Successfully Searched"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getProduct?id=1",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getProductList?limit=1&offset=0",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getSaveyoDetails",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/profile",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateOrderStatus",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertPrescription",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertProduct",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/placeOrder",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getPrescription",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getSecurityCode?venueDate=2023-04-19",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getPrescriptionDetails?id=1",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getOrderDetails?order_id=1",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getCategoryProducts?category_id=1",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getBloodDonationDetails?limit=1&offset=0",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getAllCustomerAddress",
  Scenario:  list out all customer address details
    Given I get list of of all customer  with service end point "getAllCustomerAddress"
    Then I receive valid response


#  "https://suitsbackenddevelopment.azurewebsites.net/router/getCustomerAddress",
    # no id as parameter
    Given I get list of of all customer  with service end point "getCustomerAddress"
    Then I receive valid response


#  "https://suitsbackenddevelopment.azurewebsites.net/router/deleteCustomerAddress",
  # ** for delete an existing customer  we need return the userid and id from insertCustomerAddress
  Scenario: delete exsting customer details with id and user_id
    Given I delete existingcustomer details with service end point "deleteCustomerAddress"
    And I verify message for delete existing customer address "Address deleted successfully."
    Then I receive valid response
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getlatlong",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getCategoriesList",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertCustomerAddress",
  # ******* found an issue every where the key "Message" but for this end point "message"
  Scenario: Insert new customer details
    Given I insert new customer details with service end point "insertCustomerAddress"
    Then I receive valid response
    And I verify message for insert new customer address "Address inserted successfully."
#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateCustomerAddress",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateCustomerAddress",
   # ** for update an existing customer  we need return the userid and id from insertCustomerAddress
  Scenario: Update exsting customer details with id and user_id
    Given I update customer details with service end point "updateCustomerAddress"
    Then I receive valid response
    And I verify message for update existing customer address "Address updated successfully."

#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertCategory",




  #
#
#
#  Scenario: validate the list with approved status for end point blood req approved
#    Given I get list of blood requestion with status service end point "approvedbloodreq/1"
#    Then I receive valid response
#    And I validate "Approved" status with YES
#
#  Scenario: validate the list with rejected status for end point blood req rejected
#    Given I get list of blood requestion with status service end point "rejectedbloodreq/1"
#    Then I receive valid response
#    And I validate "Rejected" status with YES
#
#
#  Scenario: validate the list with blood req with page navigation for end point blood req pagination
#    Given I get list of blood requestion with status service end point "bloodreq/10"
#    Then I receive valid response
#
#  Scenario: validate the data for update approved blood req
#    Given  I set post addbloodrequestio service end point "bloodrequisition"
#    Then I receive valid response
#    And I validate the Approved status to "null"
#    And I set post update blood requisition status "Approved" to Yes
#
#  Scenario: validate the data for update Rejecct blood req
#    Given  I set post addbloodrequestio service end point "bloodrequisition"
#    Then I receive valid response
#    And I validate the Approved status to "null"
#    And I set post update blood requisition status "Approved" to Yes
#    And I set post update blood requisition status "Rejected" to Yes
#
#  Scenario:  Validate API endpoint for Add Blood Bank
#    Given  I set post add new blood bank service end point "addbloodbank"
#    Then I receive valid response
#    Given I set post block blood bank service end point "blockbloodbank"
#    Then I receive valid response
#    Then I validate "Successfully Blocked" message
#    Given I set post unblock blood bank server end point "unblockbloodbank"
#    Then I receive valid response
#    Then I validate unblock message "Successfully Unblocked"
#


