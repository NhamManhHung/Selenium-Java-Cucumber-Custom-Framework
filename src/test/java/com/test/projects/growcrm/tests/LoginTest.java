package com.test.projects.growcrm.tests;

import com.test.common.BaseTest;
import com.test.projects.growcrm.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccessfully() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }
}
