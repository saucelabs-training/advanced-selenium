package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;

public class SauceDemoPageFactory {
    private final SauceDemoApp app;
    private HomePage homePage;
    private InventoryPage inventoryPage;
    private InformationPage informationPage;
    private CheckoutPage checkoutPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private HeaderSection headerSection;

    public SauceDemoPageFactory(SauceDemoApp app) {
        this.app = app;
    }
    public HomePage getHomePage() {
        if (this.homePage == null) {
            this.homePage = new HomePage(app);
        }
        return this.homePage;
    }

    public InventoryPage getInventoryPage() {
        if (this.inventoryPage == null) {
            this.inventoryPage = new InventoryPage(app);
        }
        return this.inventoryPage;
    }

    public InformationPage getInformationPage() {
        if (this.informationPage == null) {
            this.informationPage = new InformationPage(app);
        }
        return this.informationPage;
    }

    public CheckoutPage getCheckoutPage() {
        if (this.checkoutPage == null) {
            this.checkoutPage = new CheckoutPage(app);
        }
        return this.checkoutPage;
    }

    public ProductPage getProductPage() {
        if (this.productPage == null) {
            this.productPage = new ProductPage(app);
        }
        return this.productPage;
    }

    public CartPage getCartPage() {
        if (this.cartPage == null) {
            this.cartPage = new CartPage(app);
        }
        return this.cartPage;
    }

    public HeaderSection getHeaderSection() {
        if (this.headerSection == null) {
            this.headerSection = new HeaderSection(app);
        }
        return this.headerSection;
    }
}
