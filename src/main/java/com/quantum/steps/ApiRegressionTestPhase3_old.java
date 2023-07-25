//package com.quantum.steps;
//
//import com.quantum.utils.Jsonutility;
//import cucumber.api.PendingException;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.testng.Assert;
//
//import java.util.Map;
//
//import static com.quantum.utils.Jsonutility.*;
//import static com.quantum.utils.Jsonutility.readJson;
//import static io.restassured.RestAssured.given;
//
//public class ApiRegressionTestPhase3 {
//    public static Response response;
//    public static String getBearerToken = null;
//
//
//    @Before
//    public static void getToken() {
//        RestAssured.baseURI = "https://suitsbackenddevelopment.azurewebsites.net/router/";
//        JSONObject sign = Jsonutility.readJson("src/main/resources/data/signin.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .and()
//                .body(sign.toJSONString())
//                .when()
//                .post("signin")
//                .then()
//                .extract().response();
//        JsonPath jsonPath = response.jsonPath();
//        getBearerToken = jsonPath.get("token");
//    }
//
//
//    // *************************************************** new  API  Release ********************************************************
//
//
//    @Given("^I Signup new Doctor details with \"([^\"]*)\" application service end point \"([^\"]*)\"$")
//    public void iSignupNewDoctorDetailsWithApplicationServiceEndPoint(String validOrInvalidData, String endpoint) throws Throwable {
//        JSONObject jsonObject;
//        if (validOrInvalidData.equalsIgnoreCase("Valid data")) {
//            WriteJson("src/main/resources/data/SuitsPhase3/newdoctorsignup.json", "email", GetRandomEmailID());
//            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignup.json");
//            int newliceseno = Integer.parseInt(jsonObject.get("license_no").toString()) + 1;
//            WriteJson("src/main/resources/data/SuitsPhase3/newdoctorsignup.json", "license_no", String.valueOf(newliceseno));
//            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignup.json");
//            response = given()
//                    .header("Content-type", "application/json")
//                    .and()
//                    .body(jsonObject.toJSONString())
//                    .when()
//                    .post(endpoint)
//                    .then()
//                    .extract().response();
//            response.getBody().prettyPrint();
//
//        } else if (validOrInvalidData.equalsIgnoreCase("Existing email address")) {
//            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignupinvalid.json");
//            response = given()
//                    .header("Content-type", "application/json")
//                    .and()
//                    .body(jsonObject.toJSONString())
//                    .when()
//                    .post(endpoint)
//                    .then()
//                    .extract().response();
//            response.getBody().prettyPrint();
//
//        } else if (validOrInvalidData.equalsIgnoreCase("Existing Phone Number")) {
//            // this endpoint  removed
////            WriteJson("src/main/resources/data/SuitsPhase3/newdoctorsignup_existingphonenumber.json", "email", GetRandomEmailID());
////            WriteJson("src/main/resources/data/SuitsPhase3/newdoctorsignup_existingphonenumber.json", "license_no", GetRandomNo());
////
////            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignup_existingphonenumber.json");
////            response = given()
////                    .header("Content-type", "application/json")
////                    .and()
////                    .body(jsonObject.toJSONString())
////                    .when()
////                    .post(endpoint)
////                    .then()
////                    .extract().response();
////            response.getBody().prettyPrint();
//
//        } else if (validOrInvalidData.equalsIgnoreCase("Invalid data")) {
//            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignup_multipleinvalidata.json");
//            response = given()
//                    .header("Content-type", "application/json")
//                    .and()
//                    .body(jsonObject.toJSONString())
//                    .when()
//                    .post(endpoint)
//                    .then()
//                    .extract().response();
//            response.getBody().prettyPrint();
//        }
//    }
//
//    @When("^I will receive valid response (\\d+)$")
//    public void iWillReceiveValidResponse(int statuscode) {
//        if (statuscode == 200) {
//            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
//        } else if (statuscode == 201) {
//            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
//        } else if (statuscode == 500) {
//            Assert.assertEquals(statuscode, response.statusCode(), "Actual status validate:" + response.statusCode());
//        }
//    }
//
//    @Then("^I validate suessfully message \"([^\"]*)\"$")
//    public void iValidateSuessfullyMessage(String msg) throws Throwable {
//        Assert.assertEquals(response.jsonPath().get("Message"), msg, "Not Matched with expected value");
//    }
//
//    @Then("^I Verify response Message key \"([^\"]*)\"$")
//    public void iVerifyResponsemessage(String msg) throws Throwable {
//        Assert.assertEquals(response.jsonPath().get("Message"), msg, "Missing value validation");
//
//    }
//
//    @Then("I Verify  response error key \"([^\"]*)\"$")
//    public void iVerifyErrormessage(String errormsg) throws Throwable {
//        Assert.assertEquals(response.jsonPath().get("error"), errormsg, "error message not matched");
//
//    }
//
//    @Given("^I Get Doctor details with application service end point \"([^\"]*)\"$")
//    public void iGetDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @When("^I validate the response with message \"([^\"]*)\"$")
//    public void iValidateTheResponseWithMessage(String msg) throws Throwable {
//        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records Fetched");
//    }
//
//    @Given("^I Get list specification of treatment with application service end point \"([^\"]*)\"$")
//    public void iGetListSpecificationOfTreamentWithApplicationServieEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^I Get List of Doctor appoint schedule  with application service end point \"([^\"]*)\"$")
//    public void iGetListOfDoctorAppointScheduleWithApplicationServiceEndPoint(String endpoint) {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^I Get Doctor Specialization with application service end point \"([^\"]*)\"$")
//    public void iGetDoctorSpecializationWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//
//    @Given("^I get list of of all customer  with service end point \"([^\"]*)\"$")
//    public void iGetListOfOfAllCustomerWithServiceEndPoint(String arg0) throws Throwable {
//
//    }
//
//
//    @Given("^I change password of existing doctor id with \"([^\"]*)\" application service end point \"([^\"]*)\"$")
//    public void iChangePasswordOfExistingDoctorIdWithApplicationServiceEndPoint(String invalidOrValid, String endpoint) throws Throwable {
//        String newpassword = new String(generatePassword(13));
//        WriteJson("src/main/resources/data/SuitsPhase3/newdoctorchangepassword.json", "newpassword", newpassword);
//        JSONObject jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorchangepassword.json");
//        if (invalidOrValid.equalsIgnoreCase("Valid password")) {
//            response = given()
//                    .header("Content-type", "application/json")
//                    .header("Authorization", "Bearer " + getBearerToken)
//                    .and()
//                    .body(jsonObject.toJSONString())
//                    .when()
//                    .put(endpoint)
//                    .then()
//                    .extract().response();
//            response.getBody().prettyPrint();
//            //WriteJson("src/main/resources/data/SuitsPhase3/newdoctorchangepassword.json", "oldpassword", newpassword);
//
//        } else if (invalidOrValid.equalsIgnoreCase("")) {
//            jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorsignup.json");
//
//        } else if (invalidOrValid.equalsIgnoreCase("")) {
//
//        } else if (invalidOrValid.equalsIgnoreCase("")) {
//
//        }
//
//    }
//
//    @Given("^I change password of not existing doctor id  application service end point \"([^\"]*)\"$")
//    public void iChangePasswordOfNotExistingDoctorIdApplicationServiceEndPoint(String endpoint) {
//        JSONObject jsonObject = readJson("src/main/resources/data/SuitsPhase3/newdoctorchangepassword_doctornotexists.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .body(jsonObject.toJSONString())
//                .when()
//                .put(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//
//    }
//
//    @And("^I validate the doctor details \"([^\"]*)\" \"([^\"]*)\"$")
//    public void iValidateTheDoctorDetails(String email, String name) throws Throwable {
//        Assert.assertEquals(true, response.path("result.doctor.email").toString().contains(email), "Email not Matched");
//        Assert.assertEquals(true, response.path("result.doctor.name").toString().contains(name), "Name not matched");
//    }
//
//    @Given("^I update existing doctor details with application service end point \"([^\"]*)\"$")
//    public void iUpdateExistingDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        JSONObject jsonObject = readJson("src/main/resources/data/SuitsPhase3/updatedoctordetails_valid.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .body(jsonObject.toJSONString())
//                .when()
//                .put(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//
//    }
//
//    @Given("^I search for doctor details with application service end point \"([^\"]*)\"$")
//    public void iSearchForDoctorDetailsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//
//    @Given("^I search for doctor details with token id with application service end point \"([^\"]*)\"$")
//    public void iSearchForDoctorDetailsWithTokenIdWithApplicationServiceEndPoint(String endpoint) {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//
//    @Given("^I search for doctor details with specialization by application service end point \"([^\"]*)\"$")
//    public void iSearchForDoctorDetailsWithSpecializationByApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^I list of all specializations with application service end point \"([^\"]*)\"$")
//    public void iListOfAllSpecializationsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^As Doctor i login application with application service end point \"([^\"]*)\"$")
//    public void asDoctorILoginApplicationWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        JSONObject jsonObject = readJson("src/main/resources/data/SuitsPhase3/doctorlogin.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .body(jsonObject.toJSONString())
//                .when()
//                .post(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @And("^I validate the token id key received$")
//    public void iValidateTheTokenIdKeyReceived() {
//        Assert.assertEquals(true, response.jsonPath().get("token").toString().length() > 0, "Token not exists");
//    }
//
//    @Given("^As user i need to get time slots with application service end point \"([^\"]*)\"$")
//    public void asUserINeedToGetTimeSlotsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^As user i need set time slots with application service end point \"([^\"]*)\"$")
//    public void asUserINeedSetTimeSlotsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        JSONObject jsonObject = readJson("src/main/resources/data/SuitsPhase3/settimeslot.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .body(jsonObject.toJSONString())
//                .when()
//                .post(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^I Get List available slots with particular doctor id with application service end point \"([^\"]*)\"$")
//    public void iGetListAvailableSlotsWithParticularDoctorIdWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//    @Given("^I get list of appointments with application service end point \"([^\"]*)\"$")
//    public void iGetListOfAppointmentsWithApplicationServiceEndPoint(String endpoint) throws Throwable {
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//
//
//}
