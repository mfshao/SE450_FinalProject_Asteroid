package main.se450.interfaces;

import java.awt.Graphics;
import main.se450.collections.LineCollection;

/**
 * The Interface IShot represent shots.
 */
public interface IShot extends IObservable {
	
	/**
	 * Checks if the shot is expired by its lifetime value.
	 *
	 * @return true, if is expired
	 */
	boolean isExpired();
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	void update();
	
	/**
	 * Draw the shot on a given graphics.
	 *
	 * @param g The graphics that the shape will be drawn onto.
	 */
	void draw(Graphics g);
	
	/**
	 * Gets the line collection of the bounding box.
	 *
	 * @return the line collection
	 */
	LineCollection getLineCollection();
}
