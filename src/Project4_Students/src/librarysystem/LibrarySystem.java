package librarysystem;

import business.ControllerInterface;
import business.SystemController;
import components.AddMemberPanel;
import librarysystem.checkout.BookCheckoutWindow;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class LibrarySystem extends JFrame implements LibWindow {

  ControllerInterface ci = new SystemController();
  public final static LibrarySystem INSTANCE = new LibrarySystem();
  JPanel mainPanel;
  JMenuBar menuBar;
  JMenu options;
  JMenuItem login, allBookIds, allMemberIds, addMember, addBookCopy, addBook, checkOutBook, printCheckout, overdue;
  String pathToImage;
  private boolean isInitialized = false;

  private static LibWindow[] allWindows = {
      LibrarySystem.INSTANCE,
      LoginWindow.INSTANCE,
      AllMemberIdsWindow.INSTANCE,
      AllBookIdsWindow.INSTANCE,
      AddMemberWindow.INSTANCE,
  };

  public static void hideAllWindows() {
    for (LibWindow frame : allWindows) {
      frame.setVisible(false);
    }
  }

  private LibrarySystem() {
  }

  public void init() {
    formatContentPane();
    setPathToImage();
    insertSplashImage();
    createMenus();
    //pack();
    setSize(660, 500);
    isInitialized = true;
  }

  private void formatContentPane() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 1));
    getContentPane().add(mainPanel);
  }

  private void setPathToImage() {
    String currDirectory = System.getProperty("user.dir");
    pathToImage = currDirectory + "/src/librarysystem/library.jpg";
  }

  private void insertSplashImage() {
    ImageIcon image = new ImageIcon(pathToImage);
    mainPanel.add(new JLabel(image));
  }

  private void createMenus() {
    menuBar = new JMenuBar();
    menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
    addMenuItems();
    setJMenuBar(menuBar);
  }

  private void addMenuItems() {
    options = new JMenu("Options");
    menuBar.add(options);

    addViewAllBookIdsMenuItem(options);
    addViewAllMemberIdsMenuItem(options);
    addAddMemberMenuItem(options);
    addBookMenuItem(options);
    addBookCopyMenuItem(options);
    checkoutBookMenuItem(options);
    printCheckoutMenuItem(options);
    overdueMenuItem(options);
    addLoginMenuItem(options);
  }

  private void addAddMemberMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Add Member");
    this.addMember.addActionListener(new AddMemberListener());
    options.add(this.addMember);
  }

  private void addBookCopyMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Add Book Copy");
    this.addMember.addActionListener(new AddBookCopyListener());
    options.add(this.addMember);
  }

  private void addBookMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Add Book");
    this.addMember.addActionListener(new AddBookListener());
    options.add(this.addMember);
  }

  private void checkoutBookMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Checkout Book");
    this.addMember.addActionListener(new CheckoutBookListener());
    options.add(this.addMember);
  }


  private void printCheckoutMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Print Checkout");
    this.addMember.addActionListener(new PrintCheckoutListener());
    options.add(this.addMember);
  }

  private void overdueMenuItem(JMenu options) {
    this.addMember = new JMenuItem("Overdue");
    this.addMember.addActionListener(new OverdueListener());
    options.add(this.addMember);
  }


  private void addViewAllMemberIdsMenuItem(JMenu options) {
    allMemberIds = new JMenuItem("All Member Ids");
    allMemberIds.addActionListener(new AllMemberIdsListener());
    options.add(allMemberIds);
  }

  private void addViewAllBookIdsMenuItem(JMenu options) {
    allBookIds = new JMenuItem("All Book Ids");
    allBookIds.addActionListener(new AllBookIdsListener());
    options.add(allBookIds);
  }

  private void addLoginMenuItem(JMenu options) {
    if (SystemController.currentAuth != null) {
      login = new JMenuItem("Logout");
      login.addActionListener(new LogoutListener());
      options.add(login);
    } else {
      login = new JMenuItem("Login");
      login.addActionListener(new LoginListener());
      options.add(login);
    }
  }

  class LogoutListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      SystemController.currentAuth = null;
      LibrarySystem.hideAllWindows();
      LoginWindow.INSTANCE.init();
      Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
      LoginWindow.INSTANCE.setVisible(true);
    }
  }

  class LoginListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      LibrarySystem.hideAllWindows();
      LoginWindow.INSTANCE.init();
      Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
      LoginWindow.INSTANCE.setVisible(true);

    }

  }

  class AllBookIdsListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      LibrarySystem.hideAllWindows();
      AllBookIdsWindow.INSTANCE.init();

      List<String> ids = ci.allBookIds();
      Collections.sort(ids);
      StringBuilder sb = new StringBuilder();
      for (String s : ids) {
        sb.append(s + "\n");
      }
      System.out.println(sb.toString());
      AllBookIdsWindow.INSTANCE.setData(sb.toString());
      AllBookIdsWindow.INSTANCE.pack();
      //AllBookIdsWindow.INSTANCE.setSize(660,500);
      Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
      AllBookIdsWindow.INSTANCE.setVisible(true);

    }

  }

  class AddMemberListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      setMainPanel(new AddMemberPanel());
    }
  }
  
  private void setMainPanel(JPanel jPanel) {
    getContentPane().remove(mainPanel);
    getContentPane().add(jPanel);
    revalidate(); // Refresh the frame to show the new panel
    repaint();
  }

  class AddBookCopyListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }
  }

  class AddBookListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }
  }

  class CheckoutBookListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      LibrarySystem.hideAllWindows();
      BookCheckoutWindow.INSTANCE.init();
      BookCheckoutWindow.INSTANCE.pack();
      Util.centerFrameOnDesktop(BookCheckoutWindow.INSTANCE);
      BookCheckoutWindow.INSTANCE.setVisible(true);

      


    }
  }

  class PrintCheckoutListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      LibrarySystem.hideAllWindows();
      PrintCheckoutWindow.INSTANCE.init();
      PrintCheckoutWindow.INSTANCE.pack();
      Util.centerFrameOnDesktop(PrintCheckoutWindow.INSTANCE);
      PrintCheckoutWindow.INSTANCE.setVisible(true);
    }
  }

  class OverdueListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }
  }

  class AllMemberIdsListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      LibrarySystem.hideAllWindows();
      AllMemberIdsWindow.INSTANCE.init();
      AllMemberIdsWindow.INSTANCE.pack();
      AllMemberIdsWindow.INSTANCE.setVisible(true);

      LibrarySystem.hideAllWindows();
      AllBookIdsWindow.INSTANCE.init();

      List<String> ids = ci.allMemberIds();
      Collections.sort(ids);
      StringBuilder sb = new StringBuilder();
      for (String s : ids) {
        sb.append(s + "\n");
      }
      System.out.println(sb.toString());
      AllMemberIdsWindow.INSTANCE.setData(sb.toString());
      AllMemberIdsWindow.INSTANCE.pack();
      //AllMemberIdsWindow.INSTANCE.setSize(660,500);
      Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
      AllMemberIdsWindow.INSTANCE.setVisible(true);
    }

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
