package com.isobuilder.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI MTI Panel.
 * Panel to set and show message type of the ISO Message
 * 
 * @author Federico Alaimo
 *
*/
public class MTIPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField mtiTextField;
	private JButton mtiButton;
	private JLabel mtiLabel;
	
	public MTIPanel() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc;
		Insets cellInsets = new Insets(3, 3, 3, 3);
		
		mtiTextField = new JTextField(4);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		add(mtiTextField, gbc);
				
		mtiButton = new JButton("Set MTI");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		add(mtiButton, gbc);
		
		mtiLabel = new JLabel();
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(mtiLabel, gbc);
		
	}

	public String getMtiString() {
		return mtiTextField.getText();
	}

	public void setMtiLabelText(String mti) {
		mtiLabel.setText(mti);
	}
	
	public void setMtiButtonListener(ActionListener actionListener) {
		mtiButton.addActionListener(actionListener);
	}

	
	
}
