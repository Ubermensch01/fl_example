package com.fieldlens.login;

import com.fieldlens.BaseTest;
import com.fieldlens.entities.Credentials;
import com.fieldlens.pages.LoginPage;
import com.fieldlens.tools.StringUtilities;
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
 * Date: 9/14/17
 * Time: 4:57 PM
 */
public class NegativeTests extends BaseTest {

    @BeforeClass
    public void setUp(ITestContext context) {
        super.setUp(context.getCurrentXmlTest().getParameter("browser"));
    }

    @Test(description = "Verify credentials with incorrect password are not authenticated")
    @Description("This test verifies a validation message appear when a user attempts login with an invalid password")
    public void invalidPassword() {
        getLandingPage()
                .loginAs(Credentials.DEFAULT_USER.getUsername(), Credentials.DEFAULT_USER.getPassword() + StringUtilities.generateAlpha(3));
        LoginPage page = new LoginPage(getDriver());
        Assert.assertEquals(page.getValidationMessage(), "UNAUTHORIZED!", "Validation message is incorrect");
    }

    @Test(description = "Verify credentials with inexistent emails are not authenticated")
    @Description("This test verifies a validation message appear when a user attempts login with an invalid email")
    public void invalidEmail() {
        getLandingPage()
                .loginAs(StringUtilities.generateAlpha(3) + Credentials.DEFAULT_USER.getUsername(), Credentials.DEFAULT_USER.getPassword());
        LoginPage page = new LoginPage(getDriver());
        Assert.assertEquals(page.getValidationMessage(), "UNAUTHORIZED!", "Validation message is incorrect");
    }

    @Test(description = "Verify session cookies are not stored")
    @Description("This test verifies cookies are not stored after unsuccessful login")
    @Severity(SeverityLevel.CRITICAL)
    public void verifySessionCookies() {
        getLandingPage()
                .loginAs(
                        Credentials.DEFAULT_USER.getUsername(),
                        Credentials.DEFAULT_USER.getPassword() + StringUtilities.generateAlpha(3));
        Assert.assertTrue(getDriver().manage().getCookieNamed("JSESSIONID") == null, "Authentication cookies were stored after failed login");
    }
}
