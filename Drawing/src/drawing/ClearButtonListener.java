package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {

	Drawing drawing;
	CountableFigure countableFigure;
	
	public ClearButtonListener(Drawing drawing, CountableFigure cf){
		this.drawing = drawing;
		this.countableFigure = cf;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		drawing.clear();
		
		String cmd = arg0.getActionCommand();
		
		if (cmd == "Clear")
			countableFigure.clearCountableFigure();
	}

}
