package components;

import business.Book;
import business.OverdueController;
import business.SystemController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import model.OverdueModel;
import model.OverdueTableModel;

public class OverduePanel extends JPanel {
  public static final OverduePanel INSTANCE = new OverduePanel();
  private SystemController controller = SystemController.INSTANCE;
  private OverdueController overdueController = new OverdueController();
  private static final long serialVersionUID = 1L;
  private boolean isInitialized = false;
  private JPanel topPanel;
  private JTable table;
  private JScrollPane scrollPane;
  private OverduePanel() {
  }

  public void init() {
    if (isInitialized) {
      return;
    }
    setLayout(new BorderLayout());
    topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(1, 2));
    topPanel.setBorder(BorderFactory.createTitledBorder("Search"));
    JLabel label = new JLabel("Book ISBN:");
    JTextField textField = new JTextField(10);
    textField.addActionListener(e -> {
      String input = textField.getText();
      try {
        searchBook(input);
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, "Cannot find book with ISBN " + input);
      }
    });
    
    topPanel.add(label);
    topPanel.add(textField);
    add(topPanel, BorderLayout.NORTH);
    
    List<Book> books = new ArrayList<>();
    Book book1 = new Book("123", "Book1", 21, new ArrayList<>());
    Book book2 = new Book("345", "Book2", 21, new ArrayList<>());
    books.add(book1);
    books.add(book2);
    
    OverdueTableModel tableModel = new OverdueTableModel(List.of());
    // Create the JTable
    table = new JTable(tableModel);
    TableColumn memberNameCol = table.getColumn("Member");
    memberNameCol.setMinWidth(150);
    
    scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
    isInitialized = true;
  }
  
  private void searchBook(String isbn) {
    List<OverdueModel> models = overdueController.findBookCopiesByIsbn(isbn);
    if (models.isEmpty()) {
      return;
    }
    OverdueTableModel overdueTableModel = new OverdueTableModel(models);
    table.setModel(overdueTableModel);
    repaint();
    
  }
}
