package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import com.isobuilder.view.DataElementPanel;
import com.isobuilder.exceptions.*;

public class DeleteDEButtonListener implements ActionListener{

    private IsoMessageController controller;

    public DeleteDEButtonListener(IsoMessageController controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent arg0) {
        DataElementPanel dataElementPanel = controller.getDataElementPanel();

        int deNum = 0;

        try {
            deNum = dataElementPanel.getDENum();
            controller.removeDE(deNum);
        } catch (MaximumLengthExceededException | PatternException
                | InvalidDataElementException
                | FixedLengthNotHonoredException
                | ReservedDataElementException | ZeroLengthException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    "DataElement missing", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}
