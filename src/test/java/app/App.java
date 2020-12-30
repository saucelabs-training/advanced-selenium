package test.java.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import test.java.data.address.User;
import test.java.pages.address.NavBar;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private final WebDriver driver;

    public App(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAuthenticated(User user) {
        driver.navigate().refresh();
        return new NavBar().isLoggedIn(user);
    }

    public boolean isAuthenticated() {
        return driver.manage().getCookieNamed("remember_token") != null;
    }

    public void authenticateNewUser() {
        authenticateUser(new User());
    }

    public void authenticateUser(User user) {
        driver.get("http://a.testaddressbook.com");

        Response post = createNewUser(user);
        String value = post.headers().asList().get(9).getValue();
        Pattern p = Pattern.compile("[0-9a-fA-F]{40}");
        Matcher m = p.matcher(value);
        m.find();
        String rememberMeToken = m.group();

        Cookie rememberToken = new Cookie("remember_token", rememberMeToken);
        driver.manage().addCookie(rememberToken);
        driver.navigate().refresh();
    }

    public User createNewUser() {
        User user = new User();
        createNewUser(user);
        return user;
    }

    private Response createNewUser(User user) {
        String apiUrl = "http://a.testaddressbook.com/users";
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        Map<String, String> payload = new HashMap<>();
        payload.put("email", user.getEmail());
        payload.put("password", user.getPassword());

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl);
    }
}
