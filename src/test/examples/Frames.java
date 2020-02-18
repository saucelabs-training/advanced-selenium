package test.examples;

import test.base.Base;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Frames extends Base {

    @Test
    @DisplayName("Example Code for Switching Between Frames")
    public void frameSwitching() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // "BOTTOM"
        driver.switchTo().frame(1);

        // Move up one context
        driver.switchTo().parentFrame();

        // ""
        driver.switchTo().frame("frame-top");

        // "LEFT"
        driver.switchTo().frame("frame-left");

        // Move back to top context
        driver.switchTo().defaultContent();
    }
}
