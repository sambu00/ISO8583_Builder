package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.isobuilder.backend.IsoMesageExamples;
import com.isobuilder.backend.IsoMessage;
import com.isobuilder.exceptions.*;

public final class MenuListeners {

    private static final String FILE_EXTENSION = "imf";


    public static ActionListener newIsoMsg(IsoMessageController controller) {
        ActionListener al =  new ActionListener() {
      
            public void actionPerformed(ActionEvent arg0) {
                controller.setIsoMsg(new IsoMessage());

				controller.initIsoBuilderFrame();
				controller.updateBitmapPanel();
				controller.getTableModel().fireTableDataChanged();

            }

        };

        return al;
    }

    public static ActionListener exportMsg(IsoMessageController controller) {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "ISO Message File (*.imf)", FILE_EXTENSION);
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int chooserReturnValue = chooser
                        .showSaveDialog(controller.getIsoBuilderFrame());

                if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
                    File outputFile = chooser.getSelectedFile();

                    if (controller.getExtension(outputFile) != FILE_EXTENSION) {
                        String newName = outputFile.toString() + "."
                                + FILE_EXTENSION;
                        outputFile = new File(newName);
                    }

                    try {
                        controller.writeISoMsgToFile(outputFile);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                                e.getMessage(), "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        return al;
    }

    public static ActionListener importMsg(IsoMessageController controller) {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"ISO Message File (*.imf)", FILE_EXTENSION);
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);

				int chooserReturnValue = chooser
						.showOpenDialog(controller.getIsoBuilderFrame());

				if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {
					File importFile = chooser.getSelectedFile();

					try {
						controller.setIsoMsg(controller.readIsoMsgFromFile(importFile));

					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
								e.getMessage(), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					controller.initIsoBuilderFrame();
					controller.updateBitmapPanel();
					controller.getTableModel().fireTableDataChanged();
				}
			}
        };

        return al;
    }

    public static ActionListener msgSample(IsoMessageController controller, IsoMessage isoMsg) {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controller.setIsoMsg(isoMsg);

				controller.initIsoBuilderFrame();
				controller.updateBitmapPanel();
				controller.getTableModel().fireTableDataChanged();
			}
        };
        return al;
    }

}