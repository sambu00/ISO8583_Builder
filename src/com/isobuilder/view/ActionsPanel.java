package com.isobuilder.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * GUI Actions Panel.
 * This panel inlcudes the following buttons: 
 * . MasterCard purchase example
 * . VISA purchase example
 * . Show ISO Message in 'KORE' format
 * 
 * @author Federico Alaimo
 *
 */
public class ActionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton mcExampleButton;
	private JButton visaExample;
	
	private JButton koreButton;
	
	public ActionsPanel() {
		JPanel aPanel = new JPanel();
		JPanel innerPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(0, 1, 2, 2));
		
		mcExampleButton = new JButton("MASTERCARD sample message");
		visaExample = new JButton("VISA sample message");
		
		koreButton = new JButton("Show Kore Message");
				
		innerPanel.add(mcExampleButton);
		innerPanel.add(visaExample);
		innerPanel.add(new JSeparator());
		innerPanel.add(koreButton);		
		
		aPanel.add(innerPanel);
		aPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		
		add(aPanel);
		add(emptyPanel);
		
		
	}
	
	public void setMcExampleButtonListener(ActionListener actionListener) {
		mcExampleButton.addActionListener(actionListener);
	}

	public void setVisaExampleButtonListener(ActionListener actionListener) {
		visaExample.addActionListener(actionListener);
	}

	public void setKoreButtonListener(ActionListener actionListener) {
		koreButton.addActionListener(actionListener);
	}


}
