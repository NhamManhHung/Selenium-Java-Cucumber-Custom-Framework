package com.framework.driver;

import com.framework.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;

public final class DriverFactory {

    public static void intiDriver() {
        if (DriverManager.getDriver() == null) {
            String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                    : FrameworkConstants.BROWSER;
            WebDriver webdriver = BrowserFactory.valueOf(browserName.toUpperCase()).createDriver();
            webdriver.manage().window().maximize();
            DriverManager.setDriver(webdriver);
        }
    }
}