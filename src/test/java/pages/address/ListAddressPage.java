package test.java.pages.address;

import test.java.data.address.Address;
import test.java.exceptions.PageValidationException;

public class ListAddressPage {
    public ListAddressPage visit() {
        throw new PageValidationException("Implement Navigating Successfully");
//        return new ListAddressPage();
    }

    public void deleteAddressSuccessfully(Address address) {
        throw new PageValidationException("Implement Deleting Address Successfully");
    }
}
