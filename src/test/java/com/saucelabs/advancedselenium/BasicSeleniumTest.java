package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class BasicSeleniumTest {

	@Test
	public void seleniumDemo() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.saucedemo.com");
		assertEquals(driver.getTitle(), "Swag Labs");
		driver.quit();
	}
}
