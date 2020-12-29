package test.java.pages;

public class HomePageMethods extends OldBasePage {
    private final HomePageElements elements;

    public HomePageMethods() {
        this.elements = new HomePageElements();
    }

    public HomePageMethods navigateTo() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public InventoryPage login(String username, String password) {
        elements.getUsername().sendKeys(username);
        elements.getPassword().sendKeys(password);
        elements.getSubmit().click();
        return new InventoryPage();
    }
}
