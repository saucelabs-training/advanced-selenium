package com.saucelabs.advancedselenium.saucedemo.data;

import net.datafaker.Faker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class BaseData {
    protected final Faker faker = new Faker();
    private final Set<String> keys = new HashSet<>();

    public BaseData() {
        for (Field field : this.getClass().getDeclaredFields()) {
            keys.add(field.getName());
        }
    }

    public Object getValue(String key) {
        try {
            String getter = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = this.getClass().getDeclaredMethod(getter);
            return method.invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> getKeys() {
        return keys;
    }
}
