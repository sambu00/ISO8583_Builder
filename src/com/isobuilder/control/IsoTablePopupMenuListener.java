package com.isobuilder.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.ReservedDataElementException;
import com.isobuilder.exceptions.ZeroLengthException;


public class IsoTablePopupMenuListener implements ActionListener{
    
    private IsoMessageController controller;

    public IsoTablePopupMenuListener(IsoMessageController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JTable table = controller.getIsoTablePanel().getIsoTable();

        if (table.getSelectedRow() != -1) {
            int[] rows = table.getSelectedRows();
            int[] desToRemove =  new int[rows.length];

            for (int i = 0; i < rows.length; i++) {
                desToRemove[i] = Integer.parseInt((String) table.getModel().getValueAt(rows[i], 0)); 
            }

            for (int i = 0; i < desToRemove.length; i++) {
                try {
                    controller.removeDE(desToRemove[i]);
                } catch (ReservedDataElementException | MaximumLengthExceededException | PatternException
                        | InvalidDataElementException | FixedLengthNotHonoredException | ZeroLengthException e) {
                    JOptionPane.showMessageDialog(controller.getIsoBuilderFrame(),
							e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }


        }

    }
    
}
