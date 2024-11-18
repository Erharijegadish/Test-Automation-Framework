package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;
import static com.utility.JsonUtility.*;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign')]");
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless); // To call the parent class constructor from the child constructor
        goToWebsite(readProperty(QA, "URL"));
//        goToWebsite(readJson(QA).getUrl());
    }

    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(readJson(QA).getUrl());
    }

    //page function
    public LoginPage goToLoginPage() {
        logger.info("Trying to performing click to go to SignIn page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }
}
