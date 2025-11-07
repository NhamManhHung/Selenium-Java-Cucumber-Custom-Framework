package com.framework.interfaces;

import org.openqa.selenium.By;

public interface IVerifyActions {
    boolean verifyEquals(Object actual, Object expected);

    void assertEquals(Object actual, Object expected, String message);

    boolean verifyContains(String actual, String expected);

    void assertContains(String actual, String expected, String message);

    boolean verifyVisible(By locator);

    void assertVisible(By locator, String message);
}
