package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuplicateButtonListener implements ActionListener {
	Drawing drawing;
	CountableFigure countableFigure;
	Shape shape;
	
	public DuplicateButtonListener(Drawing drawing, CountableFigure cf) {
		this.drawing = drawing;
		this.countableFigure = cf;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		drawing.duplicate();
		
		String cmd = arg0.getActionCommand();
		
		if (cmd == "Duplicate") {
			countableFigure.incrementRectangle();
			countableFigure.incrementCircle();
		}
	}
}
