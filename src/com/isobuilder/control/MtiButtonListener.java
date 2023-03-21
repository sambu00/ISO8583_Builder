package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.isobuilder.view.MTIPanel;
import com.isobuilder.exceptions.InvalidMessageTypeException;

public class MtiButtonListener implements ActionListener{

    private IsoMessageController controller;

    public MtiButtonListener(IsoMessageController controller) {
        this.controller = controller;
    }


    public void actionPerformed(ActionEvent arg0) {
        MTIPanel mtiPanel = controller.getMtiPanel();

        try {
            controller.setIsoMessageMTI(mtiPanel.getMtiString());
        } catch (InvalidMessageTypeException e) {
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        mtiPanel.setMtiLabelText(controller.getIsoMsg().getMessageType());
    }
    
}
