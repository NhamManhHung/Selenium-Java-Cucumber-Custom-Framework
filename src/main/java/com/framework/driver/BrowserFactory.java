package com.framework.driver;

import com.framework.constants.FrameworkConstants;
import com.framework.exceptions.HeadlessNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", getCommonPrefs());
            setupCommonOptions(options);
            return options;
        }
    }, EDGE {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            setupCommonOptions(options);
            options.setExperimentalOption("prefs", getCommonPrefsWithAutofill());
            return options;
        }
    }, FIREFOX {
        @Override
        public WebDriver createDriver() {
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (HEADLESS) {
                options.addArguments("-headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            return options;
        }
    };

    private static final boolean HEADLESS = Boolean.parseBoolean(FrameworkConstants.HEADLESS);

    public abstract WebDriver createDriver();

    public abstract MutableCapabilities getOptions();

    private static Map<String, Object> getCommonPrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        return prefs;
    }

    private static Map<String, Object> getCommonPrefsWithAutofill() {
        Map<String, Object> prefs = getCommonPrefs();
        prefs.put("autofill.profile_enabled", false);
        return prefs;
    }

    private static void addCommonArguments(MutableCapabilities options) {
        options.setCapability("args", new String[]{
                "--disable-extensions",
                "--disable-infobars",
                "--disable-notifications",
                "--remote-allow-origins=*"
        });
    }

    private static void addHeadlessArguments(MutableCapabilities options) {
        options.setCapability("args", new String[]{
                "--headless=new",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1880,1000"
        });
    }

    private static void setupCommonOptions(MutableCapabilities options) {
        String[] commonArgs = {
                "--disable-extensions",
                "--disable-infobars",
                "--disable-notifications",
                "--remote-allow-origins=*"
        };

        if (options instanceof ChromeOptions co) co.addArguments(commonArgs);
        if (options instanceof EdgeOptions eo) eo.addArguments(commonArgs);

        if (HEADLESS) {
            if (options instanceof ChromeOptions co) co.addArguments("--headless=new", "--window-size=1880,1000");
            if (options instanceof EdgeOptions eo) eo.addArguments("--headless=new", "--window-size=1880,1000");
        }
    }

}