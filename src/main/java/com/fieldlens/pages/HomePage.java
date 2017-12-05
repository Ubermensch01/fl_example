package com.fieldlens.pages;

import com.fieldlens.pageobjects.UserAccountMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:28 PM
 */
public class HomePage extends BasePage {

    private static final By topBarLocator = By.cssSelector("div.top-bar");
    private static final By userAccountMenuLocator = By.cssSelector("ui-select.top-bar--user-menu");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public UserAccountMenu getUserAccountMenu() {
        return new UserAccountMenu(getDriver());
    }

    @Step
    public boolean isTopBarDisplayed() {
        boolean result = waitForElementToBeVisible(topBarLocator);
        screenshot(topBarLocator);
        return result;
    }
}
