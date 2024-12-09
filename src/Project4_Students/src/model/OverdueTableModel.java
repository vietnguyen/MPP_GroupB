package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import librarysystem.Util;

public class OverdueTableModel extends AbstractTableModel {
  private final String[] columnNames = {"ISBN", "Title", "Copy number", "Member", "Due date"};
  private final List<OverdueModel> books;

  public OverdueTableModel(List<OverdueModel> books) {
    this.books = books;
  }

  @Override
  public int getRowCount() {
    return books.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    OverdueModel book = books.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return book.getBookCopy().getBook().getIsbn();
      case 1:
        return book.getBookCopy().getBook().getTitle();
      case 2:
        return book.getBookCopy().getCopyNum();
      case 3: 
        return book.getMember() != null ? book.getMember().getFirstName() + " " + book.getMember().getLastName() : "N/A";
      case 4:
        return book.getCheckoutRecordEntry() != null ? Util.formatDate(book.getCheckoutRecordEntry().getDueDate()) : "N/A";
      default:
        return null;
    }
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false; // Allow editing
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//    Book book = books.get(rowIndex);
//    switch (columnIndex) {
//      case 0:
//        book.set(aValue.toString());
//        break;
//      case 1:
//        book.setFirstName(aValue.toString());
//        break;
//      case 2:
//        book.setLastName(aValue.toString());
//        break;
//      case 3:
//        book.setPhoneNumber(aValue.toString());
//        break;
//    }
//    fireTableCellUpdated(rowIndex, columnIndex);
  }
}
