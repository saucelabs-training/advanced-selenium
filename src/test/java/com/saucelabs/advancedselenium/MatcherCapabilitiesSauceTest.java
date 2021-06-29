package com.saucelabs.advancedselenium;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.WebDriverException;

import static org.junit.Assert.assertTrue;

public class MatcherCapabilitiesSauceTest {

    @Test
    public void sauceMatchers() {
        SauceOptions options = SauceOptions.chrome()
                .setBrowserVersion("Bad")
                .build();
        SauceSession session = new SauceSession(options);

        String sauceMessage = "Misconfigured -- Unsupported OS/browser/version/device combo";
        try {
            session.start();
            session.stop(false);
        } catch (WebDriverException ex) {
            assertTrue(ex.getMessage().contains(sauceMessage));
        }
    }
}


