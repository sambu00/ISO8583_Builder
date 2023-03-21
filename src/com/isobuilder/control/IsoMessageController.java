package com.isobuilder.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.isobuilder.backend.IsoMesageExamples;
import com.isobuilder.backend.IsoMessage;
import com.isobuilder.backend.dataelement.DEFactory;
import com.isobuilder.backend.dataelement.DataElement;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.InvalidMessageTypeException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ReservedDataElementException;
import com.isobuilder.exceptions.ZeroLengthException;
import com.isobuilder.model.IsoTableModel;
import com.isobuilder.view.ActionsPanel;
import com.isobuilder.view.BitmapPanel;
import com.isobuilder.view.DataElementFrame;
import com.isobuilder.view.DataElementPanel;
import com.isobuilder.view.IsoBuilderFrame;
import com.isobuilder.view.IsoTablePanel;
import com.isobuilder.view.KoreFrame;
import com.isobuilder.view.MTIPanel;

/**
 * Controller Class to support GUI functions
 * 
 * @author Federico Alaimo
 *
 */
public class IsoMessageController {

	// model
	private IsoMessage isoMsg;
	private IsoTableModel tableModel;

	// view
	private IsoBuilderFrame isoBuilderFrame;
	private BitmapPanel bitmapPanel;
	private DataElementPanel dataElementPanel;
	private MTIPanel mtiPanel;
	private IsoTablePanel isoTablePanel;
	private ActionsPanel actionsPanel;

	/**
	 * Class Constructor
	 */
	public IsoMessageController() {
		// model
		isoMsg = new IsoMessage();
		tableModel = new IsoTableModel();

		// view
		bitmapPanel = new BitmapPanel();
		dataElementPanel = new DataElementPanel();
		mtiPanel = new MTIPanel();
		isoTablePanel = new IsoTablePanel(tableModel);
		actionsPanel = new ActionsPanel();

		initIsoBuilderFrame();

	}

	/**
	 * Method to start the application principal frame
	 */
	public void startApp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					isoBuilderFrame = new IsoBuilderFrame(bitmapPanel,
							dataElementPanel, mtiPanel, isoTablePanel,
							actionsPanel);
					// isoBuilderFrame.setTableModel(tableModel);
					setListeners();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
	}

	/**
	 * Method to set the message type
	 * 
	 * @param mti
	 * @throws InvalidMessageTypeException
	 */
	public void setIsoMessageMTI(String mti) throws InvalidMessageTypeException {
		isoMsg.setMessageType(mti);

	}

	/**
	 * MEthod to add a data element the the ISO message
	 * 
	 * @param position
	 * @param de
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public void addDEtoIsoMessage(int position, DataElement de)
			throws MaximumLengthExceededException, PatternException,
			ReservedDataElementException, InvalidDataElementException,
			FixedLengthNotHonoredException, ZeroLengthException {
		isoMsg.addDataElement(position, de);
		tableModel.fireTableDataChanged();

		updateBitmapPanel();

	}

	/**
	 * Method to append a value to a data element
	 * 
	 * @param position
	 * @param value
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public void appendValueToDE(int position, String value)
			throws MaximumLengthExceededException, PatternException,
			FixedLengthNotHonoredException, ZeroLengthException {
		DataElement de = isoMsg.getDataElement(position);
		String newValue = de.getPlainValue() + value;
		de.setValue(newValue);
		tableModel.fireTableDataChanged();

	}

	/**
	 * Method to remove a data element from the ISO Message
	 * 
	 * @param position
	 * @throws ReservedDataElementException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public void removeDE(int position) throws ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		isoMsg.removeDataElement(position);
		tableModel.fireTableDataChanged();

		updateBitmapPanel();

	}

	/**
	 * Method to show the whole ISO Message as a String
	 * 
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 */
	public void showIsoMsg() throws MaximumLengthExceededException,
			PatternException {
		System.out.println(isoMsg.getIsoExtendedMessage());
	}

	/**
	 * Method to show the whole ISO Message as a String encoded in the provided
	 * charset
	 * 
	 * @param charsetName
	 * @throws UnsupportedEncodingException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 */
	public void showIsoMsg(String charsetName)
			throws UnsupportedEncodingException,
			MaximumLengthExceededException, PatternException {
		String s037 = isoMsg.getIsoExtendedMessage(charsetName);
		System.out.println(s037);

	}

	/**
	 * table mode getter
	 * 
	 * @return table model
	 */
	public IsoTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Method to initialize the principal frame
	 */
	protected void initIsoBuilderFrame() {
		tableModel.loadTableModel(isoMsg.getDeMap());
		mtiPanel.setMtiLabelText(isoMsg.getMessageType());
	}

	/**
	 * Method to update the Bitmap panel according to the data elements included
	 * in the ISO Message
	 */
	protected void updateBitmapPanel() {
		String bits1 = isoMsg.getBitmap1().toString();
		String bits2 = isoMsg.getBitmap2().toString();

		String bitmapsBits = bits1 + bits2;
		JLabel[] bitmapLabels = bitmapPanel.getBitmapLabels();

		for (int i = 0; i < bitmapLabels.length; i++) {
			if (bitmapsBits.charAt(i) == '1') {
				bitmapLabels[i].setBackground(Color.GREEN);
			} else {
				bitmapLabels[i].setBackground(Color.RED);
			}
		}

	}

	// LISTENERS
	/**
	 * Method used to set the listeners of the components
	 */
	private void setListeners() {
		/*
		 * MENU LISTENERS
		 */
		menuListeners();

		/*
		 * MTI PANEL LISTENERS
		 */
		mtiPanel.setMtiButtonListener(new MtiButtonListener(this));

		/*
		 * ISO TABLE PANEL LISTENERS
		 */
		isoTablePanel.setTableListener(new IsoTableMouseAdapter(this));
		isoTablePanel.setRemoveSelectedListener(new IsoTablePopupMenuListener(this));


		/*
		 * BITMAP PANEL LISTENERS
		 */
		// bitmapPanel.setListener(new RFU(this)) ... RFU


		/*
		 * DATA ELEMENT PANEL LISTENERS
		 */
		dataElementPanel.setAddDEButtonListener(new AddDEButtonListener(this));
		dataElementPanel.setAppendDEButtonListener(new AppendDEButtonListener(this));
		dataElementPanel.setDeleteDEButtonListener(new DeleteDEButtonListener(this));


		/*
		 * ACTIONS PANEL LISTENERS
		 */
		actionsPanel.setMcExampleButtonListener(new McExampleListener(this));
		actionsPanel.setVisaExampleButtonListener(new VisaExampleListener(this));
		actionsPanel.setKoreButtonListener(new KoreButtonListener(this));

	}

	/**
	 * Create and set the listeners for the components in the menu
	 * @throws ZeroLengthException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws ReservedDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws InvalidMessageTypeException
	 */
	private void menuListeners() {
		isoBuilderFrame.setNewIsoMsgMenuItemListener(MenuListeners.newIsoMsg(this));
		isoBuilderFrame.setExportMenuItemListener(MenuListeners.exportMsg(this));
		isoBuilderFrame.setImportMenuItemListener(MenuListeners.importMsg(this));
		
		try {
			isoBuilderFrame.setMcSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genMastercardExample()));
			isoBuilderFrame.setVisaSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genVisaExample()));

			isoBuilderFrame.setTarMDESSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genMdesTARExample()));
			isoBuilderFrame.setTcnMDESSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genMdesTCNExample()));
			isoBuilderFrame.setPurchaseMDESSampleMenuItem(MenuListeners.msgSample(this, IsoMesageExamples.genMdesPurchaseExample()));
	
			isoBuilderFrame.setTarVTSSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genVtsTARExample()));
			isoBuilderFrame.setTcnVTSSSampleMenuItemListener(MenuListeners.msgSample(this, IsoMesageExamples.genVtsTCNExample()));
			isoBuilderFrame.setPurchaseVTSSampleMenuItem(MenuListeners.msgSample(this, IsoMesageExamples.genVtsPurchaseExample()));
	
		} catch (InvalidMessageTypeException | MaximumLengthExceededException | PatternException
				| ReservedDataElementException | InvalidDataElementException | FixedLengthNotHonoredException
				| ZeroLengthException e) {
			JOptionPane.showMessageDialog(isoBuilderFrame,
			e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Method to save the current ISO message in a file
	 * 
	 * @param file
	 * @throws IOException
	 */
	protected void writeISoMsgToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(isoMsg);

		oos.close();
		fos.close();
	}

	/**
	 * Method to load an ISO Message from a file
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected IsoMessage readIsoMsgFromFile(File file) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		IsoMessage isoMsg = (IsoMessage) ois.readObject();

		ois.close();
		fis.close();

		return isoMsg;
	}

	/**
	 * Method to retrieve the extension of a file
	 * 
	 * @param file
	 * @return
	 */
	protected String getExtension(File file) {
		String regex = ".*\\.(?<EXT>.*)";
		Pattern pattern = Pattern.compile(regex);

		String extension = "";

		Matcher matcher = pattern.matcher(file.getName());

		if (matcher.find()) {
			extension = matcher.group("EXT");
		}

		return extension;
	}

	protected void openDataElementFrame(int deNum) {
		DataElement selectedDE = isoMsg.getDataElement(deNum);
		// new DataElementControl(isoMsg, selectedDE);
		DataElementFrame def = new DataElementFrame(selectedDE);

		def.setRemoveListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					removeDE(selectedDE.getPosition());
				} catch (ReservedDataElementException
						| MaximumLengthExceededException
						| PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException
						| ZeroLengthException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		});

		def.setUpdateValueListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String deValue = def.getDEValue();
				int dePosition = selectedDE.getPosition();

				try {
					DataElement newDE = DEFactory
							.generateDataElement(dePosition,
									deValue);
					addDEtoIsoMessage(dePosition, newDE);
				} catch (InvalidDataElementException
						| MaximumLengthExceededException
						| PatternException
						| FixedLengthNotHonoredException
						| ReservedDataElementException
						| ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

	}

	public BitmapPanel getBitmapPanel() {
		return bitmapPanel;
	}

	public DataElementPanel getDataElementPanel() {
		return dataElementPanel;
	}

	public MTIPanel getMtiPanel() {
		return mtiPanel;
	}

	public IsoTablePanel getIsoTablePanel() {
		return isoTablePanel;
	}

	public ActionsPanel getActionsPanel() {
		return actionsPanel;
	}

	public IsoBuilderFrame getIsoBuilderFrame() {
		return isoBuilderFrame;
	}

	public IsoMessage getIsoMsg() {
		return isoMsg;
	}

	public void setIsoMsg(IsoMessage isoMsg) {
		this.isoMsg = isoMsg;
	}

}
