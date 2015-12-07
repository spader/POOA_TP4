package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/**
 * Listener pour g�rer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {

	Drawing drawing;
	Shape currentShape = null;
	CountableFigure countableFigure;
	
	Vector<Shape> listFigures = new Vector<Shape>();
	
	public DrawingMouseListener(Drawing d, CountableFigure cf) {
		this.drawing = d;
		this.countableFigure = cf;
	}
	
	/**
	 * Bouge la forme s�lectionn�e (si une forme est s�lectionn�e)
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentShape != null){
			currentShape.setOrigin(e.getPoint());
			drawing.repaint();
		}
		
		if (listFigures.size() != 0) {
			for (Shape s : listFigures) {
				s.setOrigin(e.getPoint());
				drawing.repaint();
			}
		}
	}
	
	/**
	 * S�lectionne la forme sur laquelle l'utilisateur a cliqu�
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape s : drawing){
			if(s.isOn(e.getPoint())){
				currentShape = s;
				break;
			}
		}
	}

	/**
	 * D�s�lectionne la forme
	 */
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 3) {
			currentShape = null;
			
			if (listFigures.size() != 0) {
				listFigures.clear();
				System.out.println("Object is now not selected");
				countableFigure.resetGroupFigures();
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			currentShape = null;
			
			for (Shape s : drawing) {
				if (s.isOn(e.getPoint())) {
					listFigures.add(s);
					System.out.println("Nb selected object : " + listFigures.size());
					countableFigure.incrementGroupFigures();
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
