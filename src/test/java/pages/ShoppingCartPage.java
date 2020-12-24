package test.java.pages;

public class ShoppingCartPage {
    public InformationPage checkOut() {
        return new InformationPage();
    }

    public CheckoutSignIn checkOutSignIn() {
        return new CheckoutSignIn();
    }
}
