package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

/**
 * The Class ShapeDroid represents a new shape droid that can collied with
 * others.
 */
public abstract class ShapeDroid extends Shape {

	/** The line collection. */
	private LineCollection lineCollection = new LineCollection();

	/**
	 * Instantiates a new shape droid object.
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
	// Read only pattern
	public ShapeDroid(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor, final IStrategy iStrategy, ShapeSize sSize, int nScore, int nMultiplier, int nChildren) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore, nMultiplier,
				nChildren);

		createLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#update()
	 */
	@Override
	public void update() {
		super.update();

		createLines();
	}

	/**
	 * Creates a new line collection for the bounding box.
	 */
	private void createLines() {
		lineCollection.clear();

		addSides(lineCollection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * main.se450.model.Shape#addSides(main.se450.collections.LineCollection)
	 */
	public abstract void addSides(LineCollection lineCollection);

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) {
		Iterator<IShape> iSides = lineCollection.getLines().iterator();
		while (iSides.hasNext()) {
			iSides.next().draw(graphics);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#getMinX()
	 */
	@Override
	public float getMinX() {
		return lineCollection.getMinX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#getMinY()
	 */
	@Override
	public float getMinY() {
		return lineCollection.getMinY();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#getMaxX()
	 */
	@Override
	public float getMaxX() {
		return lineCollection.getMaxX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#getMaxY()
	 */
	@Override
	public float getMaxY() {
		return lineCollection.getMaxY();
	}
}
