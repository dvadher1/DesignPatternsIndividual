import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean isLoggedOut = false;
	private JLabel jLabel1 = new JLabel();
	private JLabel jLabel2 = new JLabel();
	private JButton loginButton = new JButton();
	private JButton buttonExit = new JButton();
	private JTextField userNameText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();
	private JRadioButton buyerRadio = new JRadioButton();
	private JRadioButton sellerRadio = new JRadioButton();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private String userNameFromInput = null;
	private UserInfoItem.USER_TYPE UserType = UserInfoItem.USER_TYPE.Buyer;
	
	Login() {
		try {
			this.getContentPane().setLayout(null);
			jLabel1.setText("UserName");
			jLabel1.setBounds(new Rectangle(26, 52, 80, 18));
			jLabel2.setText("Password");
			jLabel2.setBounds(new Rectangle(23, 119, 80, 18));
			loginButton.setText("Login");
			loginButton.setBounds(new Rectangle(31, 212, 85, 28));
			loginButton.addActionListener(this::loginButton_actionPerformed);
			buttonExit.setText("Exit");
			buttonExit.setBounds(new Rectangle(180, 211, 97, 28));
			buttonExit.addActionListener(e -> buttonExit_actionPerformed());
			userNameText.setBounds(new Rectangle(119, 52, 144, 22));
			passwordText.setBounds(new Rectangle(118, 119, 147, 22));
			buyerRadio.setSelected(true);
			buyerRadio.setText("Buyer");
			buyerRadio.setBounds(new Rectangle(37, 164, 103, 26));
			sellerRadio.setText("Seller");
			sellerRadio.setBounds(new Rectangle(177, 162, 103, 26));
			this.getContentPane().add(jLabel1, null);
			this.getContentPane().add(jLabel2, null);
			this.getContentPane().add(loginButton, null);
			this.getContentPane().add(buttonExit, null);
			this.getContentPane().add(userNameText, null);
			this.getContentPane().add(passwordText, null);
			this.getContentPane().add(buyerRadio, null);
			this.getContentPane().add(sellerRadio, null);
			buttonGroup1.add(buyerRadio);
			buttonGroup1.add(sellerRadio);
			setSize(300, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void loginButton_actionPerformed(ActionEvent e) {
		BufferedReader file;
		isLoggedOut = false;
//		System.out.println("login clicked");
		try {
			if (buyerRadio.isSelected()) {
				UserType = UserInfoItem.USER_TYPE.Buyer;
				file = new BufferedReader(new FileReader("database\\BuyerInfo.txt"));
			} else {
				UserType = UserInfoItem.USER_TYPE.Seller;
				file = new BufferedReader(new FileReader("database\\SellerInfo.txt"));
			}
			userNameFromInput = userNameText.getText();
			String PasswordBox = new String(passwordText.getPassword());
			String LoginName = null;
			String aline, UserName, Password;
			while ((aline = file.readLine()) != null) {
				UserName = getUserName(aline);
				Password = getPassword(aline);
				if (UserName.compareTo(userNameFromInput) == 0 && Password.compareTo(PasswordBox) == 0) {
					LoginName = UserName;
//					System.out.println("Found");
				}
			}
			if (LoginName != null) {
				this.dispose();
			}
		} catch (Exception ignored) {
		}
	}
	
	private String getUserName(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(0, Sep);
	}
	
	private String getPassword(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(Sep + 1);
	}
	
	String getUserName() {
		return userNameFromInput;
	}
	
	UserInfoItem.USER_TYPE getUserType() {
		return UserType;
	}
	
	boolean getIsLoggedOut() {
		return isLoggedOut;
	}
	
	private void buttonExit_actionPerformed() {
		isLoggedOut = true;
		dispose();
	}

}