package com.quantum.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;

import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import org.openqa.selenium.support.FindBy;

public class AllLeftMenuPage  extends WebDriverBaseTestPage<WebDriverTestPage> {


    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {

    }

    @FindBy(xpath="//nav[@class='menu']//span[text()='Master Data']")
    QAFExtendedWebElement menuMasterData;


    public void clickmenuMasterData(){
        menuMasterData.click();

    }
}
