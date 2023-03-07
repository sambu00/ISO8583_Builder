package com.isobuilder.control;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTable;
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

	private final String FILE_EXTENSION = "imf";

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
	private void initIsoBuilderFrame() {
		tableModel.loadTableModel(isoMsg.getDeMap());
		mtiPanel.setMtiLabelText(isoMsg.getMessageType());
	}

	/**
	 * Method to update the Bitmap panel according to the data elements included
	 * in the ISO Message
	 */
	private void updateBitmapPanel() {
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
		mtiPanelListeners();

		/*
		 * ISO TABLE PANEL LISTENERS
		 */
		isoTablePanelListeners();

		/*
		 * BITMAP PANEL LISTENERS
		 */
		bitmapPanelListeners();

		/*
		 * DATA ELEMENT PANEL LISTENERS
		 */
		dataElementPanelListeners();

		/*
		 * ACTIONS PANEL LISTENERS
		 */
		actionsPanelListeners();

	}

	/**
	 * Create and set the listeners for the components in the menu
	 */
	private void menuListeners() {
		isoBuilderFrame.setNewIsoMsgMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isoMsg = new IsoMessage();

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setExportMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"ISO Message File (*.imf)", FILE_EXTENSION);
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				int chooserReturnValue = chooser
						.showSaveDialog(isoBuilderFrame);

				if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
					File outputFile = chooser.getSelectedFile();

					if (getExtension(outputFile) != FILE_EXTENSION) {
						String newName = outputFile.toString() + "."
								+ FILE_EXTENSION;
						outputFile = new File(newName);
					}

					try {
						writeISoMsgToFile(outputFile);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(isoBuilderFrame,
								e.getMessage(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		isoBuilderFrame.setImportMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"ISO Message File (*.imf)", FILE_EXTENSION);
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);

				int chooserReturnValue = chooser
						.showOpenDialog(isoBuilderFrame);

				if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
					File importFile = chooser.getSelectedFile();

					try {
						isoMsg = readIsoMsgFromFile(importFile);

					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(isoBuilderFrame,
								e.getMessage(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					initIsoBuilderFrame();
					updateBitmapPanel();
					tableModel.fireTableDataChanged();
				}
			}
		});

		isoBuilderFrame.setMcSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genMastercardExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setVisaSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genVisaExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}

		});

		isoBuilderFrame.setTarMDESSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genMdesTARExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setTcnMDESSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genMdesTCNExample();
				} catch (InvalidMessageTypeException
						| ReservedDataElementException
						| MaximumLengthExceededException | PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setPurchaseMDESSampleMenuItem(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genMdesPurchaseExample();
				} catch (InvalidMessageTypeException
						| ReservedDataElementException
						| MaximumLengthExceededException | PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setTarVTSSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genVtsTARExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setTcnVTSSSampleMenuItemListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genVtsTCNExample();
				} catch (InvalidMessageTypeException
						| ReservedDataElementException
						| MaximumLengthExceededException | PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});

		isoBuilderFrame.setPurchaseVTSSampleMenuItem(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genVtsPurchaseExample();
				} catch (InvalidMessageTypeException
						| ReservedDataElementException
						| MaximumLengthExceededException | PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}
		});
	}

	/**
	 * Create and set the listeners for the components in MTIPanel
	 */
	private void mtiPanelListeners() {
		mtiPanel.setMtiButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					setIsoMessageMTI(mtiPanel.getMtiString());
				} catch (InvalidMessageTypeException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				mtiPanel.setMtiLabelText(isoMsg.getMessageType());
			}

		});

	}

	/**
	 * Create and set the listeners for the components in IsoTablePanel
	 */
	private void isoTablePanelListeners() {
		isoTablePanel.setTableListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);

				if (mouseEvent.getClickCount() == 1 
						&& mouseEvent.getButton() == 1
						&& table.getSelectedRow() != -1) {
					dataElementPanel.resetInputFields();
					String deNumString = (String) table.getModel().getValueAt(
							row, 0);
					String deValueString = (String) table.getModel()
							.getValueAt(row, 1);
					dataElementPanel.setDEFields(deNumString, deValueString);
				}

				if (mouseEvent.getClickCount() == 2 
						&& mouseEvent.getButton() == 1
						&& table.getSelectedRow() != -1) {

					int deNum = Integer.parseInt((String) table.getModel()
							.getValueAt(row, 0));

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

			}
		});
	}

	/**
	 * Create and set the listeners for the components in BitmapPanell
	 */
	private void bitmapPanelListeners() {

	}

	/**
	 * Create and set the listeners for the components in DataElementPanel
	 */
	private void dataElementPanelListeners() {
		dataElementPanel.setAddDEButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int deNum = 0;
				DataElement newDE = null;
				String deValue = dataElementPanel.getDEValue();

				try {
					deNum = dataElementPanel.getDENum();
					newDE = DEFactory.generateDataElement(deNum, deValue);
				} catch (InvalidDataElementException
						| MaximumLengthExceededException | PatternException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							"DataElement missing", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				try {
					addDEtoIsoMessage(deNum, newDE);
				} catch (MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

				String newDeValue = isoMsg.getDataElement(deNum).getPlainValue();

				dataElementPanel.resetInputFields();
				dataElementPanel.setDEFields(String.format("%03d", deNum), newDeValue);

			}

		});

		dataElementPanel.setAppendDEButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int deNum = 0;
				String deValue = null;

				try {
					deNum = dataElementPanel.getDENum();
					deValue = dataElementPanel.getDEValue();

					appendValueToDE(deNum, deValue);
					
					
					

					
				} catch (MaximumLengthExceededException | PatternException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							"DataElement missing", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

				String newDeValue = isoMsg.getDataElement(deNum).getPlainValue();

				dataElementPanel.resetInputFields();
				dataElementPanel.setDEFields(String.format("%03d", deNum), newDeValue);

			}

		});

		dataElementPanel.setDeleteDEButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int deNum = 0;

				try {
					deNum = dataElementPanel.getDENum();
					removeDE(deNum);
				} catch (MaximumLengthExceededException | PatternException
						| InvalidDataElementException
						| FixedLengthNotHonoredException
						| ReservedDataElementException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							"DataElement missing", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

	}

	/**
	 * Create and set the listeners for the components in ActionsPanel
	 */
	private void actionsPanelListeners() {
		actionsPanel.setMcExampleButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genMastercardExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}

		});

		actionsPanel.setVisaExampleButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					isoMsg = IsoMesageExamples.genVisaExample();
				} catch (InvalidMessageTypeException
						| MaximumLengthExceededException | PatternException
						| ReservedDataElementException
						| InvalidDataElementException
						| FixedLengthNotHonoredException | ZeroLengthException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

				}

				initIsoBuilderFrame();
				updateBitmapPanel();
				tableModel.fireTableDataChanged();
			}

		});

		actionsPanel.setKoreButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					// showIsoMsg("IBM037");

					String hexMessageString = isoMsg
							.getIsoExtendedMessage("IBM037");

					int effMessageLength = hexMessageString.length() / 2;

					String effMessageLengthHex = Integer.toHexString(
							effMessageLength).toUpperCase();
					while (effMessageLengthHex.length() < 4) {
						effMessageLengthHex = "0" + effMessageLengthHex;
					}

					String hexHeader = "00000000" + effMessageLengthHex
							+ "0000";
					new KoreFrame(hexHeader + hexMessageString);

				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(isoBuilderFrame,
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

				}

			}

		});
	}

	/**
	 * Method to save the current ISO message in a file
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void writeISoMsgToFile(File file) throws IOException {
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
	private IsoMessage readIsoMsgFromFile(File file) throws IOException,
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
	private String getExtension(File file) {
		String regex = ".*\\.(?<EXT>.*)";
		Pattern pattern = Pattern.compile(regex);

		String extension = "";

		Matcher matcher = pattern.matcher(file.getName());

		if (matcher.find()) {
			extension = matcher.group("EXT");
		}

		return extension;
	}
}
