package com.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IWaitActions {
    WebElement waitForElementVisible(By by);

    void waitForElementVisible(By by, int timeOut);

    void waitForElementPresent(By by);

    void waitForElementPresent(By by, int timeOut);

    void waitForElementClickable(By by);

    void waitForElementClickable(By by, int timeOut);

    void waitForPageLoaded();
}
