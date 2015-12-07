package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint implements Observer {

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	private JButton duplicateButton;
	private JButton textButton;
	
	private JMenuBar menu = new JMenuBar();
	
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel statusPanel;
	private JPanel statusText;
	
	private JLabel nbCircles;
	private JLabel nbFigures;
	private JLabel nbRectangles;
	private JTextField nbCirclesText;
	private JTextField nbRectanglesText;
	
	private Drawing drawing;
	private CountableFigure countableFigure = new CountableFigure();
	
	public void run(){
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout());
		statusPanel = new JPanel(new BorderLayout());
		statusText = new JPanel(new BorderLayout());
		
		JMenu settings = new JMenu("Settings");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem text = new JMenuItem("Text");
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawing.textShape("Click on the text to change message");
			}
		});
		
		drawing = new Drawing();
		drawing.setBackground(Color.WHITE);
		
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		duplicateButton = new JButton("Duplicate");
		textButton = new JButton("Text");
		
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(duplicateButton);
		buttonPanel.add(textButton);
		
		nbFigures = new JLabel("Nb Figures = 0", JLabel.CENTER);
		nbCircles = new JLabel("Circle", JLabel.LEFT);
		nbRectangles = new JLabel("Rectangle", JLabel.RIGHT);
		nbCirclesText = new JTextField("0");
		nbCirclesText.setPreferredSize(new Dimension(80, 15));
		nbRectanglesText = new JTextField("0");
		nbRectanglesText.setPreferredSize(new Dimension(80, 15));
		
		statusPanel.add(buttonPanel, BorderLayout.NORTH);
		statusText.add(nbCirclesText, BorderLayout.WEST);
		statusText.add(nbRectanglesText, BorderLayout.EAST);
		statusPanel.add(nbFigures, BorderLayout.CENTER);
		statusPanel.add(nbCircles, BorderLayout.WEST);
		statusPanel.add(nbRectangles, BorderLayout.EAST);
		statusPanel.add(statusText, BorderLayout.SOUTH);
		
		settings.add(undo);
		settings.add(redo);
		settings.add(text);
		settings.setMnemonic('o');
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		text.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		menu.add(settings);
		
		mainPanel.add(menu, BorderLayout.NORTH);
		mainPanel.add(drawing, BorderLayout.CENTER);
		mainPanel.add(statusPanel, BorderLayout.SOUTH);
		
		//listeners pour les boutons
		clearButton.addActionListener(new ClearButtonListener(drawing, countableFigure));
		circleButton.addActionListener(new CircleButtonListener(drawing, countableFigure));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing, countableFigure));
		duplicateButton.addActionListener(new DuplicateButtonListener(drawing, countableFigure));
		textButton.addActionListener(new TextButtonListener(drawing));
		
		//listeners pour la zone de dessin
		DrawingMouseListener l = new DrawingMouseListener(drawing, countableFigure);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(640,480);
		countableFigure.addObserver(this);
		frame.setJMenuBar(menu);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
	}
	
	public void update(int countableFigure1, int countableFigure2, int total, int groupFigure) {
		nbCirclesText.setText(Integer.toString(countableFigure1));
		nbRectanglesText.setText(Integer.toString(countableFigure2));
		nbFigures.setText("Nb Figures = " + total + " and nb groups : = " + groupFigure);
	}
}
