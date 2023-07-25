//package com.quantum.steps;
//
//import com.quantum.utils.Jsonutility;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.testng.Assert;
//
//import static io.restassured.RestAssured.given;
//
//public class ApiRegressionTestPhase3Extold {
//
//    public static Response response;
//    public static String getBearerToken = null;
//    JSONObject jsonObject;
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
//    @Given("^As user i can search for differnet  products with application service end point \"([^\"]*)\"$")
//    public void asUserICanSearchForDiffernetProductsWithApplicationServiceEndPoint(String endpoint) {
//        JSONObject productdetails = Jsonutility.readJson("src/main/resources/data/SuitePhase3ext/searchproduct.json");
//        response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", "Bearer " + getBearerToken)
//                .and()
//                .body(productdetails.toJSONString())
//                .when()
//                .post(endpoint)
//                .then()
//                .extract().response();
//        response.getBody().prettyPrint();
//    }
//    @When("^I validate response with message \"([^\"]*)\"$")
//    public void iValidateTheResponseWithMessage(String msg) throws Throwable {
//        Assert.assertEquals(response.jsonPath().get("Message"), msg, "No records Fetched");
//    }
//}
