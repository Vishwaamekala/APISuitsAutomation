@suitphase3
Feature: Validate all end point

  Background:  Setup domain for suitshealthdev
    Given set the base domain "https://suitsbackenddevelopment.azurewebsites.net/api/v1"

    # All product related

  Scenario: As user i can search for products and features
    Given As user i can search for differnet  products with application service end point "searchProduct"
    When  I validate response with message "Successfully fetched data"
    Then I will receive valid response 200



  Scenario: As user  i can change product status
    Given As user i can update products with application service end point "searchProduct"
    When  I validate response with message "Successfully fetched data"
    Then I will receive valid response 200


















  # mobile api automation test cases
#  #
#    # **************** start doctor sign  with valid and invalid data ***********************
#  # Modify email, mobile number and Licence number in json file. Post method
#  Scenario: Doctor Signup with validate data with Successfully registered
#    Given  I Signup new Doctor details with "Valid data" application service end point "doctorSignup"
#    When I will receive valid response 200
#    Then I validate suessfully message "Succesfully Signed Up Doctor"
#
#  Scenario: Doctor Signup with invalid - Verify error message Email address already exists
#    Given I Signup new Doctor details with "Existing email address" application service end point "doctorSignup"
#    When I will receive valid response 500
#    Then I Verify response Message key "Something went wrong. Please try again later."
#    And I Verify  response error key "Email is already registered"
#
#    #in this step we change the new random email id in the newdoctorsignup_existingphonenumber.json
##  Scenario: Doctor Signup with invalid - Verify error message Phone number already exists
##    Given I Signup new Doctor details with "Existing Phone Number" application service end point "doctorSignup"
##    When I will receive valid response 500
##    Then I Verify response Message key "Something went wrong. Please try again later."
##    Then I Verify response Message key "Phone number already exists"
#
#  Scenario:Doctor Signup with invalid - Verify something went wrong please try again
#    Given I Signup new Doctor details with "Invalid data" application service end point "doctorSignup"
#    When I will receive valid response 500
#    Then I Verify response Message key "Something went wrong. Please try again later."
#    And I Verify  response error key "Password must be a combination of letters, numbers, cases, and symbols with a minimum length of 10 characters"
#    #***************** End of Signup ********************************
#
#  #*********************** start Change password with valid and invalid ********************
#  Scenario: Doctor change password with valid data with Successfully changed Password
#    Given I change password of existing doctor id with "Valid password" application service end point "updatePassword"
#    When I will receive valid response 200
#    Then I validate suessfully message "Successfully changed Password"
#
#  Scenario: Doctor change password wtih invalid password
#    Given I change password of existing doctor id with "Valid password" application service end point "updatePassword"
#    When I will receive valid response 200
#    Then I validate suessfully message "Successfully changed Password"
#
#  Scenario: Doctor change password -verify doctor does not exists
#    Given I change password of not existing doctor id  application service end point "updatePassword"
#    When I will receive valid response 200
#    Then I validate suessfully message "Doctor doesn't exist"
#
#    #************************ End of Change password *****************************
#
#    #************************** update existing  doctor details valid and invalid *********************
#  Scenario:  Update doctor details - All valid data
#    Given  I update existing doctor details with application service end point "updateDoctorDetails"
#    When I will receive valid response 200
##    "Successfully Updated Details"
#
#  Scenario: Delete doctor details- All valid data
##    Given I delete existing doctor details with application service end point"
#
#  #************************* End of update existing doctor details***********************************
#
## Excel Reference /matchingDoctorDetails?email&phoneno - 6th row
#  Scenario:  Get doctor details with email and phone number
#    Given I search for doctor details with application service end point "matchingDoctorDetails?email=rajesab@cirruslabs.io&phoneno=7788665544"
#    When I will receive valid response 200
#
#  Scenario: Get doctor details with token
#    Given I search for doctor details with token id with application service end point "getdoctorDetailswithToken"
##    When I will receive valid response 200
#
#  Scenario: Get doctor details with specialization
#    Given I search for doctor details with specialization by application service end point "getDoctorDetails?spec_id=1"
#    Then I will receive valid response 200
#    When I validate the response with message "Successfully Fetched data"
#
#    #/specializations
#  #https://suitsbackenddevelopment.azurewebsites.net/router/specializations
#  Scenario: Get all list of specializations
##    Given I list of all specializations with application service end point "specializations"
#    Then I will receive valid response 200
#    When I validate the response with message "Successfully Fetched data"
#
#  Scenario:  Get Doctor details
#    Given I Get Doctor details with application service end point "getDoctorDetails?spec_id=1"
#    When I will receive valid response 200
#    When I validate the response with message "Successfully Fetched data"
#    And I validate the doctor details "test11@gmail.com" "Dr AutomationSelenium"
#
#  Scenario:  Get Specification list
#    Given  I Get list specification of treatment with application service end point "getSpecializations"
#    When I validate the response with message "Successfully fetched specializations"
#    Then I will receive valid response
#
##    /getAllAppointments   - row 20
#  Scenario:  Get all appointments
#    Given I get list of appointments with application service end point "getAllAppointments"
#
#
#    # getDoctorSchedule?doc_id=1  - row 21
#  Scenario:  Get Doctor Schedule
#    Given I Get List of Doctor appoint schedule  with application service end point "getDoctorSchedule?doc_id=1"
#    When I validate the response with message "Successfully Fetched Data"
#    Then I will receive valid response
#
#
#  #/getNewRequests?page=1 - row 24
#  Scenario: Get all new requests page wise
#
#  #  /getAppointment?mode   - row 25
#  Scenario: Get Appoint booking mode like  ONLINE or OFFLINE.
#
#  #  /getUserAppointments?user_id (not being used) - row 26
#
#  # /getSlotsByDay?doc_id&day - row 27
#  Scenario: Get slot by day and doctor id
#    Given I Get List available slots with particular doctor id with application service end point "getSlotsByDay?doc_id=102&day=tue"
#    Then I will receive valid response 200
#
#  # /timeSlot row 28
#  Scenario: Set the slots
#    Given As user i need set time slots with application service end point "timeslot"
#    When I will receive valid response 200
#
#
#  # /getTimeSlots row 29
#  Scenario: Get  get time slots
#    Given As user i need to get time slots with application service end point "getTimeSlots"
#    When I will receive valid response 200
#    When I validate the response with message "Successfully fetched Time Slots"
#
#  # /doctorLogin /excel row 30
#  Scenario:  Get doctor login application
#    Given As Doctor i login application with application service end point "doctorLogin"
#    When I will receive valid response 200
#    And  I validate the response with message "Successfully logged in!"
#    Then I validate the token id key received
#
#
#     # /getOneDoctorPerSpecialization - /excel row 31
#  Scenario:  Get One Doctor Per Specialization
#    Given I Get Doctor Specialization with application service end point "getOneDoctorPerSpecialization"
#    When   I validate the response with message "Successfully Fetched Data"
#    Then I will receive valid response 200
#
#
#
#
