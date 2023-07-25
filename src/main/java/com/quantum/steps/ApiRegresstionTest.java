package com.quantum.steps;


import com.qmetry.qaf.automation.step.QAFTestStepProvider;
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
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.List;

import static com.quantum.utils.Jsonutility.*;
import static io.restassured.RestAssured.given;

@QAFTestStepProvider
public class ApiRegresstionTest {

    public static Response response;

    public static String bloodrequisition = "{\"state\":\"Andhra Pradesh\",\"district\":\"Ananthapuramu\",\"city\":\"Ananthapuramu\",\"hospital\":\"Apollo Hospital\",\"doctor\":\"Dr.Amol\",\"patient\":\"Arpita\",\"age\":22,\"gender\":\"Female\",\"bloodgroup\":\"A Positive\",\"ipnumber\":\"ip435\",\"particulars\":\"[Whole Blood, Fresh Frozen Plasma]\",\"units\":\"2\",\"typeneed\":\"Routine(within 8 hour)\",\"orderedby\":\"shyam\",\"phone\":\"8585869695\",\"imagename\":\"pathvalue\"}";
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

    @Given("^set the base domain \"([^\"]*)\"$")
    public void setTheBaseDomain(String uri) {
        RestAssured.baseURI = uri;
    }


    @Given("^I set post to signin appliction service end point \"([^\"]*)\"$")
    public void iSetPostToSigninApplictionServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/signin.json");
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

    @Given("^I set post new signup service end point \"([^\"]*)\"$")
    public void iSetPostNewSignupServiceEndPoint(String endpoint) {
        JSONObject jsonObject;
//        jsonObject= readJson("src/main/resources/data/signup.json");
        WriteJson("src/main/resources/data/signup.json", "mobile", GetRandomMobileNo());
        jsonObject = readJson("src/main/resources/data/signup.json");
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

    @Given("^I set post new signin service end point \"([^\"]*)\"$")
    public void iSetPostNewSigninServiceEndPoint(String signinendpoint) {
        JSONObject sign = Jsonutility.readJson("src/main/resources/data/signin.json");
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(sign)
                .when()
                .post(signinendpoint)
                .then()
                .extract().response();
    }


    @Given("^I set post reset existing password service end point \"([^\"]*)\"$")
    public void iSetPostResetExistingPasswordServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/changepassword.json");
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

    @And("^I verify message for reset password \"([^\"]*)\"$")
    public void iVerifyMessageForResetPassword(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @Given("^I set post to forgot password service end point \"([^\"]*)\"$")
    public void iSetPostToForgotPasswordServiceEndPoint(String endpoint) {
        JSONObject signupmobileno = readJson("src/main/resources/data/signup.json");
        WriteJson("src/main/resources/data/changeforgotpassword.json", "mobile", signupmobileno.get("mobile").toString());
        JSONObject jsonObject = readJson("src/main/resources/data/changeforgotpassword.json");
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


    @Given("^I set post addbloodrequestio service end point \"([^\"]*)\"$")
    public void iSetPostAddbloodrequestioServiceEndPoint(String addbloodrequestion) {
        System.out.println();

        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(bloodrequisition)
                .when()
                .post(addbloodrequestion)
                .then()
                .extract().response();
        Assert.assertTrue(true, response.prettyPrint());

    }

    @Given("^I get list of blood requestion with status service end point \"([^\"]*)\"$")
    public void iGetListOfBloodRequestionWithStatusServiceEndPoint(String approvedOrrejectedstatus) {
        response = given()
                .header("Content-type", "application/json")
                .when()
                .get(approvedOrrejectedstatus)
                .then()
                .extract().response();
    }

    @And("^I validate \"([^\"]*)\" status with YES$")
    public void iValidateStatusWithYES(String status) {
        if (status.equalsIgnoreCase("approved")) {
            JsonPath jsonPath = response.jsonPath();
            List<String> ss = jsonPath.get("users.approved");
            Assert.assertTrue((long) ss.size() > 0, "Total Records with approve:YES= " + (long) ss.size());
            Assert.assertTrue(ss.stream().noneMatch(s -> s.contains("NO")), "All records approve:true status");
        } else if (status.equalsIgnoreCase("Rejected")) {
            JsonPath jsonPath = response.jsonPath();
            List<String> ss = jsonPath.get("users.approved");
            Assert.assertTrue((long) ss.size() > 0, "Total Records with approve:NO= " + (long) ss.size());
            Assert.assertTrue(ss.stream().noneMatch(s -> s.contains("YES")), "All records approve:NO status");
        }
    }


    @And("^I validate the Approved status to \"([^\"]*)\"$")
    public void iValidateTheStatusToNull(String status) {
        JsonPath jsonPath = response.jsonPath();
        String approved_status = jsonPath.get("users.approved");
        if (status.equalsIgnoreCase("null")) {
            Assert.assertNull(approved_status, "Approved status not matched");
        } else if (status.equalsIgnoreCase("No")) {
            Assert.assertEquals(approved_status, status, "Approved status not matched");
        }
    }

    @And("^I set post update blood requisition status \"([^\"]*)\" to Yes$")
    public void iSetPostUpdateBloodRequisitionStatusToYes(String status) {
        String endpoint;
        if (status.equalsIgnoreCase("Approved")) {
            endpoint = "updatebloodreq";
            Jsonutility.WriteJson("src/main/resources/data/approvedBloodReqisition.json", "id", response.jsonPath().get("result.id").toString());

        } else {
            endpoint = "updatebloodreqrej";
        }
        JSONObject jsonObject = Jsonutility.readJson("src/main/resources/data/approvedBloodReqisition.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
        Assert.assertEquals(response.jsonPath().get("Message"), "Successfully Updated", "unable to update the record");
//  can be validate when user able to search a single record
//        JsonPath jsonPath = response.jsonPath();
//        String approved_status = jsonPath.get("users.approved");
//        if (status.equalsIgnoreCase("Approved")) {
//            Assert.assertEquals(approved_status, "YES", "Status not matched");
//        } else if (status.equalsIgnoreCase("Rejected")) {
//            Assert.assertEquals(approved_status, "NO", "Status not matched");
//
//        }
    }

    @Given("^I set post add new blood bank service end point \"([^\"]*)\"$")
    public void iSetPostAddNewBloodBankServiceEndPoint(String endpoint) {
        String mobileno = GetRandomMobileNo();
        WriteJson("src/main/resources/data/addbloodbank.json", "mobile", mobileno);
        JSONObject jsonObject = readJson("src/main/resources/data/addbloodbank.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I set post block blood bank service end point \"([^\"]*)\"$")
    public void iSetPostBlockBloodBankServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/addbloodbank.json");
        String blockbloodbank = "{\"mobile\": \"" + jsonObject.get("mobile") + "\"}";
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(blockbloodbank)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Then("^I validate \"([^\"]*)\" message$")
    public void iValidateMessage(String blockedmsg) {
        Assert.assertEquals(response.jsonPath().get("Message"), blockedmsg, "unable to block the blood bank");
    }


    @Then("^I validate unblock message \"([^\"]*)\"$")
    public void iValidateUnblockMessage(String unblockedmsg) {
        Assert.assertEquals(response.jsonPath().get("Message"), unblockedmsg, "unable to unblock the blood bank");

    }

    @Given("^I get list of blood bank service end point \"([^\"]*)\"$")
    public void iGetListOfBloodBankServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }


    @Given("^I get list of blood bank with search condition service end point \"([^\"]*)\"$")
    public void iGetListOfBloodBankWithSearhConditionServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/searchbloodbank.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I get search for blood donor service end point \"([^\"]*)\"$")
    public void iGetSearchForBloodDonorServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/searchblooddonor.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I get list of of all customer with service end point \"([^\"]*)\"$")
    public void iGetListOfOfAllCustomerWithServieEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @Given("^I insert new customer details with service end point \"([^\"]*)\"$")
    public void iInsertNewCustomerDetailsWithServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/insertCustomerAddress.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify message for insert new customer address \"([^\"]*)\"$")
    public void iVerifyMessageForInsertNewCustomerAddress(String msg) {
        Assert.assertEquals(response.jsonPath().get("message"), msg, "No records displayed");
    }

    @Given("^I update customer details with service end point \"([^\"]*)\"$")
    public void iUpdateCustomerDetailsWithServiceEndPoint(String endpoint) {
        WriteJson("src/main/resources/data/updateCustomerAddress.json", "id", "97");
        WriteJson("src/main/resources/data/updateCustomerAddress.json", "user_id", "159");

        JSONObject jsonObject = readJson("src/main/resources/data/updateCustomerAddress.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();


    }


    @Given("^I delete existingcustomer details with service end point \"([^\"]*)\"$")
    public void iDeleteExistingcustomerDetailsWithServiceEndPoint(String endpoint) {

        WriteJson("src/main/resources/data/updateCustomerAddress.json", "id", "97");
        WriteJson("src/main/resources/data/updateCustomerAddress.json", "user_id", "159");

        JSONObject jsonObject = readJson("src/main/resources/data/deleteCustomerAddress.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .delete(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();

    }

    @When("^I verify message for delete existing customer address \"([^\"]*)\"$")
    public void iVerifyMessageForDeleteExistingCustomerAddress(String msg) {
        Assert.assertEquals(response.jsonPath().get("message"), msg, "No records displayed");
    }

    @Given("^I set post add new emergency contact service end point \"([^\"]*)\"$")
    public void iSetPostAddNewEmergencyContactServiceEndPoint(String endpoint) {

        JSONObject jsonObject = readJson("src/main/resources/data/addemergencycontact.json");
        WriteJson("src/main/resources/data/addemergencycontact.json", "idv", String.valueOf(Integer.parseInt(jsonObject.get("idv").toString()) + 1));
        jsonObject = readJson("src/main/resources/data/addemergencycontact.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify message for blood requisition \"([^\"]*)\"$")
    public void iVerifyMessageForBloodRequisition(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @When("^I verify message for getbloodbanklist \"([^\"]*)\"$")
    public void iVerifyMessageForGetbloodbanklist(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @Given("^I set post update new emergency contact service end point \"([^\"]*)\"$")
    public void iSetPostUpdateNewEmergencyContactServiceEndPoint(String endpoint) {

        JSONObject jsonObject = readJson("src/main/resources/data/updateemergencycontact.json");
        WriteJson("src/main/resources/data/updateemergencycontact.json", "idv", String.valueOf(Integer.parseInt(jsonObject.get("idv").toString()) + 1));
        WriteJson("src/main/resources/data/updateemergencycontact.json", "mobileone", GetRandomMobileNo());
        WriteJson("src/main/resources/data/updateemergencycontact.json", "mobiletwo", GetRandomMobileNo());
        WriteJson("src/main/resources/data/updateemergencycontact.json", "mobilethree", GetRandomMobileNo());

        jsonObject = readJson("src/main/resources/data/updateemergencycontact.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify message for updateemergencycontact \"([^\"]*)\"$")
    public void iVerifyMessageForUpdateemergencycontact(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @When("^I verify message for new user signup \"([^\"]*)\"$")
    public void iVerifyMessageForNewUserSignup(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }


    @Then("^I will receive valid response$")
    public void iWillReceiveValidResponse() {
        Assert.assertEquals(200, response.statusCode());
    }

    @When("^I verify message for change forgot password \"([^\"]*)\"$")
    public void iVerifyMessageForChangeForgotPassword(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @Given("^I set get emergency contact list service end point \"([^\"]*)\"$")
    public void iSetGetEmergencyContactListServiceEndPoint(String endpoint) {
        // JSONObject jsonObject = readJson("src/main/resources/data/getemergencylistbyuserid.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    //    @Given("^I set get emergency contact list service end point \"([^\"]*)\"$")
//    public void iSetGetEmergencyContactListServiceEndPoint(String endpoint) {
//        JSONObject jsonObject = readJson("src/main/resources/data/getemergencylistbyuserid.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .body(jsonObject.toJSONString())
//                .when()
//                .get(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
    @When("^I verify message for update existing customer address \"([^\"]*)\"$")
    public void iVerifyMessageForUpdateExistingCustomerAddress(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @When("^I verify message for addemergencycontact \"([^\"]*)\"$")
    public void iVerifyMessageForAddemergencycontact(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records to displayed");
    }

    @When("^I verify message for getemergencylist \"([^\"]*)\"$")
    public void iVerifyMessageForGetemergencylist(String msg) {
        Assert.assertEquals(response.jsonPath().get("message"), msg, "No records to display");
    }
    //    @When("^I verify message for getemergencylist \"([^\"]*)\"$")
//    public void iVerifyMessageForGetemergencylist(String msg) {
//        Assert.assertEquals(response.jsonPath().get("message"), msg, "No records to display");
//    }
//    @When("^I verify message for getemergencycontact \"([^\"]*)\"$")
//    public void iVerifyMessageForGetemergencycontact(String msg) {
//        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records to display");
//    }

    @Given("^I set update profile for the user and the service end point \"([^\"]*)\"$")
    public void iSetUpdateProfileForTheUserAndTheServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/updateprofile.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify the message for updateprofile user as \"([^\"]*)\"$")
    public void iVerifyTheMessageForUpdateprofileUserAs(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @Given("^I get list of latitude and logutide details with service end point \"([^\"]*)\"$")
    public void iGetListOfLatitudeAndLogutideDetailsWithServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify response mobile \"([^\"]*)\" name \"([^\"]*)\" message \"([^\"]*)\" displayed$")
    public void iVerifyResponseMobileNameMessageDisplayed(String mobile, String name, String msg) {
        Assert.assertEquals(response.jsonPath().get("mobile"), mobile, "No records displayed");
        Assert.assertEquals(response.jsonPath().get("fullname"), name, "No records displayed");
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records displayed");
    }

    @Given("^User able to update the order \"([^\"]*)\"$")
    public void userAbleToUpdateTheOrder(String endpoint) {
        WriteJson("src/main/resources/data/updateOrderStatus.json", "id", "94");
        WriteJson("src/main/resources/data/updateOrderStatus.json", "user_id", "159");
        WriteJson("src/main/resources/data/updateOrderStatus.json", "order_status", "Delivered");

        JSONObject jsonObject = readJson("src/main/resources/data/updateOrderStatus.json");
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .body(jsonObject.toJSONString())
                .when()
                .patch(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @When("^I verify message for updateOrderStatus \"([^\"]*)\"$")
    public void iVerifyMessageForUpdateOrderStatus(String msg) {
        Assert.assertEquals(response.jsonPath().get("message"), msg, "no records to display");
    }

    @Given("^I get list of order details from service end point \"([^\"]*)\"$")
    public void iGetListOfOrderDetailsFromServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I send emergency alert by service end point \"([^\"]*)\"$")
    public void iSendEmergencyAlertByServiceEndPoint(String endpoint) {
        JSONObject jsonObject = readJson("src/main/resources/data/sendEmergencyAlert.json");
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

    @When("^I verify the message for sendEmergencyAlert \"([^\"]*)\"$")
    public void iVerifyTheMessageForSendEmergencyAlert(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "no records to display");
    }

    @Given("^I send alert by service end point \"([^\"]*)\"$")
    public void iSendAlertByServiceEndPoint(String endpoint) {

        List<String> getmobilenumbers = response.jsonPath().get("contact_person_number");
        int max = getmobilenumbers.size();
        int min = 0;
        String getmobilenumber = getmobilenumbers.get((int) (Math.random() * (max - min + 1) + min));
        WriteJson("src/main/resources/data/checkmobile.json", "mobile", getmobilenumber);

        JSONObject jsonObject = readJson("src/main/resources/data/checkmobile.json");
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

    @When("^I verify the message for checkmobile \"([^\"]*)\"$")
    public void iVerifyTheMessageForCheckmobile(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "no records to display");
    }

    @Given("^I get savyo details list from service end point \"([^\"]*)\"$")
    public void iGetSavyoDetailsListFromServiceEndPoint(String endpoint) {
        response = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + getBearerToken)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        response.getBody().prettyPrint();
    }

    @Given("^I sent fetch by service end point \"([^\"]*)\"$")
    public void iSentFetchByServiceEndPoint(String endpoint) {
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

    @When("^I verify the message for gettheme \"([^\"]*)\"$")
    public void iVerifyTheMessageForGettheme(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "no records to display");
    }

    @Given("^I sent alert by service end point \"([^\"]*)\"$")
    public void iSentAlertByServiceEndPoint(String endpoint) {
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

    @When("^I verify the message for gethospitals \"([^\"]*)\"$")
    public void iVerifyTheMessageForGethospitals(String msg) {
        Assert.assertEquals(response.jsonPath().get("Message"), msg, "no records to display");
    }

    @Given("^I get product details by id service end point \"([^\"]*)\"$")
    public void iGetProductDetailsByIdServiceEndPoint(String endpoint) {

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

    @When("^I verify the product \"([^\"]*)\" details from the response\\.$")
    public void iVerifyTheProductDetailsFromTheResponse(String id) {
        List<String> getids = response.jsonPath().get("id");
        Assert.assertEquals(String.valueOf(getids.get(0)), id, "no records to display");

    }

    @When("^I verify the limit \"([^\"]*)\"  and offset \"([^\"]*)\" details from the response\\.$")
    public void iVerifyTheLimitAndOffsetDetailsFromTheResponse(String limit, String offset) {
        Assert.assertEquals(response.jsonPath().get("limit").toString(), limit, "limit value not matched");
        Assert.assertEquals(response.jsonPath().get("offset").toString(), offset, "limit value not matched");

    }

    // *************************************************** new  API  Release ********************************************************
//
//
//    @Given("^I Signup new Doctor details with \"([^\"]*)\" application service end point \"([^\"]*)\"$")
//    public void iSignupNewDoctorDetailsWithApplicationServiceEndPoint(String validOrInvalidData, String endpoint) throws Throwable {
//        JSONObject jsonObject;
//        if (validOrInvalidData.equalsIgnoreCase("Valid data")) {
//            jsonObject = readJson("src/main/resources/data/NewDoctorSignu.json");
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
//            //        jsonObject= readJson("src/main/resources/data/signup.json");
//            WriteJson("src/main/resources/data/signup.json", "mobile", GetRandomMobileNo());
//
//        } else if (validOrInvalidData.equalsIgnoreCase("Existing Phone Number")) {
//
//        } else if (validOrInvalidData.equalsIgnoreCase("Invalid data")) {
//
//        }
//    }
//
//    @When("^I will receive valid response (\\d+)$")
//    public void iWillReceiveValidResponse(int statuscode) {
//        if (statuscode==200){
//            Assert.assertEquals(statuscode, "Äctual status validate:"+response.statusCode());
//        }else if(statuscode==201){
//            Assert.assertEquals(statuscode, "Äctual status validate:"+response.statusCode());
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
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
}