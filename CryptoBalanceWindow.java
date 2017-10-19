import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.MenuSelectionManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptoBalanceWindow extends JFrame{
	
	// FRAME CLASS FOR CRYPTOCURRENCY CALCULATOR
	
	int a = 235;										// window width
	int b = 90;											// window height
	String label = "Crypto Balance Window";				// window name
	CryptoBalancePane[] p = new CryptoBalancePane[10];	// array of cryptocurrencies used to keep track of profile currencies
	int countCBP = 0;									// counter used for referencing array items in p
	String[] s = {"USD", "PLN"};
	
	public CryptoBalanceWindow(){
		
		//for(Strin i : )
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run(){
				
				//instantiates frame
				JFrame window = new JFrame();
				window.setSize(new Dimension(a,b));
				window.setTitle(label);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setLocationRelativeTo(null);
				
				//add jmenubar to frame
				JMenuBar menu_bar = new JMenuBar();
				JMenu file_menu = new JMenu("Reference currency");
				menu_bar.add(file_menu);
				window.setJMenuBar(menu_bar);
								
				//superpanel
				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); //<-- NOT IN USE
				mainPanel.setSize(a,b);
				window.setContentPane(mainPanel);
				window.setVisible(true);
				
				//initial subpanel for balance pane
				CryptoBalancePane panel = new CryptoBalancePane();
				p[countCBP] = panel;
				mainPanel.add(p[countCBP].pane1,countCBP++);
				
				//button panel
				CryptoBalancePane_button button_panel = new CryptoBalancePane_button();
				button_panel.setSize(new Dimension(a,20));
				button_panel.setMaximumSize(new Dimension(a,20));
				mainPanel.add(button_panel.pane1);
				
				//wrap the entire window around the content and unset resizability
				window.pack();
				window.setResizable(false);
				
				//actionlistener for button in button_panel 
				button_panel.addNewCurrencyButton.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//for every click of the add crypto button you produce a new cryptopane and expand the window
						try{
							CryptoBalancePane additionalPanel = new CryptoBalancePane();
							p[countCBP] = additionalPanel;
							mainPanel.add(p[countCBP++].pane1, mainPanel.getComponentCount()-1);
							window.setVisible(true);
							window.pack();
							System.out.println(mainPanel.getWidth() + ", " + mainPanel.getHeight());
							System.out.println("countCBP: " + countCBP);
						}
						// if you already have 10 cryptopanes, you've reached the limit and the add crypto button will be disabled
						catch(ArrayIndexOutOfBoundsException r){
							button_panel.addNewCurrencyButton.setText("limit reached");
							button_panel.addNewCurrencyButton.setFont(new Font("Tahoma", Font.BOLD, 10));
							button_panel.addNewCurrencyButton.setForeground(Color.RED);
							button_panel.addNewCurrencyButton.setEnabled(false);
						}
						
					}
					
				});
				
				// calculation procedure occuring after you click the Calculate button
				button_panel.calculateBalanceButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						
						int i = 0;
						for(CryptoBalancePane g : p){
							
							if (p[i++] != null){
								if (g.menuBox1.getSelectedItem() != null){
									g.fiatCurrencyText.setEnabled(true);
									Double multiplication = Double.parseDouble(g.freeEntryField1.getText())*g.b;	// multiplication is your balance against the rate
					            	multiplication = round_no(multiplication, 2);
									g.fiatCurrencyText.setText(multiplication.toString() + "$");
									g.fiatCurrencyText.repaint();
									g.fiatCurrencyText.setEnabled(false);
								}
								else if (panel.menuBox1.getSelectedItem() == null){
									continue;
									}
							}
							else{
								continue;
							}
						}
						mainPanel.repaint();
						window.pack();
					}
					
				}); 
			}
		});
	}
	
	//method that returns a jmenu with items from string array
	public JMenu MenuMaker(JMenu m, String[] s){
		for(int i=0; i<s.length;i++){
			JMenuItem jm = new JMenuItem(s[i].toString());
			m.add(jm);
		}
		return m;
	}
	
	// method that returns a double rounded to the desired number of decimal places
	
	private double round_no(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}


