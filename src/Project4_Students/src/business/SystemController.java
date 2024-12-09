package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
