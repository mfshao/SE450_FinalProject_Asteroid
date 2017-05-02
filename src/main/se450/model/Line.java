package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IStrategy;

/**
 * The Class Line represents line shape objects.
 */
public class Line extends Shape
{
	
	/** The line. */
	private Line2D line = new Line2D.Float(0.0f,0.0f,0.0f,0.0f);
	
	/**
	 * Instantiates a new line shape objects.
	 *
	 * @param nLeft
	 *            The minimum X coordinate of the new shape
	 * @param nTop
	 *            The minimum Y coordinate of the new shape
	 * @param nRight
	 *            The maximum X coordinate of the new shape
	 * @param nBottom
	 *            The maximum Y coordinate of the new shape
	 * @param nX
	 *            The speed in X direction of the new shape
	 * @param nY
	 *            The speed in Y direction of the new shape
	 * @param nRotation
	 *            The speed of rotation of the new shape
	 * @param cColor
	 *            The color of the new shape
	 * @param iStrategy
	 *            The border strategy of the new shape
	 * @param sSize
	 *            The ShapeSize of the new shape
	 * @param nScore
	 *            The base score value of the new shape
	 * @param nMultiplier
	 *            The multiplier value of the new shape
	 * @param nChildren
	 *            The number of children of the new shape
	 */
	public Line(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy, ShapeSize sSize, int nScore, int nMultiplier, int nChildren)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore, nMultiplier, nChildren);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.model.Shape#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) 
	{
  		line.setLine(getX1(), getY1(), getX3(), getY3());
		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(line);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.model.Shape#getMinX()
	 */
	@Override
	public float getMinX()
	{
		return Math.min(getX1(), getX3());
	}

	/* (non-Javadoc)
	 * @see main.se450.model.Shape#getMinY()
	 */
	@Override
	public float getMinY()
	{
		return Math.min(getY1(), getY3());
	}
	
	/* (non-Javadoc)
	 * @see main.se450.model.Shape#getMaxX()
	 */
	@Override
	public float getMaxX()
	{
		return Math.max(getX1(), getX3());
	}

	/* (non-Javadoc)
	 * @see main.se450.model.Shape#getMaxY()
	 */
	@Override
	public float getMaxY()
	{
		return Math.max(getY1(), getY3());
	}

	/* (non-Javadoc)
	 * @see main.se450.model.Shape#addSides(main.se450.collections.LineCollection)
	 */
	@Override
	public void addSides(LineCollection lineCollection) {
		if (lineCollection != null)
    	{
	        lineCollection.add(this);
    	}
	}
}
    
      