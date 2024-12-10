package business;

import static librarysystem.Util.parseDate;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.OverdueModel;

public class OverdueController {

  private SystemController systemController = SystemController.INSTANCE;
  private DataAccess da = new DataAccessFacade();
  private static HashMap<String, Book> allBooks;
  private List<LibraryMember> members;
  public OverdueController() {
    allBooks = da.readBooksMap();
    members = systemController.findAllLibraryMembers();
    members.get(0).getCheckoutRecord().addCheckoutRecordEntry(
        new CheckoutRecordEntry(parseDate("10/01/2024"), allBooks.get("23-11451").getCopy(1),
            members.get(0)));
    members.get(1).getCheckoutRecord().addCheckoutRecordEntry( 
        new CheckoutRecordEntry(parseDate("10/02/2024"), allBooks.get("28-12331").getCopy(1),
            members.get(1)));
  }

  /**
   * Book(the ISBN, book title), BookCopy(copyNumber), Member(member name), RecordEntry(due date)
   * @param isbn
   * @return
   */
  public List<OverdueModel> findBookCopiesByIsbn(String isbn) {
    Map<String, OverdueModel> map = new HashMap<>();
    Book book = allBooks.get(isbn);
    if (book == null) {
      throw new IllegalArgumentException("Book not found");
    }
    
    List<OverdueModel> overdueModels = new ArrayList<>();
    BookCopy[] copies = book.getCopies();
    for (BookCopy copy : copies) {
      OverdueModel model = new OverdueModel(null, copy, null);
      overdueModels.add(model);
    }
    findMemberCheckoutEntries(members, overdueModels, isbn);
    return overdueModels;
  }
  
  private void findMemberCheckoutEntries(List<LibraryMember> members, List<OverdueModel> overdueModels, String isbn) {
    members.forEach(member -> {
      List<CheckoutRecordEntry> memberEntries = member.getCheckoutRecord()
          .getCheckoutRecordEntries();
      memberEntries.forEach(memberEntry -> {
        if (memberEntry.getBookCopy().getBook().getIsbn().equals(isbn)) {
          overdueModels.forEach(model -> {
            if (model.getBookCopy().getCopyNum() == memberEntry.getBookCopy().getCopyNum()) {
              model.setMember(member);
              model.setCheckoutRecordEntry(memberEntry);
            }
          });
        }
      });
    });
  }
}
