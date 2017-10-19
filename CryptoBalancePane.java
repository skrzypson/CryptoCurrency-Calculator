import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CryptoBalancePane extends JPanel{
	
	int c = 300;
	int d = 200;
	JPanel pane1;
	static String[] currencyList = {"Bitcoin", "Ethereum", "Lisk", "OmiseGO", "Ripple"};
	static Map<String, Double> menuItems = createCurrencyMap();	// map that stores menu item names and references to currency prices
	JComboBox<String> menuBox1;
	JTextField freeEntryField1;
	JTextArea fiatCurrencyTextEquals, fiatCurrencyText;
	Double b = 0.0;
	
	public CryptoBalancePane(){
		makePanel();
	}
		
	private void makePanel(){
		//creates panel
		pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane1.setBackground(Color.WHITE);
		
		//creates and adds currency menubox
		menuBox1 = new JComboBox(currencyList);
		menuBox1.setSelectedItem(null);
		menuBox1.setPreferredSize(new Dimension(c/2,d/10));
		
		//what happens once you choose an item from the drop down list
		menuBox1.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Object item = (String) e.getItem();
					for (Entry<String, Double> entry : menuItems.entrySet()){  
			            if (entry.getKey().equals(item)) {
			            	b = entry.getValue();		// b is the exchange rate
			                break;
			            }
			    	}
					//checks b
					System.out.println("b = " + b);
				}
			}
		});
		
		pane1.add(menuBox1);
		
		//creates and adds editable textfield
		freeEntryField1 = new JTextField("0.0");
		freeEntryField1.setPreferredSize(new Dimension(c/4,d/10));
		pane1.add(freeEntryField1);
		
		//creates and adds non-editable text areas
		fiatCurrencyTextEquals = new JTextArea(" = ");
		fiatCurrencyTextEquals.setEnabled(false);
		pane1.add(fiatCurrencyTextEquals);
		fiatCurrencyText = new JTextArea(/*b.toString()*/);
		pane1.add(fiatCurrencyText);
		
		//check hashmap
		System.out.println(Collections.singletonList(menuItems));
		
	}
	
	//produces a map (from the currencylist) containing references to each web address containing the currency's price
	private static Map<String, Double> createCurrencyMap(){
		Map<String, Double> menuItems = new LinkedHashMap<>();
		for(String s : currencyList){
			menuItems.put(s, Double.parseDouble(priceScraper.scrape(s)));
		}		
		return menuItems;
	}
}
