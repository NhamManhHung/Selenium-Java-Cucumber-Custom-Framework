package com.framework.actions;

import com.framework.driver.DriverManager;
import com.framework.interfaces.IMoveActions;
import com.framework.interfaces.IUIActions;
import com.framework.interfaces.IWaitActions;
import com.framework.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MoveActions implements IMoveActions {

    private final WebDriver driver;
    private final IWaitActions waitActions;
    private final IUIActions uiActions;

    public MoveActions(WebDriver driver, IWaitActions waitActions, IUIActions uiActions) {
        this.driver = driver;
        this.waitActions = waitActions;
        this.uiActions = uiActions;
    }

    @Override
    public void setTextAndKey(By by, String value, Keys key) {
        waitActions.waitForPageLoaded();
        uiActions.getWebElement(by).sendKeys(value, key);
        System.out.println("Set text: " + value + " on element " + by);
    }

    @Override
    public void scrollToElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", uiActions.getWebElement(by));
    }

    @Override
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    @Override
    public void scrollToElementAtTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", uiActions.getWebElement(by));
    }

    @Override
    public void scrollToElementAtBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", uiActions.getWebElement(by));
    }

    @Override
    public void scrollToElementAtTop(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public void scrollToElementAtBottom(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    @Override
    public void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    @Override
    public boolean moveToElement(By by) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(uiActions.getWebElement(by)).release(uiActions.getWebElement(by)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(driver);
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hoverElement(By by) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(uiActions.getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean mouseHover(By by) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(uiActions.getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(driver);
            action.dragAndDrop(uiActions.getWebElement(fromElement), uiActions.getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(driver);
            action.clickAndHold(uiActions.getWebElement(fromElement)).moveToElement(uiActions.getWebElement(toElement)).release(uiActions.getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(driver);
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(uiActions.getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    @Override
    public WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", uiActions.getWebElement(by));
        }
        return uiActions.getWebElement(by);
    }
}
