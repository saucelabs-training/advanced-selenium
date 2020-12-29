package test.java.pages.address;

import test.java.data.address.Address;
import test.java.exceptions.PageValidationException;

public class NewAddressPage {
    public static NewAddressPage visit() {
        throw new PageValidationException("Implement Navigating Successfully");
//        return new NewAddressPage();
    }

    public void createAddressSuccessfully(Address address) {
        throw new PageValidationException("Implement Creating Address Successfully");
    }

    public void createAddressSuccessfully() {
        throw new PageValidationException("Implement Creating Address Successfully");
    }
}
