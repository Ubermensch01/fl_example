package com.fieldlens.pages;

import com.fieldlens.entities.Credentials;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:26 PM
 */
public class LoginPage extends BasePage {
    private static final By usernameInputLocator = By.cssSelector("input#login-email");
    private static final By passwordInputLocator = By.cssSelector("input#password");
    private static final By loginButtonLocator = By.cssSelector("div.sign-in button");
    private static final By loginPanelLocator = By.cssSelector("div.login.fl-panel-login");
    private static final By validationMessageLocator = By.cssSelector("div.message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Log in with provided credentials")
    public HomePage loginAs(Credentials user) {
        type(user.getUsername(), usernameInputLocator);
        type(user.getPassword(), passwordInputLocator);
        click(loginButtonLocator);
        screenshot(loginPanelLocator);

        return new HomePage(getDriver());
    }

    @Step("Log in with provided credentials")
    public HomePage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        screenshot(loginPanelLocator);
        clickLogin();

        return new HomePage(getDriver());
    }

    public String getValidationMessage() {
        return find(validationMessageLocator).getText();
    }

    @Step("Enter username")
    public LoginPage enterUsername(String username) {
        type(username, usernameInputLocator);

        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        type(password, passwordInputLocator);

        return this;
    }

    @Step("Click 'Log in' button")
    public void clickLogin() {
        click(loginButtonLocator);
        waitForElementToDisappear(By.cssSelector(".fa-spinner"));
    }
}
