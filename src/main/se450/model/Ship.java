package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IStrategy;

/**
 * The Class Ship represents a ship shape object.
 */
public class Ship extends ShapeDroid {

	/**
	 * Instantiates a new ship shape object.
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
	public Ship(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor,
			IStrategy iStrategy, ShapeSize sSize, int nScore, int nMultiplier, int nChildren) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore, nMultiplier,
				nChildren);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#addSides(main.se450.collections.
	 * LineCollection)
	 */
	public void addSides(LineCollection lineCollection) {
		if (lineCollection != null) {
			lineCollection
					.add(new Line(getX4(), getY4(), getMidpointX1X2(), getMidpointY1Y2(), getX(), getY(), getRotation(),
							getColor(), getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection
					.add(new Line(getMidpointX1X2(), getMidpointY1Y2(), getX3(), getY3(), getX(), getY(), getRotation(),
							getColor(), getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection
					.add(new Line(getX3(), getY3(), getMidpointX1X3(), getMidpointY1Y3(), getX(), getY(), getRotation(),
							getColor(), getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
			lineCollection
					.add(new Line(getMidpointX1X3(), getMidpointY1Y3(), getX4(), getY4(), getX(), getY(), getRotation(),
							getColor(), getStrategy(), getShapeSize(), getScore(), getMultiplier(), getChildren()));
		}
	}

	/**
	 * Get the X-axis midpoint.
	 *
	 * @return The X-axis midpoint.
	 */
	public float getMidpointX1X2() {
		return ((getX1() + getX2()) * 0.5f);
	}

	/**
	 * Gets the Y-axis midpoint.
	 *
	 * @return the Y-axis midpoint.
	 */
	public float getMidpointY1Y2() {
		return ((getY1() + getY2()) * 0.5f);
	}
}
