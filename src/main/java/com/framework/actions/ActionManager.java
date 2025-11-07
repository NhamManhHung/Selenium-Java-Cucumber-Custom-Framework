package com.framework.actions;

import com.framework.interfaces.IMoveActions;
import com.framework.interfaces.IUIActions;
import com.framework.interfaces.IVerifyActions;
import com.framework.interfaces.IWaitActions;
import org.openqa.selenium.WebDriver;

public class ActionManager {
    public final IWaitActions waitActions;
    public final IVerifyActions verifyActions;
    public final IMoveActions moveActions;
    public final IUIActions uiActions;

    public ActionManager(WebDriver driver) {
        this.waitActions = new WaitActions(driver);
        this.uiActions = new UIActions(driver, waitActions);
        this.moveActions = new MoveActions(driver, waitActions, uiActions);
        this.verifyActions = new VerifyActions(waitActions);
    }
}
