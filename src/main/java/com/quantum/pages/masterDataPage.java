package com.quantum.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import org.openqa.selenium.support.FindBy;

public class masterDataPage  extends WebDriverBaseTestPage<WebDriverTestPage> {


    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {

    }
    @FindBy(xpath="//h6[text()='Master Data']")
    QAFExtendedWebElement lblmasterdata;

    @FindBy(xpath = "//table[@class='MuiTable-root']//tbody//tr")
    QAFExtendedWebElement tablerows;

    @FindBy(xpath = "//ul[@class='pagination justify-content-center']//*[@data-icon='forward-step']")
    QAFExtendedWebElement btnfroward;
    @FindBy(xpath = "//ul[@class='pagination justify-content-center']//*[@data-icon='backward-step']")
    QAFExtendedWebElement btnbackward;
    @FindBy(xpath = "//ul[@class='pagination justify-content-center']//*[@data-icon='caret-right']")
    QAFExtendedWebElement btncareright;
    @FindBy(xpath = "//ul[@class='pagination justify-content-center']//*[@data-icon='caret-left']")
    QAFExtendedWebElement btncaretleft;
    @FindBy(xpath = "//button[@class='add_new_donor']")
    QAFExtendedWebElement btnaddnewdonor;

    @FindBy(xpath = "//p[text()='Add New Master Data']")
    QAFExtendedWebElement lblAddNewMasterData;

    @FindBy(xpath="select[@id='state']")
    QAFExtendedWebElement drpdownstate;

    @FindBy(xpath="select[@id='district']")
    QAFExtendedWebElement drpdowndistrict;

    @FindBy(xpath="select[@id='city']")
    QAFExtendedWebElement drpdowncity;

    @FindBy(xpath ="button[@type='submit']" )
    QAFExtendedWebElement btnsubmit;








}
