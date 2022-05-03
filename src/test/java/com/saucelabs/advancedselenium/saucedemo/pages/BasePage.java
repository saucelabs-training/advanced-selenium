package com.saucelabs.advancedselenium.saucedemo.pages;


import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import com.saucelabs.advancedselenium.saucedemo.data.BaseData;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class BasePage {
    protected Browser browser;
    protected SauceDemoApp app;
    private final Set<Field> elements = new HashSet<>();

    public BasePage(SauceDemoApp app) {
        this.browser = app.getBrowser();
        this.app = app;

        for (Field field : this.getClass().getDeclaredFields()) {
            if (Element.class.isAssignableFrom(field.getType())) {
                elements.add(field);
            }
        }
    }

    public void submitForm(BaseData data) {
        fillForm(data);
        Optional<Field> submit = elements.stream().filter(field -> field.getName().equals("submit")).findFirst();
        if (submit.isPresent()) {
            Field field = submit.get();
            field.setAccessible(true);
            try {
                ((Element) field.get(this)).click();
            } catch (IllegalAccessException e) {
                throw new ElementValidationException("Unable to submit form", e);
            }
        } else {
            throw new ElementValidationException("No element named 'submit' to click to submit form");
        }
    }

    public void fillForm(BaseData data) {
        for (Field field : elements) {
            if (field.getType().equals(TextField.class)) {
                field.setAccessible(true);
                String dataValue = (String) data.getValue(field.getName());
                try {
                    ((TextField) field.get(this)).sendKeys(dataValue);
                } catch (IllegalAccessException e) {
                    throw new ElementValidationException("Unable to automatically fill out form", e);
                }
            }
        }
    }
}
