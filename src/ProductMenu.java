import javax.swing.JDialog;

public abstract class ProductMenu extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Product product;
	protected boolean isLoggedOut = true;
	
	public boolean isLoggedOut() {
		return isLoggedOut;
	}

	public void setLoggedOut(boolean isLoggedOut) {
		this.isLoggedOut = isLoggedOut;
	}

	public ProductMenu() {
	}
	
	public abstract boolean showMenu();
	public abstract void showAddButton();
	public abstract void showViewButton();
	public abstract void showRadioButton();
	public abstract void showLabels();
	public abstract void showComboxes();
	protected abstract void setProductMenu(ProductMenu theProductMenu);
}