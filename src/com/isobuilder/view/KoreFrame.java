package com.isobuilder.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * GUI KORE Frame.
 * This frame is used to show the ISO Message in KORE format.
 * KORE format is expressed in hexadecimal value grouped by words (4 bytes).
 * Message 1111222233334444 is showed as:
 * F1F1F1F1 F2F2F2F2 F3F3F3F3 F4F4F4F4
 * 
 * Each line contains 4 words, a page contains 20 lines
 * 
 * @author Federico Alaimo
 *
 */public class KoreFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea koreTextArea;

	public KoreFrame(String korePlainMsg) {

		// WINDOWS PROPERTIES
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("KORE message");
		setSize(400, 850);
		setLayout(new BorderLayout());

		
		URL imageURL = getClass().getResource("/resources/icons/iso8583_icon.png");
		if (imageURL != null) {
		    ImageIcon icon = new ImageIcon(imageURL);
		setIconImage(icon.getImage());
		}

		setLocationRelativeTo(null); // center Frame

		koreTextArea = new JTextArea(splitMsg(korePlainMsg));
		Font font = new Font("Courier New", Font.BOLD, 15);
		koreTextArea.setFont(font);

		add(new JScrollPane(koreTextArea), BorderLayout.CENTER);

		setVisible(true);

	}

	private String splitMsg(String plainMsg) {
		String splitted = "";

		int charCount = 0;
		int spaceCount = 0;
		int lineCount = 0;
		int pageCount = 0;

		while (charCount < plainMsg.length()) {
			splitted += plainMsg.charAt(charCount);

			charCount++;
			spaceCount++;
			lineCount++;

			if (spaceCount > 7) {
				splitted += ' ';
				spaceCount = 0;
			}

			if (lineCount > 31) {
				splitted += "\n";
				lineCount = 0;
				pageCount++;
			}

			if (pageCount == 20) {
				splitted += "\n";
				pageCount = 0;
			}
		}

		return splitted;
	}
}