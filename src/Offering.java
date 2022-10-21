public class Offering {

	private OfferingList offeringList;
	String offeringName;
	
	public Offering(String offeringName) {
		this.offeringName = offeringName;
	}

	public OfferingList getOfferingList() {
		return offeringList;
	}

	public void setOfferingList(OfferingList offeringList) {
		this.offeringList = offeringList;
	}
}
