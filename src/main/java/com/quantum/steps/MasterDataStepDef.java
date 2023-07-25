package com.quantum.steps;

import com.quantum.pages.AllLeftMenuPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class MasterDataStepDef {

    AllLeftMenuPage allLeftMenuPage = new AllLeftMenuPage();

    @Then("^As user i need verify \"([^\"]*)\" menu item and click$")
    public void asUserINeedVerifyMenuItemAndClick(String MasterData) {
        allLeftMenuPage.clickmenuMasterData();


    }

    @And("^As user i need verify MasterData details$")
    public void asUserINeedVerifyMasterDataDetails() {

    }
}
