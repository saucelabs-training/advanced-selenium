package test.java;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SauceLabsConfigurationTest {
    RemoteWebDriver driver = null;
    SauceSession session = null;

    @Test
    public void togglePlatform() throws MalformedURLException, FileNotFoundException {
        ChromeOptions chromeOptions = new ChromeOptions();

        if (System.getenv("SELENIUM_PLATFORM") == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        } else if (System.getenv("SELENIUM_PLATFORM").equals("SAUCE")) {
            driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), getCapabilities());
        } else if (System.getenv("SELENIUM_PLATFORM").equals("GRID")) {
            URL url = new URL("https://remote-grid-machine-url/wd/hub");
            driver = new RemoteWebDriver(url, chromeOptions);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
    }

    private MutableCapabilities getCapabilities() throws FileNotFoundException {
        MutableCapabilities capabilities = new MutableCapabilities();

        Map<String, Object> platform = getPlatform();
        createBrowserOptions(capabilities, platform);
        createSauceOptions(capabilities, platform);
        createDefaultOptions(capabilities, platform);

        return capabilities;
    }

    private void createDefaultOptions(MutableCapabilities capabilities, Map<String, Object> platform) {
        for (Map.Entry<String, Object> entry : platform.entrySet()) {
            capabilities.setCapability(entry.getKey(), entry.getValue());
        }
    }

    private void createSauceOptions(MutableCapabilities capabilities, Map<String, Object> platform) {
        SauceOptions sauceOptions = new SauceOptions();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) platform.remove("sauce")).entrySet()) {
            sauceOptions.setCapability(entry.getKey(), entry.getValue());
        }
        capabilities.setCapability("sauce:options", sauceOptions);
    }

    private void createBrowserOptions(MutableCapabilities capabilities, Map<String, Object> platform) {
        ChromeOptions chromeOptions = new ChromeOptions();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) platform.remove("chrome")).entrySet()) {
            chromeOptions.setCapability(entry.getKey(), entry.getValue());
        }
        capabilities.setCapability("goog:chromeOptions", chromeOptions);
    }

    private Map<String, Object> getPlatform() throws FileNotFoundException {
        Yaml yaml = new Yaml();

        InputStream file = new FileInputStream("src/test/config/platforms.yml");
        Object platformData = yaml.load(file);

        Map<String, Object> platforms = (Map<String, Object>) platformData;

        return (Map<String, Object>) platforms.get(System.getProperty(("PLATFORM")));
    }

    @After
    public void endSession() {
        if (session != null) {
            session.stop(true);
        } else if (driver != null) {
            driver.quit();
        }
    }
}
