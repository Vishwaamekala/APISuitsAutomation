@suitphase3
Feature: Validate all end point

  Background:  Setup domain for suitshealthdev
    Given set the base domain "https://suitsbackenddevelopment.azurewebsites.net/api/v1/"

#  PUT	https://suitsbackenddevelopment.azurewebsites.net/router/updateCategory
  Scenario: As user i can update existing category
    Given As user i can update existing category with application service end point "updateCategory"
    Then I will receive valid response 200
    When I validate the response with message "Category details updated successfully."

#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/getProductsByType/general
  Scenario: As user i can get all products by type with general
    Given As user i can get all products by type with application service end point "getProductsByType/general"
    Then I will receive valid response 200
    When I validate the response with message "Products details fetched succesfully."

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/appointment
  Scenario:  As user i can book an appointment with doctor id
    Given As user i can book appointment with doctor id with application service end point "appointment"
    Then I will receive valid response 200
    When I validate the response with message "Appointment booked succesfully"

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/appointmentPayment
  #Need to check
  Scenario: As user i create appointment payment
    Given As user i can payment for appointment payment with application service end point "appointmentPayment"
    Then I will receive valid response 200
    When I validate the response with message "Payment Done successfully"

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/appointmentvalidations
  Scenario: As user i cannot create duplicate appointment
    Given As user i cannot create duplicate appointment with application service end point "appointmentvalidations"
    Then I will receive valid response 200
    When I validate the response with message "No duplicate found"

#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/router/deleteCategory?id=
#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/router/deleteProduct?id=
#  PUT	https://suitsbackenddevelopment.azurewebsites.net/router/updateCategoryStatus
  Scenario: As user i can update the Category status
    Given As user i can update the Category status with application service end point "updateCategoryStatus"
    Then I will receive valid response 200
    When I validate the response with message "Category Status updated successfully."

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/searchCategory
  Scenario: As user i can search category
    Given As user i can search Category status with application service end point "searchCategory"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"


#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/getCategoriesList
  Scenario:  As user i can see all getcategorieslist
    Given As user i can see all existing category with application service end point "getCategoriesList"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched Categories List"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/getCategoryProducts?category_id=3&product_type=General
  Scenario: As user i can see categories list by id
    Given As user i can see all existing category with appliation service end point "getCategoryProducts?category_id=3&product_type=General"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched Products"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/getProductsWithCategories/1
  Scenario:  As user i can get products with categories
    Given As user i can get products with categories with appliation service end point "getProductsWithCategories/1"
    Then I will receive valid response 200
    When I validate the response with message "Products retrieved successfully"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/getSubCategories
  Scenario:  As user i can get subcategories
    Given As user i can get products with categories with appliation service end point "getSubCategories"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/insertSubCategory
  #Once record is inserted we can't reinsert the record
  Scenario:  As user  i can create new subcategory
    Given As user i can create new subcategory with application service end point "insertSubCategory"
    Then I will receive valid response 200
    When I validate the response with message "Sub Category details inserted successfully."

#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/insertLabtestCategory
  # Name should be unique
  Scenario:  As user i can create labtest category
    Given As user i can create new labtest category with application service end point "insertLabtestCategory"
    Then I will receive valid response 200
    When I validate the response with message "Lab Test Category details inserted successfully."


#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/insertLabProvider

#  GET	https://suitsbackenddevelopment.azurewebsites.net/router/checkPincode/522015
  Scenario: As user i can search by pincode for details of service location
    Given As user i can search by pincode for details of service location with application service end point "checkPincode/522015"
    Then I will receive valid response 200
    When I validate the response with message "Pincode details fetched succesfully."

# post  https://suitsbackenddevelopment.azurewebsites.net/router/insertLabtest


#  POST	https://suitsbackenddevelopment.azurewebsites.net/router/insertLabtestPackageTest
  Scenario: As user i can insert new labtest package
    Given As user i can insert new labtest package with application service end point "insertLabtestPackageTest"
    Then I will receive valid response 200
    When I validate the response with message "Lab Test Package Test details inserted successfully."

#  get https://suitsbackenddevelopment.azurewebsites.net/router/getWebLabTestPackageTestsList?package_id=5
  Scenario: As user i can get weblab test package.
    Given As user i can search by pincode for details of service location with application service end point "checkPincode/522015"
    Then I will receive valid response 200
    When I validate the response with message "Pincode details fetched succesfully."

#  POST->Get	https://suitsbackenddevelopment.azurewebsites.net/router/searchProduct
  Scenario: As user i can search for all product
    Given As user i can search for all product with application service end point "searchProduct?search=eno"
    Then I will receive valid response 200
    When I validate the response with message "Products retrieved successfully"

# post https://suitsbackenddevelopment.azurewebsites.net/router/updateProductStatus
  Scenario:  As user i can update product status
    Given As user i can update product status with application service end point "updateProductStatus"
    Then I will receive valid response 200
    When I validate the response with message "Product Status updated successfully."


#    Get https://suitsbackenddevelopment.azurewebsites.net/api/v1/getMedicineTypes
  Scenario: As user i can verify all medicine types
    Given As user i can verify all medicine types with application service end point "getMedicineTypes"
    Then I will receive valid response 200
    When I validate the response with message "Medicine Types fetched succesfully."

#  GET https://suitsbackenddevelopment.azurewebsites.net/router/getCouponTypes
  Scenario: As user i can verify coupon types
    Given As user i can verify coupon types with application service end point "getCouponTypes"
    Then I will receive valid response 200
    When I validate the response with message "Coupon types fetched succesfully."

# POST https://suitsbackenddevelopment.azurewebsites.net/router/insertCoupon
  Scenario: As user i can create new coupon
    Given As user i can insert new coupon with application service end point "insertCoupon"
    Then I will receive valid response 200
    When I validate the response with message "Coupon details inserted successfully."

#    POST https://suitsbackenddevelopment.azurewebsites.net/router/generateCouponCode
  Scenario: As user i can generate coupon code
    Given As user i can generate new coupon code with application service end point "generateCouponCode"
    Then I will receive valid response 200
    When I validate the response with message "Coupon code generated successfully."

#  put https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateCouponStatus?id=1&status=1
  Scenario: As user i can update the coupon status.
    Given As user i can update the existing coupon status with application service end point "updateCouponStatus?id=1&status=1"
    Then I will receive valid response 200
    When I validate the response with message "Coupon status updated successfully."

#  post https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateCouponDetails
  Scenario: As user i can update the coupon details
    Given as user i can update the existing coupon details with application service end point "updateCouponDetails"
    Then I will receive valid response 200
    When I validate the response with message "Coupon details updated successfully."

#  Delete https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateCouponDetails?id=1

#    Get https://suitsbackenddevelopment.azurewebsites.net/api/v1/getOrderDetails?order_id=1
  Scenario:  As user i can verify the order details by order id
    Given As user i can verify the order details by order id with application service end point "getOrderDetails?order_id=2"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched order details"

#  POST - https://suitsbackenddevelopment.azurewebsites.net/api/v1/insertcategory
  Scenario: As user i can insert new category
    Given As user i can insert new category with application service end point "insertcategory"
    Then I will receive valid response 200
    When I validate the response with message "Category details inserted successfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateSubCategory
  Scenario: As user i can update sub category
    Given As user i can insert new sub category with application service end point "updateSubCategory"
    Then I will receive valid response 200
    When I validate the response with message "Sub Category details updated successfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/deleteSubCategory?id=319

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAllLabProviders/1
  Scenario: As user i can get all lab provider details by pageid
    Given As user i can get all lab provider details with application service end point "getAllLabProviders/1"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getLabTests/1
  Scenario: As  user i can get all lab tests details by page
    Given As  user i can get all lab tests details with application service end point "getLabTests/1"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAllLabTestBookings/1
  Scenario: As  user i can get all lab tests booking details by page
    Given As  user i can get all lab tests booking details with application service end point "getAllLabTestBookings/1"
    Then I will receive valid response 200
    When I validate the response with message "Bookings retrieved successfully"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getCategoryLabTestPackages/1?category_id=2
  Scenario: As  user i can get category lab test packages details by page
    Given As  user i can get category lab test packages with application service end point "getCategoryLabTestPackages/1?category_id=2"
    Then I will receive valid response 200
    When I validate the response with message "successfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getLabTestPackages/1
  Scenario: As  user i can get lab test packages details by page
    Given As  user i can get lab test packages with application service end point "getLabTestPackages/1"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/deleteLabTestPackages?id=2
#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/deleteLabProviders?id=10

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateLabCategories
  Scenario: As  user i can update lab category details
    Given As  user i can update lab category details  with application service end point "updateLabCategories"
    Then I will receive valid response 200
    When I validate the response with message "Labtest category details updated successfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchLabTestPackages?searchTerm=Master&page=1
  Scenario: As  user i can search for lab Test packages.
    Given As  user i can search for lab Test packages details  with application service end point "searchLabTestPackages?searchTerm=Master&page=1"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"
#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getUsableCoupons
  Scenario: As  user i can get all usable couons
    Given As  user i can get all usable details  with application service end point "getUsableCoupons"
    Then I will receive valid response 200
    When I validate the response with message "Coupon details fetched succesfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getCoupons/1
  Scenario: As  user i can get all couons
    Given As  user i can get all usable details  with application service end point "getCoupons/1"
    Then I will receive valid response 200
    When I validate the response with message "Coupon details fetched succesfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAllOrders/2
  Scenario: As  user i can get all order
    Given As  user i can get all order details  with application service end point "getAllOrders/2"
    Then I will receive valid response 200
    When I validate the response with message "Orders retrieved successfully"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/deleteProfilePic?id=159

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getBugHistoryList/1
  Scenario: As  user i can get bug history
    Given As  user i can get bug history details  with application service end point "getBugHistoryList/1"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/insertBugReport
  Scenario: As  user i can insert new bug
    Given As  user i can insert new bug with application service end point "insertBugReport"
    Then I will receive valid response 200
    When I validate the response with message "Successfully Added Bug Report"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getBugReportList/1
  Scenario: As  user i can get bug report list
    Given As  user i can get bug report list details  with application service end point "getBugReportList/1"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateEmergencyRequestStatus
  Scenario: As  user i can update emergecy request status
    Given As  user i can update emergecy request status with application service end point "updateEmergencyRequestStatus"
    Then I will receive valid response 200
    When I validate the response with message "Emergency Request Status Updated Successfully."

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/deleteBugReport?id=21

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/getAllEmergencyCardRequests/1
  Scenario: As  user i can get all emergency card requests
    Given As  user i can get emergency card requests details  with application service end point "getAllEmergencyCardRequests/2"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched data"

#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchDoctorSpecialization/Skin Specialist
  Scenario: As  user i can search for doctor specialization
    Given As  user i can search for doctor specialization  with application service end point "searchDoctorSpecialization/Skin Specialist"
    Then I will receive valid response 200
    When I validate the response with message "Successfully Searched"

#  POST	https://suitsbackenddevelopment.azurewebsites.net/api/v1/insertProduct
#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateProduct?id=41
#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/api/v1/softDeleteLabProviders?id=14
#  https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateLabtests
#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/api/v1/softDeleteLabTestCategories?id=19
#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/api/v1/softDeleteLabTestPackages?id=
#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updatePackageStatus
#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateLabProviders
#  DELETE	https://suitsbackenddevelopment.azurewebsites.net/api/v1/softDeleteLabTest?id=

#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateLabtestPackage
  Scenario: As  user i can update lab test package
    Given As  user i can updatelab test package with application service end point "updateLabtestPackage"
    Then I will receive valid response 200
    When I validate the response with message "Lab Test Package details updated successfully."

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchLabTestProviders?searchTerm=Apollo
  Scenario: As  user i can get lab test provider details
    Given As  user i can get lab test provider details application service end point "searchLabTestProviders?searchTerm=Apollo"
    Then I will receive valid response 200
    When I validate the response with message "Search Succesful"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchLabTestCategories/1?keyword=health
  Scenario: As  user i can get lab test category by keyword
    Given As  user i can get lab test category by keyword application service end point "searchLabTestCategories/1?keyword=health"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchLabTest/1?keyword=Urine
  Scenario: As  user i can get lab test details by search terms
    Given As  user i can get lab test details by search terms with application service end point "searchLabTest/1?keyword=Urine"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/getLabTestBookingDetailsInAdminPortal/1
  Scenario: As  user i can get lab test booking details from admin portal
    Given As  user i can getlab test booking details from admin portal with application service end point "getLabTestBookingDetailsInAdminPortal/3"
    Then I will receive valid response 200
    When I validate the response with message "Bookings details successfully fetched"

#  POST	https://suitsbackenddevelopment.azurewebsites.net/api/v1/searchMobileProduct
  Scenario: As  user i can search mobile product
    Given As  user i can search mobile product with application service end point "searchMobileProduct"
    Then I will receive valid response 200
    When I validate the response with message "Successfully fetched data"

#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateOrdersDetails
  Scenario: As  user i can update order details
    Given As  user i can update order detailswith application service end point "updateOrdersDetails"
    Then I will receive valid response 200
    When I validate the response with message "Successfully Updated Details"

#  PUT	https://suitsbackenddevelopment.azurewebsites.net/api/v1/updateBugReportStatus
  Scenario: As  user i can update bug report status
    Given As  user i can update bug report status with application service end point "updateBugReportStatus"
    Then I will receive valid response 200
    When I validate the response with message "Bug Report Status Updated Successfully."

#  PATCH	https://suitsbackenddevelopment.azurewebsites.net/api/v1/activateSaveyoMembership
#  Scenario: As  user i can activate Save membership
#    Given As  user i can activate Save membership with application service end point "activateSaveyoMembership"
#    Then I will receive valid response 200
#    When I validate the response with message "SaveYo Membership activated successfully."

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/doctorsfiltered?gender=Female&city=guntur&spec_name=Neurologist
  Scenario: As  user i can get list of doctor with spec id
    Given As  user i can get list of doctor with spec id application service end point "doctorsfiltered?gender=Female&city=guntur&spec_name=Neurologist"
    Then I will receive valid response 200
    When I validate the response with message "Successfully Fetched Data"

#  GET	https://suitsbackenddevelopment.azurewebsites.net/api/v1/allactiveregions
  Scenario: As  user i can get all active regions
    Given As  user i can get all active regions with application service end point "allactiveregions"
    Then I will receive valid response 200
    When I validate the response with message "Succesfully fetched data"





