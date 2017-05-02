package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IStrategy;

/**
 * The Class Circle represents circle shape objects.
 */
public class Circle extends ShapeDroid {

	/** The line. */
	private Line2D line = new Line2D.Float(0.0f, 0.0f, 0.0f, 0.0f);

	/** The circle. */
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);

	/**
	 * Instantiates a new circle shape objects.
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
	public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor, IStrategy iStrategy, ShapeSize sSize, int nScore, int nMultiplier, int nChildren) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore, nMultiplier,
				nChildren);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) {
		line.setLine(getCenterX(), getCenterY(), getX1(), getY1());
		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());

		Graphics2D g2d = (Graphics2D) (graphics);

		g2d.setColor(getColor());
		g2d.draw(circle);
		g2d.draw(line);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#getMinX()
	 */
	@Override
	public float getMinX() {
		return getCenterX() - getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#getMinY()
	 */
	@Override
	public float getMinY() {
		return getCenterY() - getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#getMaxX()
	 */
	@Override
	public float getMaxX() {
		return getCenterX() + getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#getMaxY()
	 */
	@Override
	public float getMaxY() {
		return getCenterY() + getRadius();
	}

	/**
	 * Get the X coordinate of the center of the circle.
	 *
	 * @return The X coordinate of the center of the circle
	 */
	public float getCenterX() {
		return getMidpointX1X3();
	}

	/**
	 * Get the Y coordinate of the center of the circle.
	 *
	 * @return The X coordinate of the center of the circle
	 */
	public float getCenterY() {
		return getMidpointY1Y3();
	}

	/**
	 * Get the radius of the circle.
	 *
	 * @return The radius of the circle
	 */
	public float getRadius() {
		return getWidth() * 0.5f; // getWidth == getHeight for circle
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#addSides(main.se450.collections.
	 * LineCollection)
	 */
	public void addSides(LineCollection lineCollection) {
		if (lineCollection != null) {
			lineCollection.add(new Line(getX1(), getY1(), getX2(), getY2(), getX(), getY(), getRotation(), getColor(),
					getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX2(), getY2(), getX3(), getY3(), getX(), getY(), getRotation(), getColor(),
					getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX3(), getY3(), getX4(), getY4(), getX(), getY(), getRotation(), getColor(),
					getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection.add(new Line(getX4(), getY4(), getX1(), getY1(), getX(), getY(), getRotation(), getColor(),
					getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));

			// lineCollection.add(new Line(getMinX(), getMinY(), getMaxX(),
			// getMinY(), getX(), getY(), getRotation(), getColor(),
			// getStrategy(), getShapeSize(), getScore(), getMultiplier(),
			// getChildren()));
			// lineCollection.add(new Line(getMaxX(), getMinY(), getMaxX(),
			// getMaxY(), getX(), getY(), getRotation(), getColor(),
			// getStrategy(), getShapeSize(), getScore(), getMultiplier(),
			// getChildren()));
			// lineCollection.add(new Line(getMaxX(), getMaxY(), getMinX(),
			// getMaxY(), getX(), getY(), getRotation(), getColor(),
			// getStrategy(), getShapeSize(), getScore(), getMultiplier(),
			// getChildren()));
			// lineCollection.add(new Line(getMinX(), getMaxY(), getMinX(),
			// getMinY(), getX(), getY(), getRotation(), getColor(),
			// getStrategy(), getShapeSize(), getScore(), getMultiplier(),
			// getChildren()));
		}
	}
}
