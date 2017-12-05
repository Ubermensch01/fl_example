package com.fieldlens;

import com.fieldlens.pages.LoginPage;
import com.fieldlens.tools.Config;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:23 PM
 */
public class BaseTest {

    private WebDriver driver;

    @Parameters({"browser"})
    protected void setUp(@Optional("ff") String browser) {
        URL grid = null;
        try {
            grid = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BrowserManager driverManager;
        switch (browser) {
            case "ff":
                driverManager = FirefoxDriverManager.getInstance();
                driverManager.setup();
                System.out.println(String.format("Firefox driver version : %s", driverManager.getDownloadedVersion()));
                driver = new FirefoxDriver();
                break;
            case "grid-ff":
                driver = new RemoteWebDriver(grid, DesiredCapabilities.firefox());
                break;
            case "grid-chrome":
                driver = new RemoteWebDriver(grid, DesiredCapabilities.chrome());
                break;
            default:
            case "chrome":
                driverManager = ChromeDriverManager.getInstance();
                driverManager.setup();
                System.out.println(String.format("Chrome driver version : %s", driverManager.getDownloadedVersion()));
                driver = new ChromeDriver();
                break;
        }
    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
        driver = null;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected LoginPage getLandingPage() {
        driver.get(Config.instance().getAppUrl());

        return new LoginPage(getDriver());
    }

    @Attachment(value = "Attachment")
    protected String attach(String data) {
        return data;
    }
}
