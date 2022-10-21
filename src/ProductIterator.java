public class ProductIterator implements ListIterator {

	private ClassProductList classProductList;
	private int index = 0;

	public ProductIterator(ClassProductList p) {
		classProductList = p;
	}
	
	public boolean hasNext() {
		return (index < classProductList.size());
	}
	
	public Object Next() {
		if(hasNext()) {
			return classProductList.get(index++);
		} else {
			return null;
		}
	}
	
	public void MoveToHead() {
		index = 0;
	}
	
	public void Remove(int i) {
		classProductList.remove(i);
	}

	public Object find(String pName) {
//		System.out.println(pName);
		Product p = (Product)Next();
		while(p!=null) {
//			System.out.println(p);
//			System.out.println();
			if(pName.equals(p.getProductName())) {
				return p;
			}
			p = (Product)Next();
		}
		return null;
	}
}




