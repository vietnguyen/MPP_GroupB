package components;

import business.ControllerInterface;
import business.SystemController;
import business.Book;
import librarysystem.IPanel;
import librarysystem.LibrarySystem;
import librarysystem.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookManagerPanel extends JPanel implements IPanel {

    public final static BookManagerPanel INSTANCE = new BookManagerPanel();

    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel rightPanel, bottomPanel;
    private AddBookPanel centerPanel;
    private JTable bookTable; // Table to display books
    private DefaultTableModel tableModel; // Model to manage table data
    private JButton saveButton;
    private JButton addButton;
    List<Book> allBooks;
    private ControllerInterface ci = SystemController.INSTANCE;
    public BookManagerPanel() {
        init();
        setVisible(true);
    }


    public void init() {
        // create two panel left and right
        setLayout(new BorderLayout());
        initTopPanel();
        initLeftPanel();
        // Create the right panel
        initRightPanel();

    }

    public void initTopPanel() {
        topPanel = new JPanel();
        add(topPanel,BorderLayout.NORTH);

        JLabel AllIDsLabel = new JLabel("Books Management");
        Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(AllIDsLabel);
    }

    private void initLeftPanel() {

        // Add the left and right panels to the main panel
        if(leftPanel != null)
            remove(leftPanel);
        leftPanel = new JPanel();
        add(leftPanel, BorderLayout.WEST);

        leftPanel.setLayout(new BorderLayout());

        //Books table
        // Define column names for the table
        String[] columnNames = {"ISBN", "Title"};
        // Create the table model and JTable
        tableModel = new DefaultTableModel(columnNames, 0); // 0 indicates no initial rows
        //add book lines into table model
        allBooks = ci.allBooks();
        for (Book b : allBooks) {
            tableModel.addRow(new Object[]{b.getIsbn(), b.getTitle()});
        }

        //Create table to display data in tableModel
        bookTable = new JTable(tableModel);
        // Enable single row selection
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set table to not auto-resize columns
        bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Set column widths
        TableColumn isbnColumn = bookTable.getColumnModel().getColumn(0);
        isbnColumn.setPreferredWidth(100); // ISBN column width

        TableColumn titleColumn = bookTable.getColumnModel().getColumn(1);
        titleColumn.setPreferredWidth(150); // Title column width

        // Set the preferred width of the entire table
        bookTable.setPreferredScrollableViewportSize(new Dimension(250, bookTable.getRowHeight() * 5)); // 5 rows visible

        // Add a ListSelectionListener to handle row selection events
        bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Ignore extra firing during initialization
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = bookTable.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        String isbn = (String) bookTable.getValueAt(selectedRow, 0);
                        Optional<Book> selectedBook = allBooks.stream()
                                .filter((Book book) -> {
                                    return book.getIsbn().equalsIgnoreCase(isbn);
                                })
                                .findFirst(); // Return the first match as an Optional
                        centerPanel.setFromBook(selectedBook);
                    }
                }
            }
        });


        // Wrap the JTable in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Add the scroll pane to the panel
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        validate();
        repaint();
    }

    private void initRightPanel() {

        rightPanel = new JPanel();
        add(rightPanel, BorderLayout.CENTER);

        rightPanel.setLayout(new BorderLayout());

        centerPanel = new AddBookPanel();
        rightPanel.add(centerPanel,BorderLayout.CENTER);

        bottomPanel = new JPanel();
        bottomPanel.setLayout( new FlowLayout());

        // Add and Close buttons
        JButton closeButton = new JButton("CLOSE");
        closeButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this); // Remove this panel from the parent container
                parent.revalidate(); // Refresh layout
                parent.repaint();    // Repaint the container
            }
        });


        //ADD button
        addButton = new JButton("ADD");
        addButton.addActionListener(e -> {
            centerPanel.addData();
        });

        //SAVE button
        saveButton = new JButton("SAVE");

        saveButton.addActionListener(e -> {
            if(centerPanel.getIsbnField().getText().isBlank() || centerPanel.getTitleField().getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Please input all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                //get data & save.
                ci.addNewBook(centerPanel.toBook());
                initLeftPanel();
            }
        });

        // Add listeners to fields
        addRequiredFieldListener(centerPanel.getIsbnField());
        addRequiredFieldListener(centerPanel.getTitleField());
        addRequiredFieldListener(centerPanel.getCheckoutDaysField());


        // Add buttons to the bottom panel
        bottomPanel.add((closeButton));
        bottomPanel.add(addButton);
        bottomPanel.add(saveButton);


        rightPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addRequiredFieldListener(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkRequiredFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkRequiredFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkRequiredFields();
            }
        });
    }

    private void checkRequiredFields() {
        // Check if all required fields are filled
        boolean allFieldsFilled = !centerPanel.getIsbnField().getText().trim().isEmpty()
                && !centerPanel.getTitleField().getText().trim().isEmpty()
                && !centerPanel.getCheckoutDaysField().getText().trim().isEmpty();
        ;
        // Enable/Disable SAVE button
        saveButton.setEnabled(allFieldsFilled);
    }
}
