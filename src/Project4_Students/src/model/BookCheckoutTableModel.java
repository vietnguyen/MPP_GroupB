package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import business.Book;
import business.BookCopy;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import librarysystem.Util;

public class BookCheckoutTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ISBN", "Title", "Copy number", "Checkout date", "Due date", "Member name"};
    private List<Object[]> data;

    public BookCheckoutTableModel(List<LibraryMember> members) {
        data = new ArrayList<>();
        for (LibraryMember member : members) {
            for (CheckoutRecordEntry entry : member.getCheckoutRecord().getCheckoutRecordEntries()) {
                BookCopy copy = entry.getBookCopy();
                Book book = copy.getBook();
                String isbn = book.getIsbn();
                String title = book.getTitle();
                int copyNum = copy.getCopyNum();
                String checkoutDate = Util.formatDate(entry.getCheckoutDate());
                String dueDate = Util.formatDate(entry.getDueDate());
                String memberName = member.getFirstName() + " " + member.getLastName();
                Object[] row = {isbn, title, copyNum, checkoutDate, dueDate, memberName};
                data.add(row);
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
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
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Allow editing
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
}
