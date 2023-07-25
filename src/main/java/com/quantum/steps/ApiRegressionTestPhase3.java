package com.quantum.steps;

import com.quantum.utils.Jsonutility;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.quantum.utils.Jsonutility.*;
import static com.quantum.utils.Jsonutility.readJson;
import static io.restassured.RestAssured.given;

public class ApiRegressionTestPhase3 {
    public static Response response;
    public static String getBearerToken = null;


    @Before
    public static void getToken() {
        RestAssured.baseURI = "https://suitsbackenddevelopment.azurewebsites.net/api/v1/";
        JSONObject sign = Jsonutility.readJson("src/main/resources/data/signin.json");
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(sign.toJSONString())
                .when()
                .post("signin")
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        getBearerToken = jsonPath.get("token");
    }


    // *************************************************** new  API  Release ********************************************************


    @Given("^I Signup new Doctor details with \"([^\"]*)\" application service end point \"([^\"]*)\"$")
    public void iSignupNewDoctorDetailsWithApplicationServiceEndPoint(String validOrInvalidData, String endpoint) throws Throwable {
        JSONObject jsonObject;
        if (validOrInvalidData.equalsIgnoreCase("Valid data")) {
            WriteJson("src/main/resources/data/SuitPhase3/newdoctorsignup.json", "email", GetRandomEmailID());
            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup.json");
            int newliceseno = Integer.parseInt(jsonObject.get("license_no").toString()) + 1;
            WriteJson("src/main/resources/data/SuitPhase3/newdoctorsignup.json", "license_no", String.valueOf(newliceseno));

            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup.json");
            response = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(jsonObject.toJSONString())
                    .when()
                    .post(endpoint)
                    .then()
                    .extract().response();
            response.getBody().prettyPrint();

        } else if (validOrInvalidData.equalsIgnoreCase("Existing email address")) {
            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignupinvalid.json");
            response = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(jsonObject.toJSONString())
                    .when()
                    .post(endpoint)
                    .then()
                    .extract().response();
            response.getBody().prettyPrint();

        } else if (validOrInvalidData.equalsIgnoreCase("Existing Phone Number")) {
            WriteJson("src/main/resources/data/SuitPhase3/newdoctorsignup_existingphonenumber.json", "email", GetRandomEmailID());
            WriteJson("src/main/resources/data/SuitPhase3/newdoctorsignup_existingphonenumber.json", "license_no", GetRandomNo());
            String exiting_phoneno = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup_existingphonenumber.json").get("phone_no").toString();
            WriteJson("src/main/resources/data/SuitPhase3/newdoctorsignup_existingphonenumber.json", "phone_no", exiting_phoneno);

            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup_existingphonenumber.json");
            response = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(jsonObject.toJSONString())
                    .when()
                    .post(endpoint)
                    .then()
                    .extract().response();
            response.getBody().prettyPrint();

        } else if (validOrInvalidData.equalsIgnoreCase("Invalid data")) {
            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup_multipleinvalidata.json");
            response = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(jsonObject.toJSONString())
                    .when()
                    .post(endpoint)
                    .then()
                    .extract().response();
            response.getBody().prettyPrint();
        }
    }

    @When("^I will receive valid response (\\d+)$")
    public void iWillReceiveValidResponse(int statuscode) {
        if (statuscode == 200) {
            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
        } else if (statuscode == 201) {
            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
        } else if (statuscode == 500) {
            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
        }
    }

    @Then("^I validate suessfully message \"([^\"]*)\"$")
    public void iValidateSuessfullyMessage(String msg) throws Throwable {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "Not Matched with expected value");
    }

    @Then("^I Verify response Message key \"([^\"]*)\"$")
    public void iVerifyResponsemessage(String msg) throws Throwable {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "Missing value validation");

    }

    @Then("I Verify  response error key \"([^\"]*)\"$")
    public void iVerifyErrormessage(String errormsg) throws Throwable {
        Assert.assertEquals(response.jsonPath().get("error"), errormsg, "error message not matched");

    }

    @Given("^I Get Doctor details with application service end point \"([^\"]*)\"$")
    public void iGetDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I validate the response with message \"([^\"]*)\"$")
    public void iValidateTheResponseWithMessage(String msg) throws Throwable {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records Fetched");
    }

    @Given("^I Get list specification of treatment with application service end point \"([^\"]*)\"$")
    public void iGetListSpecificationOfTreamentWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I Get List of Doctor appoint schedule  with application service end point \"([^\"]*)\"$")
    public void iGetListOfDoctorAppointScheduleWithApplicationServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I Get Doctor Specialization with application service end point \"([^\"]*)\"$")
    public void iGetDoctorSpecializationWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^I get list of of all customer  with service end point \"([^\"]*)\"$")
    public void iGetListOfOfAllCustomerWithServiceEndPoint(String arg0) throws Throwable {

    }


    @Given("^I change password of existing doctor id with \"([^\"]*)\" application service end point \"([^\"]*)\"$")
    public void iChangePasswordOfExistingDoctorIdWithApplicationServiceEndPoint(String invalidOrValid, String endpoint) throws Throwable {
//        String newpassword = new String(generatePassword(10));
//        WriteJson("src/main/resources/data/SuitPhase3/newdoctorchangepassword.json", "newpassword", newpassword);
        JSONObject jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorchangepassword.json");
        if (invalidOrValid.equalsIgnoreCase("Valid password")) {
            response = given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + getBearerToken)
                    .and()
                    .body(jsonObject.toJSONString())
                    .when()
                    .put(endpoint)
                    .then()
                    .extract().response();
            response.getBody().prettyPrint();
            //WriteJson("src/main/resources/data/SuitPhase3/newdoctorchangepassword.json", "oldpassword", newpassword);

        } else if (invalidOrValid.equalsIgnoreCase("")) {
            jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorsignup.json");

        } else if (invalidOrValid.equalsIgnoreCase("")) {

        } else if (invalidOrValid.equalsIgnoreCase("")) {

        }

    }

    @Given("^I change password of not existing doctor id  application service end point \"([^\"]*)\"$")
    public void iChangePasswordOfNotExistingDoctorIdApplicationServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitPhase3/newdoctorchangepassword_doctornotexists.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @And("^I validate the doctor details \"([^\"]*)\" \"([^\"]*)\"$")
    public void iValidateTheDoctorDetails(String email, String name) throws Throwable {
        Assert.assertEquals(true, response.path("result.doctor.email").toString().contains(email), "Email not Matched");
        Assert.assertEquals(true, response.path("result.doctor.name").toString().contains(name), "Name not matched");
    }

    @Given("^I update existing doctor details with application service end point \"([^\"]*)\"$")
    public void iUpdateExistingDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitPhase3/updatedoctordetails_valid.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^I search for doctor details with application service end point \"([^\"]*)\"$")
    public void iSearchForDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^I search for doctor details with token id with application service end point \"([^\"]*)\"$")
    public void iSearchForDoctorDetailsWithTokenIdWithApplicationServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^I search for doctor details with specialization by appliation service end point \"([^\"]*)\"$")
    public void iSearchForDoctorDetailsWithSpecializationByAppliationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I list of all specializations with application service end point \"([^\"]*)\"$")
    public void iListOfAllSpecializationsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As Doctor i login application with application service end point \"([^\"]*)\"$")
    public void asDoctorILoginApplicationWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitPhase3/doctorlogin.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @And("^I validate the token id key received$")
    public void iValidateTheTokenIdKeyReceived() {
        Assert.assertEquals(true, response.jsonPath().get("token").toString().length() > 0, "Token not exists");
    }

    @Given("^As user i need to get time slots with application service end point \"([^\"]*)\"$")
    public void asUserINeedToGetTimeSlotsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i need set time slots with application service end point \"([^\"]*)\"$")
    public void asUserINeedSetTimeSlotsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitPhase3/settimeslot.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I Get List available slots with particular doctor id with application service end point \"([^\"]*)\"$")
    public void iGetListAvailableSlotsWithParticularDoctorIdWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I get list of appointments with application service end point \"([^\"]*)\"$")
    public void iGetListOfAppointmentsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can update existing category with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateExistingCategoryWithApplicationserviceEndPoint(String endpoint) {
        JSONObject jsonobject = readJson("src/main/resources/data/SuitePhase3Ext/updatecategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonobject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can get all products by type with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllProductsByTypeWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can see all existing category with application service end point \"([^\"]*)\"$")
    public void asUserICanSeeAllExistingCategoryWithApplicationserviceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();


    }

    @Given("^As user i can book appointment with doctor id with application service end point \"([^\"]*)\"$")
    public void asUserICanBookAppointmentWithDoctorIdWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String appointdate = df.format(new Date());
        WriteJson("src/main/resources/data/SuitePhase3Ext/createdoctorappoint.json", "appointment_date", appointdate);
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/createdoctorappoint.json");
        int slots = Integer.parseInt(jsonObject.get("slot_id").toString());
        if (slots >= 4) {
            slots = 1;
        } else {
            slots = slots + 1;
        }
        WriteJson("src/main/resources/data/SuitePhase3Ext/createdoctorappoint.json", "slot_id", slots + "");
        jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/createdoctorappoint.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can search by pincode for details of service location with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchByPincodeForDetailsOfServiceLocationWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();


    }

    @Given("^As user i can get products with categories with appliation service end point \"([^\"]*)\"$")
    public void asUserICanGetProductsWithCategoriesWithApplicationserviceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can payment for appointment payment with application service end point \"([^\"]*)\"$")
    public void asUserICanPaymentForAppointmentPaymentWithApplicationServicceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/paymentappoint.json");
        int transactionId = Integer.parseInt(jsonObject.get("transactionId").toString());
        if (transactionId > 0) {
            transactionId = transactionId + 1;
        }
        WriteJson("src/main/resources/data/SuitePhase3Ext/paymentappoint.json", "transactionId", transactionId + "");
         jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/paymentappoint.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i cannot create duplicate appointment with application service end point \"([^\"]*)\"$")
    public void asUserICannotCreateDuplicateAppointmentWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String appointdate = df.format(new Date());
        WriteJson("src/main/resources/data/SuitePhase3Ext/createduplicateappoint.json", "appointment_date", appointdate);
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/createduplicateappoint.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As user i can create new subcategory with application service end point \"([^\"]*)\"$")
    public void asUserICanCreateNewSubcategoryWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        WriteJson("src/main/resources/data/SuitePhase3Ext/insertsubcategory.json" ,"name" ,"Family"+ RandomStringUtils.randomAlphabetic(2) );
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertsubcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As user i can create new labtest category with application service end point \"([^\"]*)\"$")
    public void asUserICanCreateNewLabtestCategoryWithApplicationnServiceEndPoint(String endpoint) throws Throwable {
        WriteJson("src/main/resources/data/SuitePhase3Ext/insertlabtestcategory.json" ,"name" ,"Sample care"+ RandomStringUtils.randomAlphabetic(2) );
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertlabtestcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can verify the order details by order id with application service end point \"([^\"]*)\"$")
    public void asUserICanVerifyTheOrderDetailsByOrderIdWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As user i can verify all medicine types with application service end point \"([^\"]*)\"$")
    public void asUserICanVerifyAllMedicineTypesWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can verify coupon types with application service end point \"([^\"]*)\"$")
    public void asUserICanVerifyCouponTypesWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can insert new coupon with application service end point \"([^\"]*)\"$")
    public void asUserICanInsertNewCouponWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        WriteJson("src/main/resources/data/SuitePhase3Ext/insertcoupon.json","code",RandomStringUtils.randomAlphabetic(8));
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertcoupon.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can generate new coupon code with application service end point \"([^\"]*)\"$")
    public void asUserICanGenerateNewCouponCodeWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can update the existing coupon status with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateTheExistingCouponStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatecouponstatus.json");
        String newstatus = jsonObject.get("status").toString();
        boolean currentstatus = true;
        if(newstatus.equalsIgnoreCase("true")){
            currentstatus=false;
        }else{
            currentstatus=true;
        }
        WriteJson("src/main/resources/data/SuitePhase3Ext/updatecouponstatus.json","status",currentstatus);
        jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatecouponstatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^as user i can update the existing coupon details with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateTheExistingCouponDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatecoupon.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can delte the existing coupon details with application service end point \"([^\"]*)\"$")
    public void asUserICanDelteTheExistingCouponDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .delete(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can search for all product with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchForAllProductWithApplicationServiceEndPoint(String endpoint) throws Throwable {

        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can update product status with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateProductStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updateproductsstatus.json");
        String newstatus = jsonObject.get("status").toString();
        boolean currentstatus = true;
        if(newstatus.equalsIgnoreCase("true")){
            currentstatus=false;
        }else{
            currentstatus=true;
        }
        WriteJson("src/main/resources/data/SuitePhase3Ext/updateproductsstatus.json","status",currentstatus);
        jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updateproductsstatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can insert new labtest package with application service end point \"([^\"]*)\"$")
    public void asUserICanInsertNewLabtestPackageWithAppllicationServiceEndPoint(String endpoint) throws Throwable {

        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertlabtestpackage.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As user i can update the Category status with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateTheCategoryStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatecategorystatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^As user i can search Category status with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchCategoryStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/searchcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }


    @Given("^As user i can insert new category with application service end point \"([^\"]*)\"$")
    public void asUserICanInsertNewCategoryWithApplicationServiceEndPoint(String endpoint) {
        WriteJson("src/main/resources/data/SuitePhase3Ext/insertcategory.json", "name", "Automation" + RandomStringUtils.randomAlphabetic(5));
        WriteJson("src/main/resources/data/SuitePhase3Ext/insertcategory.json", "image", "selenium" + RandomStringUtils.randomAlphabetic(5));

        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^As user i can insert new sub category with application service end point \"([^\"]*)\"$")
    public void asUserICanInsertNewSubCategoryWithApplicationServiceEndPoint(String endpoint) throws Throwable {

        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatesubcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As user i can get all lab provider details with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllLabProviderDetailsWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can get all lab tests details with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllLabTestsDetailsWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get all lab tests booking details with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllLabTestsBookingDetailsWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get category lab test packages with application service end point \"([^\"]*)\"$")
    public void asUserICanGetCategoryLabTestPackagesWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get lab test packages with application service end point \"([^\"]*)\"$")
    public void asUserICanGetLabTestPackagesWithApplicationserviceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can update lab category details  with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateLabCategoryDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatelabcategory.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can search for lab Test packages details  with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchForLabTestPackagesDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get all usable details  with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllUsableDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can search order details  with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchOrderDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get all order details  with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllOrderDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get bug history details  with application service end point \"([^\"]*)\"$")
    public void asUserICanGetBugHistoryDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get bug report list details  with application service end point \"([^\"]*)\"$")
    public void asUserICanGetBugReportListDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get emergency card requests details  with application service end point \"([^\"]*)\"$")
    public void asUserICanGetEmergencyCardRequestsDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can search for doctor specialization  with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchForDoctorSpecializationWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can update emergecy request status with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateEmergecyRequestStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updateemergencyrequeststatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .patch(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get list of doctor with spec id application service end point \"([^\"]*)\"$")
    public void asUserICanGetListOfDoctorWithSpecIdApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/searchmobileproduct.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can get all active regions with application service end point \"([^\"]*)\"$")
    public void asUserICanGetAllActiveRegionsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }


    @Given("^As  user i can updatelab test package with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdatelabTestPackageWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatelabtestpackage.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can get lab test provider details application service end point \"([^\"]*)\"$")
    public void asUserICanGetLabTestProviderDetailsApplicationServiceEndPoint(String endpoint) throws Throwable {

        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can get lab test category by keyword application service end point \"([^\"]*)\"$")
    public void asUserICanGetLabTestCategoryByKeywordApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can get lab test details by search terms with application service end point \"([^\"]*)\"$")
    public void asUserICanGetLabTestDetailsBySearchTermsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^As  user i can getlab test booking details from admin portal with application service end point \"([^\"]*)\"$")
    public void asUserICanGetlabTestBookingDetailsFromAdminPortalWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can activate Save membership with application service end point \"([^\"]*)\"$")
    public void asUserICanActivateSaveMembershipWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/activatemembership.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .patch(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can update bug report status with application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateBugReportStatusWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updatebugreortstatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can search mobile product with application service end point \"([^\"]*)\"$")
    public void asUserICanSearchMobileProductWithApplicationServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/searchmobileproduct.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can update order detailswith application service end point \"([^\"]*)\"$")
    public void asUserICanUpdateOrderDetailswithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/updateOrdersDetails.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^As  user i can insert new bug with application service end point \"([^\"]*)\"$")
    public void asUserICanInsertNewBugWithApplicationServiceEndPoint(String endpoint) throws Throwable {
        JSONObject jsonObject = readJson("src/main/resources/data/SuitePhase3Ext/insertbugreport.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }
}
