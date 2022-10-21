public class OfferingIterator implements ListIterator {

	private OfferingList offeringList;
	private int index = 0;

	public OfferingIterator(OfferingList p) {
		offeringList = p;
	}
	
	public boolean hasNext() {
		return (index < offeringList.size());
	}
	
	public Object Next() {
		if(hasNext()) {
			return offeringList.get(index++);
		} else {
			return null;
		}
	}
	
	public void MoveToHead() {
		index = 0;
	}
	
	public void Remove(int i) {
		offeringList.remove(i);
	}
}
