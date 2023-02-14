package com.isobuilder.model;

import java.util.TreeMap;

import javax.swing.table.AbstractTableModel;

import com.isobuilder.backend.dataelement.DataElement;

/**
 * Table Model to map IsoMessage deMap in the JTable 
 * 
 * @author Federico Alaimo
 *
 */
public class IsoTableModel extends AbstractTableModel {

	private TreeMap<Integer, DataElement> deMap;
	
	private static final long serialVersionUID = 1L;

	public IsoTableModel() {

	}

	@Override
	public int getColumnCount() {
		// always 2 columns: data element number and data element value
		return 2;
	}

	@Override
	public int getRowCount() {
		// each row in the map is a data element from deMap
		return deMap.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// get Set of keys (data element that are included in deMap ordered by positions)
		Integer[] mapKeys = deMap.keySet().toArray(new Integer[0]);
		
		// get the data element from the selected row
		DataElement selectedDataElement = deMap.get(mapKeys[row]);

		// column 0: provide data element position
		// column 1: provide data element value
		if (col == 0) {
			return String.format("%03d", selectedDataElement.getPosition());
		} else {
			return selectedDataElement.getPlainValue();
		}

	}

	/**
	 * Method used to load the data in the table model
	 * 
	 * @param deMap
	 */
	public void loadTableModel(TreeMap<Integer, DataElement> deMap) {
		this.deMap = deMap;

	}

}
