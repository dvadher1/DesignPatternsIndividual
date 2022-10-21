import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ProduceProductMenu extends ProductMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Product product;
	protected boolean isLoggedOut = true;
	ProductMenu theProductMenu;
	Product currentProduct;
	
	JComboBox<Trading> tradingCombox = new JComboBox<>();
	JButton tradingAddButton = new JButton();
	private JButton buttonLogout = new JButton();
	
	public void setProductMenu(ProductMenu pm) {
		theProductMenu = pm;
	}
	
	public ProduceProductMenu(Product p) {
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
		theProductMenu.product = currentProduct;
		showAddButton();
		showComboxes();
		setVisible(true);
		return isLoggedOut();
	}
	
	void TradingAddButton_actionPerformed() {
		Trading trade = (Trading) tradingCombox.getSelectedItem();
		PTBS.mainFacade.acceptTrade(currentProduct, trade, PTBS.mainFacade.thePerson.getUserName());
		refresh();
	}
	
	void TradingViewButton_actionPerformed() {
		Trading theAss=(Trading)tradingCombox.getSelectedItem();
		PTBS.mainFacade.viewTrading(theAss);
	}
	
	void refresh() {
		tradingCombox.removeAllItems();
		if(PTBS.mainFacade.sellerPendingOffer.get(PTBS.mainFacade.thePerson.getUserName()) != null) {
			for(Trading trade : PTBS.mainFacade.sellerPendingOffer.get(PTBS.mainFacade.thePerson.getUserName())) {
				if(trade.tradeName.equals(currentProduct.getProductName()))
					tradingCombox.addItem(trade);
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
		tradingAddButton.setText("Accept Offer");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
		this.getContentPane().add(tradingAddButton, null);
	}

	public void showViewButton() {
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}

	public void showRadioButton() {
		tradingAddButton.addActionListener(e -> TradingAddButton_actionPerformed());
		tradingAddButton.setText("Accept Offer");
		tradingAddButton.setBounds(new Rectangle(240, 54, 200, 29));
		this.getContentPane().add(tradingAddButton, null);
	}

	public void showLabels() {
//		System.out.println("d");
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}

	public void showComboxes() {
//		System.out.println("e");
		tradingCombox.setBounds(new Rectangle(20, 57, 200, 22));
		this.getContentPane().add(tradingCombox, null);
		refresh();
	}

}
