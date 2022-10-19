import java.util.ArrayList;

public class ClassProductList extends ArrayList<ClassProductList> {

	private ProductIterator productIterator;

	private Product[] product;

	private ReminderVisitor reminderVisitor;

	public void accept(NodeVisitor visitor) {

	}

}
