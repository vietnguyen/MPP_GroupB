package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{
    private static final long serialVersionUID = 1L;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private CheckoutRecord checkoutRecord;
    
    public CheckoutRecordEntry(LocalDate checkoutDate, BookCopy bookCopy, LibraryMember libraryMember) {
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
        this.bookCopy = bookCopy;
        this.checkoutRecord = libraryMember.getCheckoutRecord();
        this.checkoutRecord.addCheckoutRecordEntry(this);
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry [checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", bookCopy=" + bookCopy
                + "]";
    }
}
