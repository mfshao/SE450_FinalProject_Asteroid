package main.se450.interfaces;

import java.awt.Dimension;

import main.se450.model.Shape;

/**
 * The Interface IStrategy represents border strategies.
 */
public interface IStrategy 
{
	
	/**
	 * Execute the strategic move for a shape at the border.
	 *
	 * @param shape A shape at the border
	 */
	void execute(Shape shape);
	
	/**
	 * Update dimension of current screen area.
	 *
	 * @param newDimension The new dimension of current screen area
	 */
	void updateDimension(Dimension newDimension);
}
      