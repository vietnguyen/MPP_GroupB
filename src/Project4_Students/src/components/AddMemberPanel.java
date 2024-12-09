package components;

import business.ControllerInterface;
import business.SystemController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddMemberPanel extends JPanel {
  private JPanel leftPanel;
  private TextArea textArea;
  private ControllerInterface ci = SystemController.INSTANCE;
  
  public AddMemberPanel() {
    init();
    setVisible(true);
  }
  
  
  public void init() {
    // create two panel left and right
    setLayout(new BorderLayout());

    leftPanel = new JPanel();

    // Create the right panel
    JPanel rightPanel = new JPanel();
    rightPanel.setBackground(Color.LIGHT_GRAY);
    rightPanel.setLayout(new GridLayout(2, 1)); // Example layout for nested components
    rightPanel.add(new JLabel("Right Panel - Item 1"));
    rightPanel.add(new JLabel("Right Panel - Item 2"));

    // Add the left and right panels to the main panel
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.CENTER);
    initLeftPanel();
    setMemberList();
  }
  
  public void setMemberList() {
//    TextArea textArea = (TextArea) leftPanel.getComponent(0);
    List<String> ids = ci.allMemberIds();
    Collections.sort(ids);
    StringBuilder sb = new StringBuilder();
    for (String s : ids) {
      sb.append(s + "\n");
    }
    textArea.setText(sb.toString());
  }
  
  private void initLeftPanel() {
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
    leftPanel.setLayout(fl);
    textArea = new TextArea(8, 20);
    leftPanel.add(textArea);
  }
}
