package com.isobuilder.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.isobuilder.model.IsoTableModel;

/**
 * GUI ISO table Panel.
 * This PAnel shows the list of data element included in the ISO Message.
 * It uses a JTable to show the data present in the IsoTableModel
 * 
 * @author Federico Alaimo
 *
 */
public class IsoTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable isoTable;

	public IsoTablePanel(IsoTableModel tableModel) {
			
		setLayout(new BorderLayout());
		
		
		isoTable = new JTable(tableModel);
		
		TableColumn col0 = isoTable.getColumnModel().getColumn(0);
		col0.setPreferredWidth(10);
		col0.setHeaderValue("DataElement");
		
		TableColumn col1 = isoTable.getColumnModel().getColumn(1);
		col1.setPreferredWidth(280);
		col1.setHeaderValue("Value");
		
		add(new JScrollPane(isoTable), BorderLayout.CENTER);
		
	}
	
	public void setTableListener(MouseListener ml) {
		isoTable.addMouseListener(ml);
	}
	
}
