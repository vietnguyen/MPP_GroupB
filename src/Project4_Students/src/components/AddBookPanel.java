package components;

import business.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddBookPanel extends JPanel {
    private Book currentBook;
    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorsField;
    private JFormattedTextField checkoutDaysField;
    private JPanel authorBoxesPanel;
    private JTextField noOfCopiesField;
    private boolean isInitBookPanel, isInitBookCopiesPanel;

    private JTabbedPane tabbedPane;
    private JPanel tabBook;
    private JPanel tabBookCopies;
    private JTable copiesTable;
    private java.util.List<JCheckBox> checkBoxes;
    private List<Author> allAuthors;
    private ControllerInterface ci = SystemController.INSTANCE;

    public AddBookPanel() {
        allAuthors = ci.allAuthors();
        initLayout();
    }

    private void initLayout() {
        //init new book
        if (currentBook == null)
            currentBook = new Book("", "", 30, new ArrayList<Author>());

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        setLayout(new BorderLayout());
        add(tabbedPane,BorderLayout.CENTER);
        // Create two tabs (panels)
        tabBook = new JPanel();
        tabBookCopies = new JPanel();

        tabbedPane.addTab("Book", tabBook);
        tabbedPane.addTab("Book Copies", tabBookCopies);

        initBookPanel();
        initBookCopiesPanel();


        validate();
        repaint();
        setVisible(true);
    }

    private void initAuthorsPanel() {

        // Panel for checkboxes
        authorBoxesPanel = new JPanel();
        authorBoxesPanel.setLayout(new BoxLayout(authorBoxesPanel, BoxLayout.Y_AXIS));
        //authorBoxesPanel.setPreferredSize(new Dimension(200, 500));

        // Create checkboxes
        checkBoxes = new ArrayList<>();
        for (Author author : ci.allAuthors()) {
            JCheckBox checkBox = new JCheckBox(author.getFirstName() + " " + author.getLastName());
            checkBoxes.add(checkBox);
            authorBoxesPanel.add(checkBox);
        }

    }

    private void initBookPanel(){
        if(isInitBookPanel) return;

        // Create the panel to hold the form elements
        tabBook.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add components to the panel
        // Create labels and text fields for each input
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.weightx = 0.2; // Small width for the label
        tabBook.add(new JLabel("ISBN*:"), gbc);

        gbc.gridx = 1; // Column 1
        gbc.weightx = 1.0; // Expands more
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch field horizontally
        isbnField = new JTextField();
        tabBook.add(isbnField, gbc);

        // Label and field for "Title"
        gbc.gridx = 0;
        gbc.gridy = 1; // Row 1
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.EAST;
        tabBook.add(new JLabel("Title*:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        titleField = new JTextField();
        tabBook.add(titleField, gbc);


        // Label and field for "Checkout Days"
        gbc.gridx = 0;
        gbc.gridy = 2; // Row 2
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.EAST;
        tabBook.add(new JLabel("Checkout Days*:"), gbc);

        gbc.gridx = 1;
        //max checkout days
        // Create a NumberFormatter to accept only integers for max checkout days
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(integerFormat);
        numberFormatter.setValueClass(Integer.class); // Restrict to Integer type
        numberFormatter.setAllowsInvalid(false); // Prevent invalid input
        numberFormatter.setMinimum(1); // Optional: Set minimum value (e.g., 0)
        numberFormatter.setMaximum(360); // Optional: Set maximum value (e.g., 9999)

        // Create the JFormattedTextField
        checkoutDaysField = new JFormattedTextField(numberFormatter);
        checkoutDaysField.setColumns(10);
        tabBook.add(checkoutDaysField, gbc);

        //No of copies
        gbc.gridx = 0;
        gbc.gridy = 3; // Row 2
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.EAST;
        tabBook.add(new JLabel("No of Copies:"), gbc);

        gbc.gridx = 1;
        noOfCopiesField = new JTextField();
        noOfCopiesField.setEnabled(false);
        tabBook.add(noOfCopiesField, gbc);

        //No of copies
        gbc.gridx = 0;
        gbc.gridy = 4; // Row 4
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.EAST;
        tabBook.add(new JLabel("Authors:"), gbc);

        // List of authors
        gbc.gridx = 1;
        gbc.weighty = 3;
        gbc.fill = GridBagConstraints.BOTH;
        initAuthorsPanel();
        // Wrap the panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(authorBoxesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabBook.add(scrollPane, gbc);

        isInitBookPanel = true;
    }

    private void initBookCopiesPanel(){
        if(isInitBookCopiesPanel) return;

        //Tab book copies
        tabBookCopies.setLayout(new BorderLayout());
        // Define column names for the table
        String[] columnNames = {"Copy#", "Available"};
        // Create the table model and JTable
        DefaultTableModel copiesModel = new DefaultTableModel(columnNames, 0); // 0 indicates no initial rows

        //Create table to display data in tableModel
        copiesTable = new JTable(copiesModel);
        // Enable single row selection
        copiesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set table to not auto-resize columns
        copiesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Set column widths

        // Set the preferred width of the entire table
        copiesTable.setPreferredScrollableViewportSize(new Dimension(250, copiesTable.getRowHeight() * 5)); // 5 rows visible

        // Wrap the JTable in a JScrollPane
        JScrollPane copiesScollPane = new JScrollPane(copiesTable);

        // Add the scroll pane to the panel
        tabBookCopies.add(copiesScollPane, BorderLayout.CENTER);
        isInitBookCopiesPanel = true;
    }

    private void bindBookData() {
        if (currentBook == null)
            currentBook = new Book("", "", 30, new ArrayList<Author>());

        isbnField.setText(currentBook.getIsbn());
        titleField.setText(currentBook.getTitle());
        checkoutDaysField.setText(Integer.toString(currentBook.getMaxCheckoutLength()));
        noOfCopiesField.setText(Integer.toString(currentBook.getNumCopies()));

        int aut = currentBook.getAuthors().size();
        // set author checkboxes
        for (JCheckBox chkbox : checkBoxes) {
            boolean checked = currentBook.getAuthors().stream().anyMatch(author -> (author.getFirstName() + " " + author.getLastName())
                    .contains(chkbox.getText()));
            chkbox.setSelected(checked);
        }

    }

    private void bindBookCopiesData(){
        //add book lines into table model
        String[] columnNames = {"Copy#", "Available"};
        // Create the table model and JTable
        DefaultTableModel copiesModel = new DefaultTableModel(columnNames, 0); // 0 indicates no initial rows

        BookCopy[] copies = currentBook.getCopies();
        for (BookCopy b : copies) {
            copiesModel.addRow(new Object[]{b.getCopyNum(), b.isAvailable()});
        }

        copiesTable.setModel(copiesModel);

    }
    // Set fields from an existing Book object
    void setFromBook(Optional<Book> book) {
        if (book.isPresent()) {
            currentBook = book.get();
            bindBookData();
            bindBookCopiesData();
        } else {
            // Handle the case where no book is present
            isbnField.setText("");
            titleField.setText("");
            checkoutDaysField.setText("");
            noOfCopiesField.setText("");

        }

    }


    // Get input and create a Book object
    Book toBook() {

        List<Author> selectedAuthors = new ArrayList<>();
        List<Author> allAuthors = ci.allAuthors();
        for (JCheckBox chk : checkBoxes) {
            if (chk.isSelected()) {
                Optional<Author> foundAuthor = allAuthors.stream()
                        .filter(author -> (author.getFirstName() + " " + author.getLastName()).equalsIgnoreCase(chk.getText()))
                        .findFirst();
                // Print result
                if (foundAuthor.isPresent()) {
                    Author auth = foundAuthor.get();
                    selectedAuthors.add(auth);
                }
            }

        }
        currentBook = new Book(isbnField.getText(),titleField.getText(),Integer.parseInt(this.getCheckoutDaysField().getText()),selectedAuthors);
        return currentBook;
    }

    public JTextField getIsbnField() {
        return isbnField;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getNoOfCopiesField() {
        return noOfCopiesField;
    }

    public JTextField getCheckoutDaysField() {
        return checkoutDaysField;
    }

    public void addData() {
        if(tabbedPane.getSelectedIndex() == 0) //tabBook
        {
            currentBook = null;
            bindBookData();
            bindBookCopiesData();
        }
        else if(tabbedPane.getSelectedIndex() == 1) //tab copies
        {
            currentBook.addCopy();
            bindBookData();
            bindBookCopiesData();
        }
    }
}
