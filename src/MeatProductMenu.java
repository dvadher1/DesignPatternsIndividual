import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MeatProductMenu extends ProductMenu {
	
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
	
	public void setProductMenu(ProductMenu pm) {
		theProductMenu = pm;
	}
	
	public MeatProductMenu(Product p) {
//		System.out.println("g");
		
		currentProduct = p;
		try {
		    this.getContentPane().setLayout(null);
		    this.setTitle("");
		    buttonLogout.setText("Logout");
		    buttonLogout.setBounds(new Rectangle(120, 215, 200, 37));
		    buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
		    this.getContentPane().add(buttonLogout, null);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    setModal(true);
	    setSize(503,294);
	}

	public boolean showMenu() {
		showAddButton();
		showComboxes();
		setVisible(true);
		return isLoggedOut();
	}
	
	@SuppressWarnings("deprecation")
	void TradingAddButton_actionPerformed() {
		String seller = (String) tradingCombox.getSelectedItem();
		int randomDay = ThreadLocalRandom.current().nextInt(1, 31 + 1);
		int randomMoney = 10*ThreadLocalRandom.current().nextInt(1, 10 + 1);
		PTBS.mainFacade.addTrading(currentProduct, new Date(2022-1900, 9, randomDay), seller, randomMoney, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	void TradingViewButton_actionPerformed() {
		Trading theAss=(Trading)tradingCombox.getSelectedItem();
		PTBS.mainFacade.viewTrading(theAss);
	}
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName()) != null) {
		    for(String seller : PTBS.mainFacade.sellerOfProduct.get(currentProduct.getProductName())) {
		    	tradingCombox.addItem(seller);
		    }
		}
	}

	private void buttonLogout_actionPerformed(ActionEvent e) {
		isLoggedOut=true;
	    dispose();
	}
	public boolean isLoggedOut() {
		return isLoggedOut;
	}

	public void showAddButton() {
//		System.out.println("a");
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an offer to seller");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
		this.getContentPane().add(tradingAddButton, null);
	}

	public void showViewButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an offer to seller");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
	}

	public void showRadioButton() {
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}

	public void showLabels() {
//		System.out.println("d");
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Make an offer to seller");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
	}

	public void showComboxes() {
//		System.out.println("e");
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}
}