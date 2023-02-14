package com.isobuilder.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.isobuilder.backend.dataelement.DataElement;
import com.isobuilder.backend.dataelement.MessageElementValue;
import com.isobuilder.backend.format.DEFormatEnum;

/**
 * GUI Data Element Frame.
 * This frame is used to inspect a data element details.
 * It shows every data element attribute, including the effective length of the value.
 * Actions allowed:
 * . UPDATE data element value
 * . REMOVE data element from the ISO Message
 * . CLOSE the frame
 * 
 * @author Federico Alaimo
 *
 */
public class DataElementFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DataElement de;

	private JLabel deNum;
	private JLabel deFormat;
	private JLabel deMaxLength;
	private JLabel deLength;
	private JLabel notifyUpdate;

	private JButton removeDEButton;
	private JButton updateDEButton;
	private JButton closeButton;

	private JTextArea deValueTextArea;

	private JPanel deInfoPanel;
	private JPanel deActionsPanel;
	private JPanel deValuePanel;
	private JPanel closePanel;
	private JPanel notifyPanel;

	public DataElementFrame(DataElement de) {

		this.de = de;

		// WINDOWS PROPERTIES
		windowProperties();

		// COMPONENTS
		generateComponents();

		// DESIGN WINDOW
		designWindow();

		// INTERNAL LISTENERS
		setInternalListeners();

		
		displayDELength();
		
		pack();
		setVisible(true);

	}
	
	public void setRemoveListener(ActionListener al) {
		removeDEButton.addActionListener(al);
		
	}

	public void setUpdateValueListener(ActionListener al) {
		updateDEButton.addActionListener(al);
	}

	public String getDEValue() {
		return deValueTextArea.getText();
	}

	private void windowProperties() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Data Element " + getDEPositionStirng());
		setSize(500, 500);

		this.setLayout(new GridBagLayout());

		URL imageURL = getClass().getResource("/resources/icons/iso8583_icon.png");
		if (imageURL != null) {
		    ImageIcon icon = new ImageIcon(imageURL);
		setIconImage(icon.getImage());
		}

		setLocationRelativeTo(null); // center Frame
	}

	private void generateComponents() {
		// LABELS
		deNum = new JLabel(getDEPositionStirng());
		deFormat = new JLabel(de.getDeFormat().getFormat().toString());
		deLength = new JLabel();
		deMaxLength = new JLabel(Integer.toString(de.getDeFormat().getMaxLength()));
		notifyUpdate = new JLabel();
		notifyUpdate.setForeground(Color.BLUE);

		// BUTTONS
		removeDEButton = new JButton("Remove Data Element");
		updateDEButton = new JButton("Update Value");
		closeButton = new JButton("Close");

		// TEXTAREA
		deValueTextArea = new JTextArea(de.getPlainValue());
		Font font = new Font("Courier New", Font.PLAIN, 12);
		deValueTextArea.setFont(font);
		deValueTextArea.setLineWrap(true);
		
	}

	private JPanel designInfoPanel() {
		JPanel p = new JPanel();

		p.setLayout(new GridBagLayout());

		GridBagConstraints gbc;
		Insets insets = new Insets(3, 3, 3, 3);

		JLabel deNumLabel = new JLabel("Data Element:");
		JLabel deFormatLabel = new JLabel("Format:");
		JLabel deMaxLengthLabel = new JLabel("Max lenght:");
		JLabel deLengthLabel = new JLabel("Length:");

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = insets;
		p.add(deNumLabel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = insets;
		p.add(deNum, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = insets;
		p.add(deFormatLabel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.insets = insets;
		p.add(deFormat, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = insets;
		p.add(deMaxLengthLabel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = insets;
		p.add(deMaxLength, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = insets;
		p.add(deLengthLabel, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = insets;
		p.add(deLength, gbc);

		p.setBorder(BorderFactory
				.createTitledBorder("Data Element Information"));

		return p;
	}

	private JPanel designActionsPanel() {
		JPanel p = new JPanel();

		p.setLayout(new GridLayout(0, 1));

		p.add(removeDEButton);
		p.add(updateDEButton);

		p.setBorder(BorderFactory.createTitledBorder("Actions"));

		return p;
	}

	private JPanel designValuePanel() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		p.add(new JScrollPane(deValueTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		p.setBorder(BorderFactory.createTitledBorder("Data Element value"));

		p.setPreferredSize(new Dimension(730, 500));

		return p;
	}

	private JPanel designNotifyPanel() {
		JPanel p = new JPanel();
		
		p.add(notifyUpdate);
		
		return p;
	}
	
	private JPanel designClosePanel() {
		JPanel p = new JPanel();

		p.add(closeButton);

		return p;
	}

	private void designWindow() {
		GridBagConstraints gbc;

		deInfoPanel = designInfoPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(deInfoPanel, gbc);

		deActionsPanel = designActionsPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(deActionsPanel, gbc);

		deValuePanel = designValuePanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 5;
		gbc.weighty = 0.9;
		gbc.fill = GridBagConstraints.NONE;
		add(deValuePanel, gbc);

		notifyPanel = designNotifyPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(notifyPanel, gbc);
		
		closePanel = designClosePanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(closePanel, gbc);

	}

	private void setInternalListeners() {
		// close the frame
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}

		});
		
		// change data element length at each change in the text area
		deValueTextArea.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent arg0) {
				displayDELength();
			}

			public void insertUpdate(DocumentEvent arg0) {
				displayDELength();
			}

			public void removeUpdate(DocumentEvent arg0) {
				displayDELength();
			}
			
		});
		
		// disable all components when removing a data element
		removeDEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deValueTextArea.setEnabled(false);
				removeDEButton.setEnabled(false);
				updateDEButton.setEnabled(false);
			}
			
		});
		
		// notify data element value update
		updateDEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyUpdate.setText("Data Element value updated!");
				hideNotifyLabel();
				
			}	
		});
		
	}

	private void displayDELength() {
		int deEffLength = MessageElementValue.valueLength(deValueTextArea.getText());
				
		deLength.setText(Integer.toString(deEffLength));
		
		int effLen = Integer.parseInt(deLength.getText());
		int maxLen = de.getDeFormat().getMaxLength();

		// notify length error
		deLength.setForeground(Color.BLACK);
		
		if (effLen == 0) {
			deLength.setForeground(Color.RED);
		}
		
		if (de.getDeFormat().getFormat() == DEFormatEnum.FIXED) {
			if (effLen != maxLen) {
				deLength.setForeground(Color.RED);
			} 
		} else {
			if (effLen > maxLen) {
				deLength.setForeground(Color.RED);
			} 
		}		

	}

	private String getDEPositionStirng() {
		return String.format("%03d", de.getPosition());
	}
	
	private void hideNotifyLabel() {
		Timer timer = new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notifyUpdate.setText("");;
			}
		});

		timer.setRepeats(false);
		timer.start();

	}

}
