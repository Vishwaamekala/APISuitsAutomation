package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.quantum.pages.suitshealthdev_loginPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@QAFTestStepProvider
public class LoginStepDef {

    suitshealthdev_loginPage loginPage = new suitshealthdev_loginPage();

    @Given("^As a admin user i need to open the url \"([^\"]*)\"$")
    public void asAAdminUserINeedToOpenTheUrl(String url) {

        new WebDriverTestBase().getDriver().manage().window().maximize();
        new WebDriverTestBase().getDriver().get(url);


    }
    @When("^As a user i need enter \"([^\"]*)\"  \"([^\"]*)\"  and click on login button$")
    public void asAUserINeedEnterAndClickOnLoginButton(String username, String password) {
        loginPage.loginwithvaliddata(username,password);
    }

    @Then("As a user i need to verify landingPage")
    public void asAUserINeedToVerifyLandingPage() {
        loginPage.landingpagevalidation();

    }



}
