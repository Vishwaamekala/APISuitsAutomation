package com.quantum.pages;


import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class suitshealthdev_loginPage extends WebDriverBaseTestPage<WebDriverTestPage> {


    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {

    }

    @FindBy(xpath = "//input[@id='email-login']")
    private QAFExtendedWebElement txtusername;


    @FindBy(xpath = "//input[@id='-password-login']")
    private QAFExtendedWebElement txtpassword;

    @FindBy(xpath = "//button[@type='submit']")
    private QAFExtendedWebElement btnlogin;

    @FindBy(xpath = "//img[@alt='logo']")
    private QAFExtendedWebElement image;


    public void loginwithvaliddata(String username, String password) {
        txtusername.sendKeys(username);
        txtpassword.sendKeys(password);
        btnlogin.waitForVisible(3000);
        btnlogin.click();
//        waitForTextPresent("admin");
    }

    public void landingpagevalidation() {
        image.waitForVisible(3000);
        Assert.assertTrue(image.isDisplayed(), "Page logo visiable");

    }

}
