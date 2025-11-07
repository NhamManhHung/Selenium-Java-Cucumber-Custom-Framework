package com.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface IMoveActions {
    void setTextAndKey(By by, String value, Keys key);

    void scrollToElement(By by);

    void scrollToElement(WebElement element);

    void scrollToElementAtTop(By by);

    void scrollToElementAtBottom(By by);

    void scrollToElementAtTop(WebElement element);

    void scrollToElementAtBottom(WebElement element);

    void scrollToPosition(int X, int Y);

    boolean moveToElement(By by);

    boolean moveToOffset(int X, int Y);

    boolean hoverElement(By by);

    boolean mouseHover(By by);

    boolean dragAndDrop(By fromElement, By toElement);

    boolean dragAndDropElement(By fromElement, By toElement);

    boolean dragAndDropOffset(By fromElement, int X, int Y);

    boolean pressENTER();

    boolean pressESC();

    boolean pressF11();

    WebElement highLightElement(By by);
}
