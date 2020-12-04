package test.java;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceTestBase {
    public RemoteWebDriver driver = null;
    public SauceSession session = null;

    @Rule
    public SauceTestWatcher sauceTestWatcher = new SauceTestWatcher();

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };

    @Before
    public void startSession() {
        ChromeOptions chromeOptions = new ChromeOptions();

        SauceOptions sauceOptions = new SauceOptions(chromeOptions);
        sauceOptions.setName(System.getenv("SAUCE_USERNAME") + " - " + name.getMethodName());

        session = new SauceSession(sauceOptions);
        sauceTestWatcher.setSession(session);
        driver = session.start();
    }

    private static class SauceTestWatcher extends TestWatcher {
        private SauceSession session;

        public void setSession(SauceSession session) {
            this.session = session;
        }

        @Override
        protected void failed(Throwable e, Description description) {
            session.stop(false);
        }

        @Override
        protected void succeeded(Description description) {
            session.stop(true);
        }

    }


}
