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
	
	public void setCellDataType(Class type) {
		this.dataType = type;
	}

}
