package main.se450.interfaces;

import java.awt.Graphics;
import main.se450.collections.LineCollection;

/**
 * The Interface IDebris to represents debris objects.
 */
public interface IDebris extends IObservable {
	
	/**
	 * Checks if the object is expired with lifetime value equals to or less than 0.
	 *
	 * @return true, if the object is expired
	 */
	boolean isExpired();
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	void update();
	
	/**
	 * Draw the debris object.
	 *
	 * @param g The graphics that the debris will be drawn onto.
	 */
	void draw(Graphics g);
	
	/**
	 * Get the line collection of the debris object.
	 *
	 * @return The line collection the debris object.
	 */
	LineCollection getLineCollection();
	
	/**
	 * Set the speed in X direction for the debris object.
	 *
	 * @param nX The speed in X direction for the debris object.
	 */
	void setX(float nX);

	/**
	 * Sets the speed in Y direction for the debris object.
	 *
	 * @param nY The speed in Y direction for the debris object.
	 */
	void setY(float nY);

	/**
	 * Sets the size of the ship's shield object.
	 *
	 * @param minX The minimum X coordinate of the ship's shield object.
	 * @param minY The minimum Y coordinate of the ship's shield object.
	 */
	void setFrame(float minX, float minY);
}