package test.java.browser;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

import java.io.File;
import java.io.IOException;

public class FileUploadTest extends SauceTestBase {

    @Test
    public void uploadFile() throws IOException {
        // FileDetector Required when using Remote WebDriver
        // driver.setFileDetector(new LocalFileDetector());

        driver.get("http://the-internet.herokuapp.com/upload");

        WebElement inputElement = driver.findElement(By.id("file-upload"));
        File file = new File("lib/drivers/chromedriver");
        inputElement.sendKeys(file.getCanonicalPath());
    }

}

