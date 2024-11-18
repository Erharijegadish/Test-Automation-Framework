package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase {

    @Test(description = "Verifies with valid user is able to login into application", groups = {"e2e","sanity"},
    dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName()
                ,"Hari Jegadish");
    }

    @Test(description = "Verifies with valid user is able to login into application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName()
                ,"Hari Jegadish");
    }

    @Test(description = "Verifies with valid user is able to login into application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
    retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginExcelTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName()
                ,"Hari Jegadish");
    }
}
