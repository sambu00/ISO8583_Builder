package com.isobuilder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.isobuilder.backend.dataelement.MessageElementValue;

/**
 * GUI DataElement Panel.
 * This panel includes all the fields to prepare a new data element or to select an already present data element.
 * Then the data element can be added or removed from the ISO Message, or its value can be changed.
 * 
 * @author Federico Alaimo
 *
 */
public class DataElementPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel deNumLabel;
	private JLabel valueLabel;
	private JLabel notifyLabel;
	private JTextField deNumTextField;
	private JTextField valueTextField;
	private JButton addDEButton;
	private JButton appendDEButton;
	private JButton deleteDEButton;
	private JButton resetFieldsButton;
	private JCheckBox hexCheckBox;

	public DataElementPanel() {
		
		JPanel innerPanel = new JPanel(new GridBagLayout());
		
		//this.setLayout(new GridBagLayout());
		this.setLayout(new BorderLayout());

		GridBagConstraints gbc;
		Insets cellInsets = new Insets(3, 3, 3, 3);

		deNumLabel = new JLabel("Position");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		innerPanel.add(deNumLabel, gbc);

		deNumTextField = new JTextField(3);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		gbc.weightx = 0.5;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		innerPanel.add(deNumTextField, gbc);
		
		notifyLabel = new JLabel();
		notifyLabel.setForeground(Color.BLUE);
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		gbc.anchor = GridBagConstraints.LINE_START;
		innerPanel.add(notifyLabel, gbc);

		addDEButton = new JButton("Add/Replace");
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		innerPanel.add(addDEButton, gbc);

		appendDEButton = new JButton("Append");
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		innerPanel.add(appendDEButton, gbc);

		deleteDEButton = new JButton("Remove");
		gbc = new GridBagConstraints();
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.insets = cellInsets;
		innerPanel.add(deleteDEButton, gbc);

		valueLabel = new JLabel("Value");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = cellInsets;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		innerPanel.add(valueLabel, gbc);

		valueTextField = new JTextField();
		JScrollPane scrlPane = new JScrollPane(valueTextField,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = cellInsets;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		innerPanel.add(scrlPane, gbc);

		hexCheckBox = new JCheckBox("as HEX value");
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = cellInsets;
		innerPanel.add(hexCheckBox, gbc);
		
		resetFieldsButton = new JButton("Reset"); 
		gbc = new GridBagConstraints();
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.insets = cellInsets;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		innerPanel.add(resetFieldsButton, gbc);
		
		innerPanel.setBorder(BorderFactory.createTitledBorder("Data Element"));
		
		add(innerPanel, BorderLayout.CENTER);
		
		internalListeners();
	}

	public void setAddDEButtonListener(ActionListener actionListener) {
		addDEButton.addActionListener(actionListener);
	}

	public void setAppendDEButtonListener(ActionListener actionListener) {
		appendDEButton.addActionListener(actionListener);
	}

	public void setDeleteDEButtonListener(ActionListener actionListener) {
		deleteDEButton.addActionListener(actionListener);
	}

	public int getDENum() {
		return Integer.parseInt(deNumTextField.getText());
	}

	public String getDEValue() {
		if (hexCheckBox.isSelected()) {
			return MessageElementValue.HEX_TAG_OPEN + valueTextField.getText()
					+ MessageElementValue.HEX_TAG_CLOSE;
		} else {
			return valueTextField.getText();
		}
	}
	
	public void setDEFields(String deNumString, String deValueString) {
		deNumTextField.setText(deNumString);
		valueTextField.setText(deValueString);
	}

	public void resetInputFields() {
		deNumTextField.setText("");
		valueTextField.setText("");
		hexCheckBox.setSelected(false);
	}
	
	public void resetDeNumTextField() {
		deNumTextField.setText("");
	}

	public void resetValueTextField() {
		valueTextField.setText("");
	}
	
	public void resetHexCheckBox() {
		hexCheckBox.setSelected(false);
	}
	
	/**
	 * listeners for internal component behaviors
	 */
	private void internalListeners() {
		// add data element
		addDEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyLabel.setText("Added");
				hideNotifyLabel();
			}
		});
		
		// append value to data element
		appendDEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyLabel.setText("Appended");
				hideNotifyLabel();
			}
		});
		
		// remove data element
		deleteDEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyLabel.setText("Removed");
				hideNotifyLabel();
			}
		});
		
		// reset input fields
		resetFieldsButton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				resetInputFields();
			}
			
		});
	}
	
	private void hideNotifyLabel() {
		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyLabel.setText("");;
			}
		});

		timer.setRepeats(false);
		timer.start();

	}

}
