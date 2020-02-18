package test.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class StdoutTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        System.out.println("Test Failed");
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        System.out.println("Test Succeeded");
    }
}
