package components;

import business.ControllerInterface;
import business.SystemController;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddMemberForm extends JPanel {
  ControllerInterface ci = new SystemController();
  
  public AddMemberForm() {
    init();
    setVisible(true);
  }
  public void init() {
    // Create the panel to hold the form elements
    
    setLayout(new GridLayout(9, 2, 10, 10));
    setBorder(new EmptyBorder(15, 15, 15, 15));

    // Create labels and text fields for each input
    JLabel lblMemberId = new JLabel("Member ID:");
    JTextField txtMemberId = new JTextField();

    JLabel lblFirstName = new JLabel("First Name:");
    JTextField txtFirstName = new JTextField();

    JLabel lblLastName = new JLabel("Last Name:");
    JTextField txtLastName = new JTextField();

    JLabel lblStreet = new JLabel("Street:");
    JTextField txtStreet = new JTextField();

    JLabel lblCity = new JLabel("City:");
    JTextField txtCity = new JTextField();

    JLabel lblState = new JLabel("State:");
    JTextField txtState = new JTextField();

    JLabel lblZip = new JLabel("ZIP:");
    JTextField txtZip = new JTextField();

    JLabel lblTelephone = new JLabel("Telephone:");
    JTextField txtTelephone = new JTextField();

    // Create a submit button
    JButton btnSubmit = new JButton("Submit");

    // Add action listener to the submit button
    btnSubmit.addActionListener(e -> {
      // Retrieve values from the fields and print them to the console
      String memberId = txtMemberId.getText();
      String firstName = txtFirstName.getText();
      String lastName = txtLastName.getText();
      String street = txtStreet.getText();
      String city = txtCity.getText();
      String state = txtState.getText();
      String zip = txtZip.getText();
      String telephone = txtTelephone.getText();

      String message = "";

      try {
        ci.addMember(memberId, firstName, lastName, telephone, street, zip, state, city);
        message = "Member Details:\n" +
                "Member ID: " + memberId + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Street: " + street + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n" +
                "ZIP: " + zip + "\n" +
                "Telephone: " + telephone;
      } catch (Exception ex) {
        message = ex.getMessage();
      }
      JOptionPane.showMessageDialog(this, message);
    });

    // Add components to the panel
    add(lblMemberId);
    add(txtMemberId);
    add(lblFirstName);
    add(txtFirstName);
    add(lblLastName);
    add(txtLastName);
    add(lblStreet);
    add(txtStreet);
    add(lblCity);
    add(txtCity);
    add(lblState);
    add(txtState);
    add(lblZip);
    add(txtZip);
    add(lblTelephone);
    add(txtTelephone);
    add(new JLabel()); // Empty space
    add(btnSubmit);
    setVisible(true);
  }
}
