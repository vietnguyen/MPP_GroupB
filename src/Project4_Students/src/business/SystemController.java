package business;

import java.time.LocalDate;
import java.util.*;

import business.exceptions.CheckoutException;
import business.exceptions.CheckoutRecordException;
import business.exceptions.InvalidArgumentException;
import business.exceptions.UnauthorizedException;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = Auth.BOTH;
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

	@Override
	public List<Book> searchBooks(String keyword) {
		return allBooks().stream()
				.filter(book -> book.getIsbn().contains(keyword) || book.getTitle().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase()))
				.toList();
	}




	@Override
	public boolean addMember(String memberId, String firstName, String lastName, String phone, String street, String zipCode, String state, String city){
		if(currentAuth != Auth.ADMIN && currentAuth != Auth.BOTH){
			throw new UnauthorizedException("You are not authorized to perform this action");
		}
		if(memberId == null || memberId.isBlank()
			|| firstName == null || firstName.isBlank()
			|| lastName == null || lastName.isBlank()
			|| phone == null || phone.isBlank()
			|| street == null || street.isBlank()
			|| zipCode == null || zipCode.isBlank()
			|| state == null || state.isBlank()
			|| city == null || city.isBlank()){
			throw new InvalidArgumentException("Missing required input");
		}

		if(!phone.matches("[0-9]{10}")){
			throw new InvalidArgumentException("Phone number must be 10 digits long");
		}

		if(!zipCode.matches("[0-9]{5}")){
			throw new InvalidArgumentException("Zip code must be 5 digits long");
		}

		LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, new Address(street, city, state, zipCode));
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(member);
		return true;
	}

	public CheckoutRecord getCheckoutRecord(String memberId) throws CheckoutRecordException {
		DataAccess da = new DataAccessFacade();
		var members = da.readMemberMap();
		if(members.containsKey(memberId)) return members.get(memberId).getCheckoutRecord();
		else throw new CheckoutRecordException("Member with given ID not found");
	}
}
