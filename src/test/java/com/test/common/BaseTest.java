package com.test.common;

import com.framework.actions.ActionManager;
import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest extends ActionManager {

    public BaseTest() {
        super(DriverManager.getDriver());
    }

    @Parameters("BROWSER")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("edge") String browser, Method method) {
        DriverFactory.intiDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

//    public WebDriver createBrowser(@Optional("chrome") String browser) {
//        PropertiesHelpers.loadAllFiles();
//        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
//        DriverManager.setDriver(driver);
//        return DriverManager.getDriver();
//    }

}

