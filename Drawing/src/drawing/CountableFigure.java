package drawing;

import java.util.Vector;

public class CountableFigure {
	
	private Vector<Observer> observers = new Vector<>();
	int countableCircle;
	int countableRectangle;
	int groupFigures;
	int result;
	
	public CountableFigure() {
		countableCircle = 0;
		countableRectangle = 0;
		groupFigures = 0;
		result = 0;
		
		notifyObservers();
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	private void notifyObservers() {
		for (Observer obs : observers)
			obs.update(countableCircle, countableRectangle, result, groupFigures);
	}
	
	public void incrementCircle() {
		countableCircle++;
		result++;
		
		notifyObservers();
	}
	
	public void incrementRectangle() {
		countableRectangle++;
		result++;
		
		notifyObservers();
	}
	
	public void incrementGroupFigures() {
		groupFigures++;
		
		notifyObservers();
	}
	
	public void resetGroupFigures() {
		groupFigures = 0;
		notifyObservers();
	}
	
	public void clearCountableFigure() {
		countableCircle = 0;
		countableRectangle = 0;
		result = 0;
		
		notifyObservers();
	}
	
}