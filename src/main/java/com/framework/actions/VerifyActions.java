package com.framework.actions;

import com.framework.interfaces.IVerifyActions;
import com.framework.interfaces.IWaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class VerifyActions implements IVerifyActions {
    private final IWaitActions waitActions;

    public VerifyActions(IWaitActions waitActions) {
        this.waitActions = waitActions;
    }

    @Override
    public boolean verifyEquals(Object actual, Object expected) {
        waitActions.waitForPageLoaded();
        System.out.println("Verify equals: " + actual + " and " + expected);
        return actual.equals(expected);
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        waitActions.waitForPageLoaded();
        System.out.println("Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    @Override
    public boolean verifyContains(String actual, String expected) {
        waitActions.waitForPageLoaded();
        System.out.println("Verify contains: " + actual + " and " + expected);
        return actual.contains(expected);
    }

    @Override
    public void assertContains(String actual, String expected, String message) {
        waitActions.waitForPageLoaded();
        System.out.println("Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }


    @Override
    public boolean verifyVisible(By locator) {
        waitActions.waitForPageLoaded();
        WebElement element = waitActions.waitForElementVisible(locator);
        boolean visible = element != null && element.isDisplayed();
        System.out.println("Verify visible: " + locator + " -> " + visible);
        return visible;
    }

    @Override
    public void assertVisible(By locator, String message) {
        waitActions.waitForPageLoaded();
        WebElement element = waitActions.waitForElementVisible(locator);
        boolean visible = element != null && element.isDisplayed();
        System.out.println("Assert visible: " + locator + " -> " + visible);
        Assert.assertTrue(visible, message);
    }
}
