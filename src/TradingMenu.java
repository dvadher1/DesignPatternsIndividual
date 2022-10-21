import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

class TradingMenu extends JDialog {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Product product;
	protected boolean isLoggedOut = true;
	ProductMenu theProductMenu;
	Product currentProduct;
	
	JComboBox<String> tradingCombox = new JComboBox<>();
	JButton tradingAddButton = new JButton();
	private JButton buttonLogout = new JButton();
	
	TradingMenu() {
		try {
		    this.getContentPane().setLayout(null);
		    this.setTitle("");
		    buttonLogout.setText("Logout");
		    buttonLogout.setBounds(new Rectangle(120, 215, 200, 37));
		    buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
		    this.getContentPane().add(buttonLogout, null);
	    } catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		setModal(true);
		setSize(575, 330);
	}
	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	
	boolean showMenu(Trading trade, Person person) {
		showAddButton();
		showComboxes();
		setVisible(true);
		return isLoggedOut();
	}
	
	public void showAddButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an offer to seller");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
		this.getContentPane().add(tradingAddButton, null);
	}
	public void showComboxes() {
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}
	@SuppressWarnings("deprecation")
	void TradingAddButton_actionPerformed() {
		String seller = (String) tradingCombox.getSelectedItem();
		int randomDay = ThreadLocalRandom.current().nextInt(1, 31 + 1);
		int randomMoney = 10*ThreadLocalRandom.current().nextInt(1, 10 + 1);
		PTBS.mainFacade.addTrading(currentProduct, new Date(2022-1900, 9, randomDay), seller, randomMoney, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName()) != null) {
		    for(String seller : PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName())) {
		    	tradingCombox.addItem(seller);
		    }
		}
	}
	public boolean isLoggedOut() {
		return isLoggedOut;
	}
}