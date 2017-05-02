package main.se450.interfaces;

import java.awt.Color;
import java.awt.Graphics;
import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;

/**
 * The Interface IShape represent shapes.
 */
public interface IShape 
{
	
	/**
	 * Update the shape.
	 */
	void update();
	
	/**
	 * Draw the shape on a given graphics.
	 *
	 * @param g The graphics that the shape will be drawn onto.
	 */
	void draw(Graphics g);
	
	/**
	 * Get the minimum X coordinate.
	 *
	 * @return The minimum X coordinate
	 */
	float getMinX();
	
	/**
	 * Get the minimum Y coordinate.
	 *
	 * @return the minimum Y coordinate
	 */
	float getMinY();
	
	/**
	 * Get the maximum X coordinate.
	 *
	 * @return the maximum X coordinate
	 */
	float getMaxX();
	
	/**
	 * Get the maximum Y coordinate.
	 *
	 * @return the maximum Y coordinate
	 */
	float getMaxY();
	
	/**
	 * Get the shape size.
	 *
	 * @return The shape size
	 */
	ShapeSize getShapeSize();
	
	/**
	 * Get the base score.
	 *
	 * @return The base score
	 */
	int getScore();
	
	/**
	 * Get the multiplier value.
	 *
	 * @return The multiplier value
	 */
	int getMultiplier();
	
	/**
	 * Get the number of children.
	 *
	 * @return The number of children
	 */
	int getChildren();
	
	/**
	 * Update the X and the Y coordinates using the X and Y direction speed.
	 *
	 * @param nX The X direction speed.
	 * @param nY The Y direction speed.
	 */
	void translateXY(float nX, float nY);
	
	/**
	 * Get the line collection of the bounding box.
	 *
	 * @return The line collection
	 */
	LineCollection getLineCollection();

	/**
	 * Get the speed of rotation.
	 *
	 * @return The speed of rotation
	 */
	float getRotation();

	/**
	 * Get the color.
	 *
	 * @return The color
	 */
	Color getColor();

	/**
	 * Get the border strategy.
	 *
	 * @return The border strategy
	 */
	IStrategy getStrategy();

	/**
	 * Get the X axis midpoint.
	 *
	 * @return The X axis midpoint
	 */
	float getMidpointX1X3();

	/**
	 * Get Y axis midpoint.
	 *
	 * @return The Y axis midpoint
	 */
	float getMidpointY1Y3();

	/**
	 * Set the X direction speed.
	 *
	 * @param nX The X direction speed
	 */
	void setX(float nX);

	/**
	 * Set the X direction speed.
	 *
	 * @param nY The Y direction speed
	 */
	void setY(float nY);

	/**
	 * Get the X direction speed.
	 *
	 * @return The X direction speed
	 */
	float getX();
	
	/**
	 * Get the Y direction speed.
	 *
	 * @return The Y direction speed
	 */
	float getY();
}
      