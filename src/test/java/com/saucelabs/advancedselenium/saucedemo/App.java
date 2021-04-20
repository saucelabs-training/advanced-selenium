package test.java.com.saucelabs.advancedselenium.saucedemo;

import com.google.gson.Gson;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private final RemoteWebDriver driver;

    public App(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        driver.manage().addCookie(new Cookie("session-username", "standard_user"));
    }

    public void addInventory(int repeats) {
        List<Integer> contents = new ArrayList<>();

        List<Integer> numbers = new ArrayList<>(5);
        for (int i = 0; i < 6; i++)
            numbers.add(i);
        Collections.shuffle(numbers);

        for (int i=0; i<repeats;i++) {
            contents.add(numbers.remove(0));
        }
        getLocalStorage().setItem("cart-contents", String.valueOf(contents));
    }

    public void addInformation() {
        // This doesn't actually do anything in the app right now, so nothing to fake
    }

    public boolean isAuthenticated() {
        Cookie cookie = driver.manage().getCookieNamed("session-username");
        return cookie != null;
    }

    public int cartItems() {
        String contents = getLocalStorage().getItem("cart-contents");
        return new Gson().fromJson(contents, ArrayList.class).size();
    }

    private LocalStorage getLocalStorage() {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        return webStorage.getLocalStorage();
    }
}
