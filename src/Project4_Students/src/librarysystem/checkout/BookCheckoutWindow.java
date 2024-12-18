package librarysystem.checkout;

import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import model.BookCheckoutTableModel;

import javax.swing.*;

import business.LibraryMember;
import business.SystemController;
import business.exceptions.CheckoutException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookCheckoutWindow extends JFrame implements LibWindow {
    private static final long serialVersionUID = 1L;
    public static final BookCheckoutWindow INSTANCE = new BookCheckoutWindow();
    private SystemController controller = SystemController.INSTANCE;

    private boolean isInitialized = false;
    private JTable checkoutTable;
    private BookCheckoutTableModel tableModel;

    private BookCheckoutWindow() {
    }

    @Override
    public void init() {
        if (isInitialized) return;
        isInitialized = true;

        setTitle("Book Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        initializeTable();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Adding padding between components
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adding margin around the panel

        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField();
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();

        panel.add(memberIdLabel);
        panel.add(memberIdField);
        panel.add(isbnLabel);
        panel.add(isbnField);

        JButton checkoutButton = new JButton("Checkout");
        panel.add(new JLabel()); // empty cell
        panel.add(checkoutButton);

        add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton mainMenuButton = new JButton("Main Menu");
        bottomPanel.add(mainMenuButton);
        add(bottomPanel, BorderLayout.SOUTH);

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String memberId = memberIdField.getText();
                String isbn = isbnField.getText();
                try {
                    controller.checkoutBook(memberId, isbn);
                    refreshTable();
                } catch (CheckoutException ex) {
                    JOptionPane.showMessageDialog(BookCheckoutWindow.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(BookCheckoutWindow.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(BookCheckoutWindow.this, "Book checked out successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                memberIdField.setText("");
                isbnField.setText("");
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LibrarySystem.hideAllWindows();
                LibrarySystem.INSTANCE.setVisible(true);
            }
        });

        setVisible(true);
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    private void initializeTable() {
        List<LibraryMember> members = controller.findAllLibraryMembers();
        tableModel = new BookCheckoutTableModel(members);
        checkoutTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(checkoutTable);
        add(scrollPane, BorderLayout.NORTH);
    }

    private void refreshTable() {
        List<LibraryMember> members = controller.findAllLibraryMembers();
        tableModel = new BookCheckoutTableModel(members);
        checkoutTable.setModel(tableModel);
        repaint();
    }
}