package com.isobuilder.control;

import java.awt.event.MouseAdapter;
import java.awt.Point;
import javax.swing.JTable;

import java.awt.event.MouseEvent;

public class IsoTableMouseAdapter extends MouseAdapter {

    private IsoMessageController controller;

    public IsoTableMouseAdapter(IsoMessageController controller) {
        this.controller = controller;
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();
		Point point = mouseEvent.getPoint();

        // left click
        if (mouseEvent.getButton() == 1 && table.getSelectedRow() != -1) {
            switch (mouseEvent.getClickCount()) {
                case 1:
                    setDataElementPanel(table, point);
                    break;
                case 2:
                    openDataElementFrame(table, point);
                    break;
                default:
                    break;
            }
            
        }
    }

    private void setDataElementPanel(JTable table, Point point) {
        int row = table.rowAtPoint(point);

        controller.getDataElementPanel().resetInputFields();
        String deNumString = (String) table.getModel().getValueAt(row, 0);
        String deValueString = (String) table.getModel().getValueAt(row, 1);
        controller.getDataElementPanel().setDEFields(deNumString, deValueString);
    }

    private void openDataElementFrame(JTable table, Point point) {
        int row = table.rowAtPoint(point);
        int deNum = Integer.parseInt((String) table.getModel().getValueAt(row, 0));

        controller.openDataElementFrame(deNum);

    }

}
