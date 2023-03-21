package com.isobuilder.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import com.isobuilder.view.KoreFrame;


public class KoreButtonListener implements ActionListener{

    private IsoMessageController controller;

    public KoreButtonListener (IsoMessageController controller) {
        this.controller = controller;
    }


    public void actionPerformed(ActionEvent arg0) {
        try {

            // showIsoMsg("IBM037");

            String hexMessageString = controller.getIsoMsg()
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
            JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
                    e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }
}
