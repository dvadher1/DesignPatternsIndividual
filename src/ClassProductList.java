import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ClassProductList extends ArrayList<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassProductList() {}
	
	public void initProductList() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("database\\ProductInfo.txt"));
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				Product p = new Product(parts[1], (parts[0].equals("Meat")?0:1));
				this.add(p);
			}
			br.close();
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void accept(NodeVisitor visitor) {
		visitor.visitFacade(null);
	}
}