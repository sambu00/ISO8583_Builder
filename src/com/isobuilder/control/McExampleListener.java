package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.isobuilder.backend.IsoMesageExamples;
import com.isobuilder.exceptions.*;


public class McExampleListener implements ActionListener{

    private IsoMessageController controller;

    public McExampleListener(IsoMessageController controller) {
        this.controller = controller;
    }


    public void actionPerformed(ActionEvent arg0) {
        try {
            controller.setIsoMsg(IsoMesageExamples.genMastercardExample());
        } catch (InvalidMessageTypeException
                | MaximumLengthExceededException | PatternException
                | ReservedDataElementException
                | InvalidDataElementException
                | FixedLengthNotHonoredException | ZeroLengthException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

        controller.initIsoBuilderFrame();
        controller.updateBitmapPanel();
        
        controller.getTableModel().fireTableDataChanged();
    }
}

