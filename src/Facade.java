import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Facade {

	int UserType;
	Product theSelectedProduct;
	int nProductCategory;
	ClassProductList theProductList;
	Person thePerson;
	HashSet<String> buyers = new HashSet<>();
	HashSet<String> sellers = new HashSet<>();
	HashMap<String, HashSet<String>> sellerOfProduct = new HashMap<>();
	HashMap<String, HashSet<Trading>> sellerPendingOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> sellerAcceptedOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> buyerAcceptedOffer = new HashMap<>();
	HashMap<String, HashSet<Trading>> buyerPendingOffer = new HashMap<>();

	public boolean login(UserInfoItem userInfo) {
		Login login = new Login();
		login.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				login.isLoggedOut = true;
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				login.isLoggedOut = true;
			}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		login.setModal(true);
		login.setVisible(true);
		userInfo.userName = login.getUserName();
		userInfo.userType = login.getUserType();
		return login.getIsLoggedOut();
	}
	
	public void acceptTrade(Product product, Trading trade, String seller) {
		sellerPendingOffer.get(seller).remove(trade);
		Trading temp = null;
		for(Trading t : buyerPendingOffer.get(trade.buyer)) {
			if(t.seller.equals(seller)) {
				temp = t;
			}
		}
		if(temp != null)
			buyerPendingOffer.get(trade.buyer).remove(temp);
		
//		System.out.println("\nRemaining: ");
//		System.out.println("Buyer: " + buyerPendingOffer);
//		System.out.println("Seller: " + sellerPendingOffer);
		
		if(!sellerAcceptedOffer.containsKey(seller)) {
			sellerAcceptedOffer.put(seller, new HashSet<>());
		}
		sellerAcceptedOffer.get(seller).add(trade);
		if(!buyerAcceptedOffer.containsKey(trade.buyer)) {
			buyerAcceptedOffer.put(trade.buyer, new HashSet<>());
		}
		buyerAcceptedOffer.get(trade.buyer).add(trade);
	}

	public void addTrading(Product product, Date dueDate, String seller, int price, String buyer) {
		
//		System.out.println("got the trade" + buyer + " -> " + seller);
		if(!sellerPendingOffer.containsKey(seller)) {
			sellerPendingOffer.put(seller, new HashSet<>());
		}
		sellerPendingOffer.get(seller).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
		if(!buyerPendingOffer.containsKey(buyer)) {
			buyerPendingOffer.put(buyer, new HashSet<>());
		}
		buyerPendingOffer.get(buyer).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
//		System.out.println("Offers:");
//		System.out.println(sellerPendingOffer);
//		System.out.println(buyerPendingOffer);
	}

	public void viewTrading(Trading trading) {
		TradingMenu tradingMenu = new TradingMenu();
		tradingMenu.showMenu(trading, thePerson);
	}

	public void decideBidding(String seller, Trading trade) {
		sellerPendingOffer.get(seller).remove(trade);
		Trading temp = null;
		for(Trading t : buyerPendingOffer.get(trade.buyer)) {
			if(t.seller.equals(seller)) {
				temp = t;
			}
		}
		if(temp != null)
			buyerPendingOffer.get(trade.buyer).remove(temp);
	}

	public void discussBidding(String seller, String buyer, Product product, Date dueDate, int price) {
		if(!sellerPendingOffer.containsKey(seller)) {
			sellerPendingOffer.put(seller, new HashSet<>());
		}
		sellerPendingOffer.get(seller).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
		if(!buyerPendingOffer.containsKey(buyer)) {
			buyerPendingOffer.put(buyer, new HashSet<>());
		}
		buyerPendingOffer.get(buyer).add(new Trading(product.getProductName(), dueDate, price, buyer, seller));
	}

	public void submitBidding(String seller, Trading trade) {
		if(!sellerAcceptedOffer.containsKey(seller)) {
			sellerAcceptedOffer.put(seller, new HashSet<>());
		}
		sellerAcceptedOffer.get(seller).add(trade);
		if(!buyerAcceptedOffer.containsKey(trade.buyer)) {
			buyerAcceptedOffer.put(trade.buyer, new HashSet<>());
		}
		buyerAcceptedOffer.get(trade.buyer).add(trade);
	}

	public void remind() {
//		System.out.println("Reminding");
		Reminder reminder = new Reminder();
		reminder.showReminder(thePerson.getProductList());
	}

	public void createUser(UserInfoItem userInfo) {
		if(userInfo.userType == UserInfoItem.USER_TYPE.Buyer) {
			thePerson = new Buyer();
		} else {
			thePerson = new Seller();
		}
		thePerson.setUserName(userInfo.userName);
	}

	public void createProductList() {
		theProductList = new ClassProductList();
		theProductList.initProductList();
//		System.out.println("Facade.java -> " + theProductList);
	}

	public void AttachProductToUser() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("database\\BuyerInfo.txt"));
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				buyers.add(parts[0]);
			}
			br.close();
			br = new BufferedReader(new FileReader("database\\SellerInfo.txt"));
			while((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				sellers.add(parts[0]);
			}
			br.close();
			br = new BufferedReader(new FileReader("database\\UserProduct.txt"));
			while((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				
				if(sellers.contains(parts[0])) {
					if(!sellerOfProduct.containsKey(parts[1])) {
						sellerOfProduct.put(parts[1], new HashSet<>());
					}
					sellerOfProduct.get(parts[1]).add(parts[0]);
				}
				
				if(parts[0].equals(thePerson.userName)) {
					theSelectedProduct = findProductByName(parts[1]);
					if(theSelectedProduct != null) {
						thePerson.addProduct(theSelectedProduct);
					}
				}
			}
			br.close();
			
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	private Product findProductByName(String pName) {
		ProductIterator it = new ProductIterator(theProductList);
		return (Product) it.find(pName);
	}

	public boolean SelectProduct() {
		SelectProductUI ui = new SelectProductUI();
		theSelectedProduct = ui.getSelectedProduct(thePerson.productList);
//		System.out.println("selected product " + theSelectedProduct);
		thePerson.currentProduct = theSelectedProduct;
		nProductCategory = ui.productCategory;
		return ui.isLoggedOut();
	}

	public boolean productOperation() {
		thePerson.CreateProductMenu(theSelectedProduct, nProductCategory);
		return thePerson.showMenu();
	}
}
