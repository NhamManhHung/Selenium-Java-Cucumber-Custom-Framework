package com.test.projects.growcrm.pages;

import com.framework.actions.ActionManager;
import com.framework.driver.DriverManager;
import org.openqa.selenium.By;

public class LoginPage extends ActionManager {

    public LoginPage() {
        super(DriverManager.getDriver());
    }

    private final By txtUsername = By.id("email");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.id("loginSubmitButton");
    private final By lblWelcome = By.id("main-top-nav-bar");

    public void enterUsername(String username) {
        uiActions.setText(txtUsername, username);
    }

    public void enterPassword(String password) {
        uiActions.setText(txtPassword, password);
    }

    public void clickLoginButton() {
        uiActions.clickElement(btnLogin);
    }

    public void verifyWelcomePage() {
        verifyActions.assertVisible(lblWelcome, "Welcome banner should be displayed");
    }

    public void login() {
        uiActions.openURL("https://demo.growcrm.io/login");
        enterUsername("admin@example.com");
        enterPassword("growcrm");
        clickLoginButton();
        verifyWelcomePage();
    }
}
