@RegressionSuite
Feature: Validate all end point

  Background:  Setup domain for suitshealthdev
    Given set the base domain "https://suitsbackenddevelopment.azurewebsites.net/api/v1/"

  # mobile api automation test cases
#https://suitsbackenddevelopment.azurewebsites.net/api/v1/doctorSignup
  Scenario: Doctor Signup with validate data with Successfully registered
    Given  I Signup new Doctor details with "Valid data" application service end point "doctorSignup"
    When I will receive valid response 200
    Then I validate suessfully message "Successfully registered."

  Scenario: Verify error message Email address already exists
    Given I Signup new Doctor details with "Existing email address" application service end point "doctorSignup"
    When I will receive valid response 200
    Then I Verify response Message key "Phone number already exists"

  Scenario: Verify error message Phone number already exists
    Given I Signup new Doctor details with "Existing Phone Number" application service end point "doctorSignup"
    When I will receive valid response 200
    Then I Verify response Message key "Phone number already exists"

  Scenario: Verify something went wrong please try again
    Given I Signup new Doctor details with "Invalid data" application service end point "doctorSignup"
    When I will receive valid response 200
    Then I Verify response Message key "Something went wrong. Please try again later."
    And I Verify  response error key "Please fill all Mandatory Details"

#GET https://suitsbackenddevelopment.azurewebsites.net/router/getDoctorDetails?spec_id=1
  Scenario:  Get Doctor details
    Given I Get Doctor details with application service end point "DoctorDetails?spec_id="
    When I validate the response with message "Successfully Fetched data"
    #Then I will receive valid response

#https://suitsbackenddevelopment.azurewebsites.net/router/getSpecializations
  Scenario:  Get Specification list
    Given  I Get list specification of treatment with application service end point "getSpecializations"
    When I validate the response with message "Successfully fetched specializations"
    Then I will receive valid response

# GET https://suitsbackenddevelopment.azurewebsites.net/router/getDoctorSchedule?doc_id=1
  Scenario:  Get Doctor Schedule
    Given I Get List of Doctor appoint schedule  with application service end point "getDoctorSchedule?doc_id=1"
    When   I validate the response with message "Successfully Fetched Data"
    Then I will receive valid response

#https://suitsbackenddevelopment.azurewebsites.net/router/getOneDoctorPerSpecialization
  Scenario:  Get One Doctor Per Specialization
    Given I Get Doctor Specialization with application service end point "getOneDoctorPerSpecialization"
    When   I validate the response with message "Successfully Fetched Data"
    Then I will receive valid response

# POST https://suitsbackenddevelopment.azurewebsites.net/api/v1/appointment
  Scenario: Create a doctor new appointment
    Given Get doctor appointment to the patient "appointment"
    Then I will receive valid response 200
    When I validate the response with message "Appointment booked succesfully"

# GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAllAppointments
    Scenario: Get all appointments
    Given Get all doctor appointments "getAllAppointments"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"

# GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getDoctorAppointments
  #Need to look
  Scenario:  Get appointment list for doctors
    Given Get all appointment list for doctors "getDoctorAppointments"
     Then I will receive valid response 200
     When I validate the response with message "Appointments fetched succesfully"

# GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getDoctorAppointmentsbyMode?mode=ONLINE

  Scenario: Get appointment list for a doctor by online mode
    #Need to look
   Given Get appointment list for a doctor by Online mode "getDoctorAppointmentsbyMode?mode=ONLINE"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getNewRequests?page=1
  Scenario: Get new appointment requests list for a doctor
    Given Get new appointment request list for a doctor "getNewRequests?page=1"
    Then I will receive valid response 200
    When I validate the response with message "Appointment Requests fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getUpcomingAppointments?page=1
  Scenario: Get upcoming appointment from doctor
    Given Get upcoming appointment from doctor "getUpcomingAppointments?page=1"
    Then I will receive valid response 200
    When I validate the response with message "Appointment Requests fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getUpcomingOnlineAppointments?page=1
  Scenario: Get doctor appointment for online
    Given Get upcoming appointment for online "getUpcomingOnlineAppointments?page=1"
    Then I will receive valid response 200
    When I validate the response with message "Appointment Requests fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAppointmentHistory?page=1
  Scenario: Get app history
    Given Get app history "getAppointmentHistory?page=1"
    Then I will receive valid response 200
    When I validate the response with message "Appointment Requests fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getOnlineAppointmentHistory?page=1
  Scenario: Get app history online
    Given Get app history "getOnlineAppointmentHistory?page=1"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getOfflineAppointmentHistory?page=1
  Scenario: Get app history offline
    Given Get app history "getOfflineAppointmentHistory?page=2"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAlldoctorsDetails/1
  Scenario: Get all doctor details
    Given get all doctor details "getAlldoctorsDetails/1"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched data"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getUserAppointmentHistory
  Scenario: Get appointment list for the users
    Given Get appointment list for the users "getUserAppointmentHistory"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"

#GET https://suitsbackenddevelopment.azurewebsites.net/api/v1/getUserUpcomingAppointments
  Scenario: Get appointment upcoming list for users
    Given Get appointment upcoming list for the users "getUserUpcomingAppointments"
    Then I will receive valid response 200
    When I validate the response with message "Appointments fetched succesfully"


















  # old  End  points ############################################################
#  "https://suitsbackenddevelopment.azurewebsites.net/router/signin",
  Scenario: User able to signin with userid and password
    Given I set post to signin appliction service end point "signin"
    When I verify response mobile "9951991196" name "vishwaa" message "Successfully Logged In" displayed
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/signup",
  Scenario:  A Create new user with Signup end point
    Given  I set post new signup service end point "signup"
    When I verify message for new user signup "Successfully registered"
    Then I will receive valid response
#  "https://suitsbackenddevelopment.azurewebsites.net/router/changepassword",
    #idv value not displayed in signup we are unable do change password
#  Scenario: B Existing user able to reset existing password
#    Given I set post reset existing password service end point "changepassword"
#    Then I receive valid response
#    And I verify message for reset password "Successfully registered"

#  "https://suitsbackenddevelopment.azurewebsites.net/router/changeforgotpassword",
  Scenario: Existing user able to forgot existing password
    Given I set post to forgot password service end point "changeforgotpassword"
    When I verify message for change forgot password "Successfully Updated"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/bloodrequisition",
  Scenario: get add blood requestion request
    Given  I set post addbloodrequestio service end point "bloodrequisition"
    When I verify message for blood requisition "Successfully Created"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateprofile",
  Scenario: Verify update profile of the user
    Given I set update profile for the user and the service end point "updateprofile"
    When I verify the message for updateprofile user as "Successfully Updated"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/searchblooddonor",
  Scenario:  Validate API endpoint for search for blood donor
    Given I get search for blood donor service end point "searchblooddonor"
    When I verify message for getbloodbanklist "Successfully Searched"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/searchbloodbank",
  Scenario:  Validate API endpoint for search blood bank
    Given I get list of blood bank with search condition service end point "searchbloodbank"
    When I verify message for getbloodbanklist "Successfully Searched"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/addemergencycontact",
  Scenario:  Validate API endpoint for add emergency contact details
    Given I set post add new emergency contact service end point "addemergencycontact"
    When I verify message for addemergencycontact "Successfully Added"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateemergencycontact",
  Scenario:  Validate API endpoint for update emergency contact details
    Given I set post update new emergency contact service end point "updateemergencycontact"
    When I verify message for updateemergencycontact "Successfully Updated"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getemergencylist",
#  Scenario: Validate API endpoint for emergency contacts details
#    Given I set get emergency contact list service end point "getemergencylist"
#    #When I verify message for getemergencylist "Successfully Searched"
#    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/sendEmergencyAlert",
  Scenario: Validate emergency alert to the user
    Given I send emergency alert by service end point "sendEmergencyAlert"
    When I verify the message for sendEmergencyAlert "Emergency alert Sent Successfully"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/checkmobile",
  Scenario: Validate check mobile number exists
    Given I get list of of all customer with service end point "getAllCustomerAddress"
    Given I send alert by service end point "checkmobile"
    When I verify the message for checkmobile "Mobile number already in use"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/gettheme",
  Scenario: To fetch the current theme settings
    Given I sent fetch by service end point "gettheme"
    When I verify the message for gettheme "Succesfully fetched data"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/gethospitals",
  Scenario: To fetch the hospital list
    Given I sent alert by service end point "gethospitals"
    When I verify the message for gethospitals "Successfully Searched"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getcareteam",
  # There is no body to post method for this API

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getOrder",
  Scenario: To get inserted order details
    Given I get list of order details from service end point "getOrder"
    Then I will receive valid response


  # https://suitsbackenddevelopment.azurewebsites.net/router/getbloodbanks
  Scenario:  Validate API endpoint for get Blood Bank list
    Given I get list of blood bank service end point "getbloodbanks"
    When I verify message for getbloodbanklist "Successfully Searched"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getProduct?id=1",
  #pass the same id deails in 117 and 118
  Scenario:  validate API endpoint for get product by id
    Given  I get product details by id service end point "getProduct?id=1"
    When I verify the product "1" details from the response.
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getProductList?limit=1&offset=0",
  Scenario:  validate API endpoint for get productlist with limit and offset parameters
    Given  I get product details by id service end point "getProductList?limit=1&offset=0"
    When I verify the limit "1"  and offset "0" details from the response.
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getSaveyoDetails",
  Scenario: Get saveyo details from the service endpoint
    Given I get savyo details list from service end point "getSaveyoDetails"
    Then I will receive valid response
#  "https://suitsbackenddevelopment.azurewebsites.net/router/profile",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateOrderStatus",
  Scenario: User update the order status
    Given User able to update the order "updateOrderStatus"
    When I verify message for updateOrderStatus "Order status updated successfully."
    Then I will receive valid response

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
    Given I get list of of all customer with service end point "getAllCustomerAddress"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/getCustomerAddress",
    # no id as parameter
  Scenario: To get all customer address of the user
    Given I get list of of all customer  with service end point "getCustomerAddress"
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/deleteCustomerAddress",
  # ** for delete an existing customer  we need return the userid and id from insertCustomerAddress
  Scenario: delete exsting customer details with id and user_id
    Given I delete existingcustomer details with service end point "deleteCustomerAddress"
    When I verify message for delete existing customer address "Address deleted successfully."
    Then I will receive valid response
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getlatlong",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/emergencylist",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/getCategoriesList",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertCustomerAddress",
  # ******* found an issue every where the key "Message" but for this end point "message"
  Scenario: Insert new customer details
    Given I insert new customer details with service end point "insertCustomerAddress"
    When I verify message for insert new customer address "Address inserted successfully."
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateCustomerAddress",
#  "https://suitsbackenddevelopment.azurewebsites.net/router/updateCustomerAddress",
   # ** for update an existing customer  we need return the userid and id from insertCustomerAddress
  Scenario: Update exsting customer details with id and user_id
    Given I update customer details with service end point "updateCustomerAddress"
    When I verify message for update existing customer address "Address updated successfully."
    Then I will receive valid response

#  "https://suitsbackenddevelopment.azurewebsites.net/router/insertCategory",

#   https://suitsbackenddevelopment.azurewebsites.net/router/getlatlong
  Scenario: To get user address with latitude and longitude list
    Given I get list of latitude and logutide details with service end point "getlatlong"
    Then I will receive valid response

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


