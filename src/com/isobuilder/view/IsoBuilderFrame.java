package com.isobuilder.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;


/**
 * GUI ISO Builder Frame. This is the principal Frame of the application and
 * it's used to manage the ISO Message. The are several panels included that are
 * created by the controller and passed to the constructor.
 * 
 * @author Federico Alaimo
 *
 */
public class IsoBuilderFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newIsoMsgMenuItem;
	private JMenuItem importMenuItem;
	private JMenuItem exportMenuItem;
	private JMenu windowMenu;
	private JCheckBoxMenuItem bitmapCheckBoxMenuItem;
	private JMenu samplesMenu;
	private JMenuItem mcSampleMenuItem;
	private JMenuItem visaSampleMenuItem;
	private JMenuItem tarMDESSampleMenuItem;
	private JMenuItem tarVTSSampleMenuItem;
	private JMenuItem tcnMDESSampleMenuItem;
	private JMenuItem tcnVTSSampleMenuItem;
	private JMenuItem purchaseMDESSampleMenuItem;
	private JMenuItem purchaseVTSSampleMenuItem;

	private JPanel centerPanel;
	private JPanel bitmapPanel;

	public IsoBuilderFrame(JPanel bitmapPanel, JPanel dataElementPanel,
			JPanel mtiPanel, JPanel isoTablePanel, JPanel actionsPanel) {
		super("Iso 8583 Builder");

		// WINDOWS PROPERTIES
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		URL imageURL = getClass().getResource("/resources/icons/iso8583_icon.png");
		if (imageURL != null) {
		    ImageIcon icon = new ImageIcon(imageURL);
		setIconImage(icon.getImage());
		}

		prepareMenuBar();

		setDataElementPanel(dataElementPanel);

		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);

		centerPanel.setLayout(new GridBagLayout());

		setBitmapPanel(bitmapPanel, bitmapCheckBoxMenuItem.isSelected());

		setMessageStructurePanel(mtiPanel, isoTablePanel);

		setActionsPanel(actionsPanel);

		internalListeners();

		setVisible(true);

	}

	public void setNewIsoMsgMenuItemListener(ActionListener al) {
		newIsoMsgMenuItem.addActionListener(al);
	}

	public void setImportMenuItemListener(ActionListener al) {
		importMenuItem.addActionListener(al);
	}

	public void setExportMenuItemListener(ActionListener al) {
		exportMenuItem.addActionListener(al);
	}

	public void setMcSampleMenuItemListener(ActionListener al) {
		mcSampleMenuItem.addActionListener(al);
	}

	public void setVisaSampleMenuItemListener(ActionListener al) {
		visaSampleMenuItem.addActionListener(al);
	}

	public void setTarMDESSampleMenuItemListener(ActionListener al) {
		tarMDESSampleMenuItem.addActionListener(al);
	}

	public void setTcnMDESSampleMenuItemListener(ActionListener al) {
		tcnMDESSampleMenuItem.addActionListener(al);
	}

	public void setTarVTSSampleMenuItemListener(ActionListener al) {
		tarVTSSampleMenuItem.addActionListener(al);
	}

	public void setTcnVTSSSampleMenuItemListener(ActionListener al) {
		tcnVTSSampleMenuItem.addActionListener(al);
	}

	public void setPurchaseMDESSampleMenuItem(ActionListener al) {
		purchaseMDESSampleMenuItem.addActionListener(al);
	}

	public void setPurchaseVTSSampleMenuItem(ActionListener al) {
		purchaseVTSSampleMenuItem.addActionListener(al);
	}

	private void setDataElementPanel(JPanel dataElementPanel) {
		add(dataElementPanel, BorderLayout.NORTH);
	}

	private void setBitmapPanel(JPanel bitmapPanel, boolean visible) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.anchor = GridBagConstraints.LINE_START;

		centerPanel.add(bitmapPanel, gbc);
		bitmapPanel.setVisible(visible);

		this.bitmapPanel = bitmapPanel;
	}

	private void setMessageStructurePanel(JPanel mtiPanel, JPanel isoTablePanel) {
		JPanel messageStructurePanel = new JPanel();
		messageStructurePanel.setLayout(new BorderLayout());

		JPanel innerPanel = new JPanel();

		innerPanel.setLayout(new BorderLayout());

		innerPanel.add(mtiPanel, BorderLayout.NORTH);
		innerPanel.add(isoTablePanel, BorderLayout.CENTER);

		innerPanel.setBorder(BorderFactory.createTitledBorder("ISO Message"));
		messageStructurePanel.add(innerPanel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		centerPanel.add(messageStructurePanel, gbc);

	}

	private void setActionsPanel(JPanel actionsPanel) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		centerPanel.add(actionsPanel, gbc);
	}

	private void prepareMenuBar() {
		menuBar = new JMenuBar();

		// "File" - used for IMPORT/EXPORT functions
		fileMenu = new JMenu("File");
		newIsoMsgMenuItem = new JMenuItem("New ISO Message",
				UIManager.getIcon("Tree.leafIcon"));
		importMenuItem = new JMenuItem("Open",
				UIManager.getIcon("FileView.directoryIcon"));
		exportMenuItem = new JMenuItem("Save",
				UIManager.getIcon("FileView.floppyDriveIcon"));

		fileMenu.add(newIsoMsgMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(importMenuItem);
		fileMenu.add(exportMenuItem);

		menuBar.add(fileMenu);

		// "Samples" - used to prepare proved examples
		samplesMenu = new JMenu("Samples");
		mcSampleMenuItem = new JMenuItem("MasterCard");
		visaSampleMenuItem = new JMenuItem("VISA");
		tarMDESSampleMenuItem = new JMenuItem("MDES - TAR");
		tcnMDESSampleMenuItem = new JMenuItem("MDES - TCN");
		purchaseMDESSampleMenuItem = new JMenuItem("MDES - Purchase");
		tarVTSSampleMenuItem = new JMenuItem("VTS - TAR");
		tcnVTSSampleMenuItem = new JMenuItem("VTS - TCN");
		purchaseVTSSampleMenuItem = new JMenuItem("VTS - Purchase");

		samplesMenu.add(mcSampleMenuItem);
		samplesMenu.add(visaSampleMenuItem);
		samplesMenu.addSeparator();
		samplesMenu.add(tarMDESSampleMenuItem);
		samplesMenu.add(tcnMDESSampleMenuItem);
		samplesMenu.add(purchaseMDESSampleMenuItem);
		samplesMenu.addSeparator();
		samplesMenu.add(tarVTSSampleMenuItem);
		samplesMenu.add(tcnVTSSampleMenuItem);
		samplesMenu.add(purchaseVTSSampleMenuItem);

		menuBar.add(samplesMenu);

		// "Window" - used to show/hide bitmap panel
		windowMenu = new JMenu("Window");
		bitmapCheckBoxMenuItem = new JCheckBoxMenuItem("Show Bitmap");
		bitmapCheckBoxMenuItem.setSelected(true);
		windowMenu.add(bitmapCheckBoxMenuItem);

		menuBar.add(windowMenu);

		setJMenuBar(menuBar);
	}

	/**
	 * listeners for internal component behaviors
	 */
	private void internalListeners() {
		// hide/show Bitmap panel
		bitmapCheckBoxMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean showBitmapPanel = bitmapCheckBoxMenuItem.isSelected();
				bitmapPanel.setVisible(showBitmapPanel);
			}
		});
	}

}
