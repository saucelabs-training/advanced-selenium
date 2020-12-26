package test.java.pages;

import test.java.exceptions.PageValidationException;

public class OverviewPage extends BasePage {
    public void finishSuccessfully() {
        throw new PageValidationException("Need to implement successful finish");
    }
}
