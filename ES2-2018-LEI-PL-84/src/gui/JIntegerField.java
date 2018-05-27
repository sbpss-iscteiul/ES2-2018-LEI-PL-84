package gui;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JIntegerField extends JTextField {

	private static final long serialVersionUID = 1L;

	@Override
	public void processKeyEvent(KeyEvent e) {
		if(Character.isDigit(e.getKeyChar())) {
			super.processKeyEvent(e);
		}
		e.consume();
		return;
	}
	
	public int getNumber() {
		int result = 0;
        String text = getText();
        if (text != null && !"".equals(text)) {
            result = Integer.valueOf(text);
        }
        return result;
	}
}
