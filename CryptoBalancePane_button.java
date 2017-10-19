import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptoBalancePane_button extends JPanel{
	
	int c = 300;
	int d = 200;
	int panelCount = 0;
	JPanel pane1;
	JButton addNewCurrencyButton, calculateBalanceButton;
	
	public CryptoBalancePane_button(){
		makeButtonPanel();
	}
		
	private void makeButtonPanel(){
		//creates panel
		pane1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//pane1.setPreferredSize(new Dimension(c/4,d/4));
		pane1.setBackground(new Color(229, 229, 229));
		//pane1.setAlignmentX(RIGHT_ALIGNMENT);
		
		//creates and adds addCurrencyButton button
		addNewCurrencyButton = new JButton("Add crypto");
		pane1.add(addNewCurrencyButton);
		addNewCurrencyButton.setPreferredSize(new Dimension(c/3,d/10));
		addNewCurrencyButton.setForeground(new Color(00, 170, 10));
		
		//creates and adds calculateBalanceButton button
		calculateBalanceButton = new JButton("Calculate");
		pane1.add(calculateBalanceButton);
		calculateBalanceButton.setPreferredSize(new Dimension(c/3,d/10));
		calculateBalanceButton.setForeground(new Color(00, 66, 99));
	}
}
