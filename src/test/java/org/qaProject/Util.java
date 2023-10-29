package org.qaProject;

import org.openqa.selenium.WebElement;

public class Util {

    public static void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void clickFor(WebElement element, int quantity) {
        for (int i = 1; i < quantity; i++) {
            element.click();
        }
    }


}
