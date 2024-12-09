package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import business.exceptions.CheckoutException;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	public static final SystemController INSTANCE = new SystemController();

	private SystemController() {}
	
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
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	public List<LibraryMember> findAllLibraryMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
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

		availableCopy.changeAvailability();
		book.updateCopies(availableCopy);
		da.updateBook(book);

		LibraryMember member = members.get(memberId);
		new CheckoutRecordEntry(LocalDate.now(), availableCopy, member);
		da.updateMember(member);
	}

    @Override
    public List<Book> allBooks() {
        DataAccess da = new DataAccessFacade();
        return da.readBooksMap().values().stream().toList();

    }

    @Override
    public List<Author> allAuthors() {
        DataAccess da = new DataAccessFacade();
        return da.readAuthors();

    }

    @Override
    public void addNewBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.saveNewBook(book);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Optional<Book> filteredBook = allBooks().stream()
                .filter(book -> book.getIsbn().equalsIgnoreCase(isbn))
                .findFirst();
        if (filteredBook.isPresent())
            return filteredBook.get();
        else return	null;

    }

	
}
