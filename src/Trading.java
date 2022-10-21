import java.text.DateFormat;
import java.util.Date;

public class Trading {

	String tradeName;
	Date dueDate = new Date();
	int price;
	String buyer;
	String seller;

	OfferingList offeringList;
	

public Trading(String tradeName, Date dueDate, int price, String buyer, String seller) {
		super();
		this.tradeName = tradeName;
		this.dueDate = dueDate;
		this.price = price;
		this.buyer = buyer;
		this.seller = seller;
	}



//	private Product product;
//	private OfferingList offeringList;	

	@Override
	public String toString() {
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
		return tradeName + " " + dateFormat.format(dueDate) + " $" + price;
	}



	public void accept(NodeVisitor visitor, int i) {
		visitor.visitTrading(this, i);
	}
	
	String getDueDateString() {
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
		return  dateFormat.format(dueDate);
	}

}
