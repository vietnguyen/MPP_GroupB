package librarysystem;

import business.ControllerInterface;
import business.SystemController;
import business.exceptions.CheckoutRecordException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintCheckoutWindow extends JFrame implements LibWindow {
    private static final long serialVersionUID = 1000L;
    public static final PrintCheckoutWindow INSTANCE = new PrintCheckoutWindow();
    ControllerInterface ci = SystemController.INSTANCE;
    private boolean isInitialized = false;
    private TextArea textArea = new TextArea(20,90);

    @Override
    public void init() {
        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);
        isInitialized = true;
        setSize(400, 300);
        setTitle("Checkout Records");
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel prompt = new JLabel("Search for a member to get their checkout record");
        panel.add(new JLabel());
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField();

        panel.add(prompt);
        panel.add(memberIdLabel);
        panel.add(memberIdField);

        JButton searchButton = new JButton("Search");
        panel.add(searchButton);
        JButton mainMenuButton = new JButton("Main Menu");
        panel.add(mainMenuButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(textArea);
        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String memberId = memberIdField.getText();
                    var recordEntries = ci.getCheckoutRecord(memberId).getCheckoutRecordEntries();
                    var sb = new StringBuilder();
                    if(recordEntries.isEmpty()) sb.append("No checkout record to display");
                    else recordEntries.forEach(x -> sb.append(x.toString()));
                    setData(sb.toString());
                }catch (CheckoutRecordException ex){
                    setData(ex.getMessage());
                }
            }
        });

        mainMenuButton.addActionListener(e -> {
            setVisible(false);
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void setData(String data) {
        textArea.setText(data);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
}
