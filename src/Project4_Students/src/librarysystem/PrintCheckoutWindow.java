package librarysystem;

import business.ControllerInterface;
import business.SystemController;
import business.exceptions.CheckoutRecordException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrintCheckoutWindow extends JFrame implements LibWindow {
    private static final long serialVersionUID = 1000L;
    public static final PrintCheckoutWindow INSTANCE = new PrintCheckoutWindow();
    ControllerInterface ci = SystemController.INSTANCE;
    private boolean isInitialized = false;
    private TextArea textArea = new TextArea(20,90);
    private String[] columnNames = { "Book Name", "Book Copy", "Checkout Date", "Due Date" };
    private DefaultTableModel dtm = new DefaultTableModel(null, columnNames) {

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    };
    private JTable table = new JTable(dtm);
    private JScrollPane sp = new JScrollPane(table);
    private JScrollBar vScroll = sp.getVerticalScrollBar();
    private int row;
    private JPanel panel;

    @Override
    public void init() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);
        isInitialized = true;
        setSize(400, 300);
        setTitle("Checkout Records");
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel prompt = new JLabel("Search for a member to get their checkout record");
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField();

        JPanel topPanel = new JPanel(new GridLayout(0, 1));
        panel.add(topPanel, BorderLayout.NORTH);

        topPanel.add(prompt);
        topPanel.add(memberIdLabel);
        topPanel.add(memberIdField);

        JButton searchButton = new JButton("Search");
        topPanel.add(searchButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(sp);
        panel.add(bottomPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dtm.setRowCount(0);
                    ArrayList<ArrayList<String>> dataList = new ArrayList<>();
                    String memberId = memberIdField.getText();
                    var recordEntries = ci.getCheckoutRecord(memberId).getCheckoutRecordEntries();
                    if(recordEntries.isEmpty()) JOptionPane.showMessageDialog(panel, "No checkout record to display");
                    else recordEntries.forEach(x -> {
                        var details = x.getDetails();
                        ArrayList<String> detailList = new ArrayList<>();
                        detailList.addAll(List.of(new String[]{details.get("bookName"), details.get("bookCopy"), details.get("checkoutDate"), details.get("dueDate")}));
                        dataList.add(detailList);
                    });

                    for (int i = 0; i < dataList.size(); i++) {
                        String[] arr = new String[4];
                        for (int j = 0; j < 4; j++) {
                            arr[j] = dataList.get(i).get(j);
                        }
                        dtm.addRow(arr);
                    }
                }catch (CheckoutRecordException ex){
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }
            }
        });

        JPanel bottomPanel2 = new JPanel();
        bottomPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton mainMenuButton = new JButton("Main Menu");
        bottomPanel2.add(mainMenuButton);
        panel.add(bottomPanel2, BorderLayout.SOUTH);

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
