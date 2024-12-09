package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

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
        return "CheckoutRecordEntry [checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", bookName=" + bookCopy.getBook().getTitle() + ", bookCopy=" + bookCopy.getCopyNum()
                + "]\n";
    }

    public HashMap<String,String> getDetails(){
        var map = new HashMap<String,String>();
        map.put("checkoutDate", checkoutDate.toString());
        map.put("dueDate", dueDate.toString());
        map.put("bookName", bookCopy.getBook().getTitle());
        map.put("bookCopy", Integer.toString(bookCopy.getCopyNum()));

        return map;
    }
}
