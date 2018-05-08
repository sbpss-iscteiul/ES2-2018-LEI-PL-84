package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{
	
	private Class dataType;
	
	public MyTableModel(Object[][] rowNames, Object columnNames[]) {
		super(rowNames, columnNames);
	}
	
	@Override
	public Class getColumnClass(int col) {
		if(super.getValueAt(0, 0)!=null)
			switch (col) {
				case 0: return String.class;
				case 1: return String.class;
				case 2: return dataType; 
				case 3: return dataType;
				default: return String.class;
			}
		else return String.class;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 2: return false;
		case 3: return false;
		}
		return true;
	}
	
	public void setCellDataType(Class type, JFrame frame) {
		this.dataType = type;
	}
	
	public void setDataType(Class dataType) {
		this.dataType=dataType;
	}

}
