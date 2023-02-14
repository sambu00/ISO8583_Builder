package com.isobuilder.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * GUI Bitmap Panel.
 * This panel includes 128 labels, one for each data element of the ISO Message.
 * The labels are highlighted in RED if the corresponding data element is NOT PRESENT
 * The labels are highlighted in GREEN if the corresponding data element is PRESENT 
 * 
 * @author Federico Alaimo
 *
 */
public class BitmapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel[] bitmapLabels;
	
	public BitmapPanel() {
		
		JPanel bPanel = new JPanel();
		JPanel innerPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(0, 8, 2, 1));
		bitmapLabels = new JLabel[128];
				
		for (int i = 0; i < bitmapLabels.length; i++) {
			bitmapLabels[i] = new JLabel(generateLabelString(i), SwingConstants.CENTER);
			bitmapLabels[i].setOpaque(true);
			bitmapLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			bitmapLabels[i].setBackground(Color.RED);
			innerPanel.add(bitmapLabels[i]);			
			
		}
		bPanel.add(innerPanel);
		bPanel.setBorder(BorderFactory.createTitledBorder("Bitmap"));
		
		add(bPanel);
		add(emptyPanel);
		
	}
	
	public JLabel[] getBitmapLabels() {
		return bitmapLabels;
	}

	private String generateLabelString(int n) {

		return String.format("%03d", n + 1);
	}
	
	

}
