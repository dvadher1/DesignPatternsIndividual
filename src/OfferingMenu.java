import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OfferingMenu extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Trading theTrading;
	private JComboBox<Offering> comboOfferingList = new JComboBox<>();
	
	private JTextField offeringName = new JTextField();
	private JTextField dueDate = new JTextField();

	private JLabel jLabel1 = new JLabel();
	private JButton buttonClose = new JButton();
	
	OfferingMenu() {
		try {
		  jLabel1.setText("Assignment Name");
	      jLabel1.setBounds(new Rectangle(25, 31, 118, 18));
	      this.getContentPane().setLayout(null);
	      offeringName.setText("jTextField1");
	      offeringName.setBounds(new Rectangle(192, 31, 341, 22));
	      dueDate.setText("dueDate");
	      dueDate.setBounds(new Rectangle(195, 87, 337, 22));
	      buttonClose.setText("Close");
	      buttonClose.setBounds(new Rectangle(86, 253, 79, 29));
	      buttonClose.addActionListener(this::buttonClose_actionPerformed);
	      comboOfferingList.setBounds(new Rectangle(32, 204, 413, 22));
	      this.getContentPane().add(jLabel1, null);
	      this.getContentPane().add(offeringName, null);
	      this.getContentPane().add(dueDate, null);
	      this.getContentPane().add(buttonClose, null);
	      this.getContentPane().add(comboOfferingList, null);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		setModal(true);
		setSize(575,330);
	}
	
	void showMenu(Trading trade, Person person) {
		theTrading=trade;
	    offeringName.setText(theTrading.tradeName );

	    DateFormat theDateFormat=DateFormat.getDateInstance(DateFormat.SHORT );
	    dueDate.setText(theDateFormat.format(theTrading.dueDate));
	    refreshSolutionList();
	    setVisible(true);
	}
	
	private void buttonClose_actionPerformed(ActionEvent e) {
	    theTrading.tradeName = offeringName.getText() ;
	    DateFormat tempDateFormat=DateFormat.getDateInstance(DateFormat.SHORT );
	    try
	    {
	      theTrading.dueDate=tempDateFormat.parse(dueDate.getText() );
	    }catch (Exception ignored){}
	    dispose();
	}
	
	private void refreshSolutionList() {
	    comboOfferingList.removeAllItems() ;
	    OfferingIterator it=new OfferingIterator(theTrading.offeringList);
	    while(it.hasNext() )
	    {
	        Offering offering = (Offering) it.Next();
	      comboOfferingList.addItem(offering);
	    }
	}
}