package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<CheckoutRecordEntry> checkoutRecordEntries;

    public CheckoutRecord() {
        checkoutRecordEntries = new ArrayList<>();
    }

    public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }
}
