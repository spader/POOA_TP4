package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 1L;
	
	ArrayList<Shape> shapes;
	ArrayList<Shape> duplicateShapes;
	
	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
		duplicateShapes = new ArrayList<Shape>();
	}
	
	/**
	 * Impl�mentation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}
	
	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s){
		shapes.add(s);
		this.repaint();
	}
	
	/** 
	 * Red�finition de la m�thode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Shape s : shapes)
			s.paint(g);
		
		for (Shape s : duplicateShapes)
			s.paint(g);
	}
	
	public void duplicate() {
		if (shapes.size() != 0) {
			for (Shape s : shapes) {
				Shape newShape = s.duplicateFigure();
				Point point = new Point(s.origin());
				point.y += 120;
				newShape.setOrigin(point);
				duplicateShapes.add(newShape);
				System.out.println("Shape is duplicated");
			}
			
			this.repaint();
		}
	}
	
	public void textShape(String name) {
		if (shapes.size() != 0) {
			for (Shape s : shapes)
				s.setText(name);
		
			this.repaint();
		}
		
		if (duplicateShapes.size() != 0) {
			for (Shape s : duplicateShapes)
				s.setText(name);
		
			this.repaint();
		}
	}
	
	/**
	 * Enl�ve toutes les formes et redessine
	 */
	public void clear(){
		shapes.clear();
		duplicateShapes.clear();
		this.repaint();
	}
	
	
}
