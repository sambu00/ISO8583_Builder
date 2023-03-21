package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.isobuilder.view.DataElementPanel;
import com.isobuilder.exceptions.*;

public class AppendDEButtonListener implements ActionListener{

    private IsoMessageController controller;

    public AppendDEButtonListener(IsoMessageController controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent arg0) {
        DataElementPanel dataElementPanel = controller.getDataElementPanel();

        int deNum = 0;
        String deValue = null;

        try {
            deNum = dataElementPanel.getDENum();
            deValue = dataElementPanel.getDEValue();

            controller.appendValueToDE(deNum, deValue);
            
        } catch (MaximumLengthExceededException | PatternException
                | FixedLengthNotHonoredException | ZeroLengthException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    "DataElement missing", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        String newDeValue = controller.getIsoMsg().getDataElement(deNum).getPlainValue();

        dataElementPanel.resetInputFields();
        dataElementPanel.setDEFields(String.format("%03d", deNum), newDeValue);

    }
    
}
