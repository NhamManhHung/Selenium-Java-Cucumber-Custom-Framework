package com.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface IUIActions {
    void sleep(double second);

    WebElement getWebElement(By by);

    List<WebElement> getWebElements(By by);

    Boolean checkElementExist(By by);

    // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
    boolean checkElementExist(By by, int maxRetries, int waitTimeMillis);

    void openURL(String url);

    void clickElement(By by);

    void clickElement(By by, int timeout);

    void setText(By by, String value);

    String getElementText(By by);

    String getElementAttribute(By by, String attributeName);

    String getElementCssValue(By by, String cssPropertyName);
}
