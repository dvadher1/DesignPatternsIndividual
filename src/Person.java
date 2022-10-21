public abstract class Person {
	
	String userName;
	ClassProductList productList;
	ProductMenu theProductMenu;
	Product currentProduct;
	
	public Person() {
		productList = new ClassProductList();
	}

	public abstract ProductMenu CreateProductMenu(Product product, int productType);
	public abstract boolean showMenu();
	
	public void showAddButton() {
		theProductMenu.showAddButton();
	}

	public void showViewButton() {
		theProductMenu.showViewButton();
	}

	public void showRadioButton() {
		theProductMenu.showRadioButton();
	}

	public void showLabels() {
		theProductMenu.showLabels();
	}
	
	public void showComboxes() {
		theProductMenu.showComboxes();
	}
	
	public void show() {
		theProductMenu.setVisible(true);
	}
	
	public boolean isLoggedOut() {
		return theProductMenu.isLoggedOut();
	}
	
	ClassProductList getProductList() {
		return productList;
	}
	
	public void addProduct(Product p) {
		productList.add(p);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String s) {
		this.userName = s;
	}
}
