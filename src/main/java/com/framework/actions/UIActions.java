package com.framework.actions;

import com.framework.interfaces.IUIActions;
import com.framework.interfaces.IWaitActions;
import com.framework.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UIActions implements IUIActions {

    private final WebDriver driver;
    private final IWaitActions waitActions;
    private final double STEP_TIME = 0.5;

    public UIActions(WebDriver driver, IWaitActions waitActions) {
        this.driver = driver;
        this.waitActions = waitActions;
    }

    @Override
    public void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public Boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);

        if (!listElement.isEmpty()) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
    @Override
    public boolean checkElementExist(By by, int maxRetries, int waitTimeMillis) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement element = getWebElement(by);
                if (element != null) {
                    System.out.println("Found element at " + (retryCount + 1) + "retry");
                    return true; // Phần tử được tìm thấy
                }
            } catch (NoSuchElementException e) {
                System.out.println("Not found. Try again " + (retryCount + 1));
                retryCount++;
                try {
                    Thread.sleep(waitTimeMillis); // Chờ trước khi thử lại
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        // Trả về false nếu không tìm thấy phần tử sau maxRetries lần
        LogUtils.warn("Not found element after " + maxRetries + " retry");
        return false;
    }

    @Override
    public void openURL(String url) {
        driver.get(url);
        sleep(STEP_TIME);
        LogUtils.info("Open URL:  " + url);
    }

    @Override
    public void clickElement(By by) {
        waitActions.waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click on element " + by);
    }

    @Override
    public void clickElement(By by, int timeout) {
        waitActions.waitForElementClickable(by, timeout);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("Click on element " + by);
    }

    @Override
    public void setText(By by, String value) {
        waitActions.waitForElementVisible(by);
        sleep(STEP_TIME);
        WebElement element = getWebElement(by);
        element.clear();
        element.sendKeys(value);
        LogUtils.info("Set text " + value + " on element " + by);
    }

    @Override
    public String getElementText(By by) {
        waitActions.waitForElementVisible(by);
        sleep(STEP_TIME);
        LogUtils.info("Get text of element " + by);
        String text = getWebElement(by).getText();
        LogUtils.info("==> TEXT: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    @Override
    public String getElementAttribute(By by, String attributeName) {
        waitActions.waitForElementVisible(by);
        System.out.println("Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        System.out.println("==> Attribute value: " + value);
        return value;
    }

    @Override
    public String getElementCssValue(By by, String cssPropertyName) {
        waitActions.waitForElementVisible(by);
        System.out.println("Get CSS value " + cssPropertyName + " of element " + by);
        String value = getWebElement(by).getCssValue(cssPropertyName);
        System.out.println("==> CSS value: " + value);
        return value;
    }
}
