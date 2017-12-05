package com.fieldlens.login;

import com.fieldlens.BaseTest;
import com.fieldlens.entities.Credentials;
import com.fieldlens.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:53 PM
 */
public class FunctionalTests extends BaseTest {

    private HomePage page;

    @BeforeClass
    public void setUp(ITestContext context) {
        super.setUp(context.getCurrentXmlTest().getParameter("browser"));
        page = getLandingPage()
                .loginAs(Credentials.DEFAULT_USER);
    }

    @Test(description = "Verify home page is displayed")
    @Description("This test verifies user is redirected to the home page after successful login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyHomePageIsDisplayed() {
        // This assertion verifies that the user is seeing a crucial element of the homepage which implies homepage is loaded.
        Assert.assertTrue(page.isTopBarDisplayed(), "Top Bar is not displayed, user is not redirected to the home page");
    }

    @Test(description = "Verify session cookies are stored")
    @Description("This test verifies cookies are stored after successful login ensuring further unobstructed access to authentication-restricted pages")
    @Severity(SeverityLevel.CRITICAL)
    public void verifySessionCookies() {
        Assert.assertTrue(getDriver().manage().getCookieNamed("JSESSIONID") != null, "No authentication cookies were stored after login");
        attach(getDriver().manage().getCookieNamed("JSESSIONID").toString());
    }
}
