package business;

import java.util.List;

import business.Book;
import business.exceptions.CheckoutRecordException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<LibraryMember> findAllLibraryMembers();
    public List<Book> allBooks();
    public List<Author> allAuthors();
    public void addNewBook(Book book);
    public Book getBookByIsbn(String isnb);
	boolean addMember(String memberId, String firstName, String lastName, String phone, String street, String zipCode, String state, String city);
	CheckoutRecord getCheckoutRecord(String memberId) throws CheckoutRecordException;
}
