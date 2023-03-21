package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.isobuilder.backend.dataelement.DEFactory;
import com.isobuilder.backend.dataelement.DataElement;
import com.isobuilder.view.DataElementPanel;

import com.isobuilder.exceptions.*;

public class AddDEButtonListener implements ActionListener{

    private IsoMessageController controller;

    public AddDEButtonListener(IsoMessageController controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent arg0) {
        DataElementPanel dataElementPanel = controller.getDataElementPanel();

        int deNum = 0;
        DataElement newDE = null;
        String deValue = dataElementPanel.getDEValue();

        try {
            deNum = dataElementPanel.getDENum();
            newDE = DEFactory.generateDataElement(deNum, deValue);
        } catch (InvalidDataElementException
                | MaximumLengthExceededException | PatternException
                | FixedLengthNotHonoredException | ZeroLengthException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    "DataElement missing", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        try {
            controller.addDEtoIsoMessage(deNum, newDE);
        } catch (MaximumLengthExceededException | PatternException
                | ReservedDataElementException
                | InvalidDataElementException
                | FixedLengthNotHonoredException | ZeroLengthException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        String newDeValue = controller.getIsoMsg().getDataElement(deNum).getPlainValue();

        dataElementPanel.resetInputFields();
        dataElementPanel.setDEFields(String.format("%03d", deNum), newDeValue);
    }

}
