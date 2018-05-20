package gui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CheckBoxItem {

	private String name;
	private boolean isSelected = false;
	
	public CheckBoxItem(String name) {
		this.name = name;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public String getName() {
		return name;
	}
}

class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer<CheckBoxItem> {

	@Override
	public Component getListCellRendererComponent(JList<? extends CheckBoxItem> list, CheckBoxItem value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setEnabled(list.isEnabled());
		setSelected(value.isSelected());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setText(value.getName());
		return this;
	}
	
}
