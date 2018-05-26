package gui;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	private Class dataType;
	private boolean intervalCelldEditable;
	private boolean bitsCelldEditable;
	
	public MyTableModel(Object[][] rowNames, Object columnNames[]) {
		super(rowNames, columnNames);
		intervalCelldEditable = true;
		bitsCelldEditable = true;
	}	

	@Override
	public Class getColumnClass(int col) {
		if(super.getValueAt(0, 0)!=null)
			switch (col) {
				case 0: return String.class;
				case 1: return String.class;
				case 2: return dataType; 
				case 3: return dataType;
				case 4: return Integer.class;
				default: return String.class;
			}
		else return String.class;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		switch(column) {
		case 2: return intervalCelldEditable;
		case 3: return intervalCelldEditable;
		case 4: return bitsCelldEditable;
		default: return true;
		}
	}
	
	public Class getDataType() {
		return dataType;
	}
	
	public void setCellDataType(Class type) {
		this.dataType = type;
	}

	public void setIntervalCellEditable(boolean e) {
		this.intervalCelldEditable = e;
	}
	public boolean isIntervalCellEditable() {
		return intervalCelldEditable;
	}
	
	public void setBitsCellEditable(boolean e) {
		this.bitsCelldEditable = e;
	}
	public boolean isBitsCellEditable() {
		return bitsCelldEditable;
	}
	
}
