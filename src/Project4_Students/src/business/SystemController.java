package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.exceptions.CheckoutException;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
		// TODO: initialize CheckoutRecord
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public void checkoutBook(String memberId, String isbn) throws CheckoutException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> members = da.readMemberMap();
		HashMap<String, Book> books = da.readBooksMap();
	
		if (!members.containsKey(memberId)) {
			throw new CheckoutException("Member ID " + memberId + " not found");
		}
	
		if (!books.containsKey(isbn)) {
			throw new CheckoutException("Book ISBN " + isbn + " not found");
		}
	
		Book book = books.get(isbn);
		BookCopy availableCopy = null;
		for (BookCopy copy : book.getCopies()) {
			if (copy.isAvailable()) {
				availableCopy = copy;
				break;
			}
		}
	
		if (availableCopy == null) {
			throw new CheckoutException("No available copies for book ISBN " + isbn);
		}
	
		LibraryMember member = members.get(memberId);
		availableCopy.changeAvailability();
		CheckoutRecordEntry entry = new CheckoutRecordEntry(LocalDate.now(), availableCopy, member);
		
		// TODO: save CheckoutRecordEntry and BookCopy to storage
	}
	
	
}
