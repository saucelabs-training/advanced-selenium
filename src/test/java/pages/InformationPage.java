package test.java.pages;

import test.java.exceptions.PageValidationException;

public class InformationPage extends BasePage {
    public static InformationPage visit() {
        return new InformationPage();
    }

    public void submitInformation(String first, String last, String postal) {
    }

    public void addInformationSuccessfully(String first, String last, String postal) {
        submitInformation(first, last, postal);
        validateSuccessfulInformationAddition();
    }

    public void validateSuccessfulInformationAddition() {
        throw new PageValidationException("Need to implement validating adding information");
    }

    public boolean informationAddSuccessful() {
        return false;
    }

    public void addInformation() {
        // Don't use this one
    }
}
