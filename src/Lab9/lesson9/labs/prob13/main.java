package lesson9.labs.prob13;

import java.util.List;

public class main {
    public static void main(String[] args){
        Book book = new Book("Test", 3);
        List<BookCopy> copies = book.getCopies();
        copies.forEach(c -> c.changeAvailability());

        System.out.println(book.isAvailable()); // false
        copies.get(1).changeAvailability();
        System.out.println(book.isAvailable()); // true
    }
}
