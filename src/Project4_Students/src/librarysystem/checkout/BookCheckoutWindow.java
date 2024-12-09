package librarysystem.checkout;

import librarysystem.LibWindow;
import librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookCheckoutWindow extends JFrame implements LibWindow {
    private static final long serialVersionUID = 1L;
    public static final BookCheckoutWindow INSTANCE = new BookCheckoutWindow();

    private boolean isInitialized = false;

    private BookCheckoutWindow() {
    }

    public void init() {
        if (isInitialized) return;
        isInitialized = true;

        setTitle("Book Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

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
                // Add logic to handle the checkout process
                // For example, call a method to check if the book is available and checkout
                // If successful, update the JTable with the new checkout record
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to return to the main menu
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
}
