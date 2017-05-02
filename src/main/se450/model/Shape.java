package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;

/**
 * The Class Shape represents shape objects in an abstract way.
 */
public abstract class Shape implements IShape {

	/** The x 1. */
	private float x1 = 0.0f;

	/** The y 1. */
	private float y1 = 0.0f;

	/** The x 2. */
	private float x2 = 0.0f;

	/** The y 2. */
	private float y2 = 0.0f;

	/** The x 3. */
	private float x3 = 0.0f;

	/** The y 3. */
	private float y3 = 0.0f;

	/** The x 4. */
	private float x4 = 0.0f;

	/** The y 4. */
	private float y4 = 0.0f;

	/** The x. */
	private float x = 0.0f;

	/** The y. */
	private float y = 0.0f;

	/** The rotation. */
	private float rotation = 0.0f;

	/** The color. */
	private Color color = null;

	/** The i strategy. */
	private IStrategy iStrategy = null;

	/** The size. */
	private ShapeSize size = null;

	/** The score. */
	private int score = 0;

	/** The multiplier. */
	private int multiplier = 0;

	/** The children. */
	private int children = 0;

	/** The line collection. */
	private LineCollection lineCollection = new LineCollection();

	/**
	 * Instantiates a new shape.
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
	 * @param iiStrategy
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
	public Shape(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor, final IStrategy iiStrategy, ShapeSize sSize, int nScore, int nMultiplier, int nChildren) {
		x1 = nLeft;
		y1 = nTop;
		x2 = nRight;
		y2 = nTop;
		x3 = nRight;
		y3 = nBottom;
		x4 = nLeft;
		y4 = nBottom;
		x = nX;
		y = nY;
		rotation = nRotation;
		color = cColor;
		iStrategy = iiStrategy;
		size = sSize;
		score = nScore;
		multiplier = nMultiplier;
		children = nChildren;

		createLines();
	}

	/**
	 * Creates a new line collection for the bounding box.
	 */
	private void createLines() {
		lineCollection.clear();

		addSides(lineCollection);
	}

	/**
	 * Adds the sides into line collection.
	 *
	 * @param lineCollection
	 *            The line collection of new sides to be added.
	 */
	public abstract void addSides(LineCollection lineCollection);

	/**
	 * Get the upper-left X coordinate.
	 *
	 * @return The upper-left X coordinate
	 */
	public float getX1() {
		return x1;
	}

	/**
	 * Get the upper-left Y coordinate.
	 *
	 * @return the upper-left Y coordinate.
	 */
	public float getY1() {
		return y1;
	}

	/**
	 * Get the upper-right X coordinate.
	 *
	 * @return The upper-right X coordinate.
	 */
	public float getX2() {
		return x2;
	}

	/**
	 * Get the upper-right Y coordinate.
	 *
	 * @return The upper-right Y coordinate.
	 */
	public float getY2() {
		return y2;
	}

	/**
	 * Get the lower-right X coordinate.
	 *
	 * @return The lower-right X coordinate.
	 */
	public float getX3() {
		return x3;
	}

	/**
	 * Get the lower-right Y coordinate.
	 *
	 * @return The lower-right Y coordinate.
	 */
	public float getY3() {
		return y3;
	}

	/**
	 * Get the lower-left X coordinate.
	 *
	 * @return The lower-left X coordinate.
	 */
	public float getX4() {
		return x4;
	}

	/**
	 * Get the lower-left Y coordinate.
	 *
	 * @return The lower-left Y coordinate.
	 */
	public float getY4() {
		return y4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getX()
	 */
	public float getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getY()
	 */
	public float getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getRotation()
	 */
	@Override
	public float getRotation() {
		return rotation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getStrategy()
	 */
	@Override
	public final IStrategy getStrategy() {
		return iStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getShapeSize()
	 */
	@Override
	public ShapeSize getShapeSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getScore()
	 */
	@Override
	public int getScore() {
		return score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMultiplier()
	 */
	@Override
	public int getMultiplier() {
		return multiplier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getChildren()
	 */
	@Override
	public int getChildren() {
		return children;
	}

	/**
	 * Get the width of the shape.
	 *
	 * @return the width of the shape.
	 */
	public float getWidth() {
		return (float) (Math.sqrt(Math.pow(x3 - x1, 2.0) + Math.pow(y3 - y1, 2.0)));
	}

	/**
	 * Get the height of the shape..
	 *
	 * @return the height of the shape.
	 */
	public float getHeight() {
		return (float) (Math.sqrt(Math.pow(x4 - x2, 2.0) + Math.pow(y4 - y2, 2.0)));
	}

	/**
	 * Set the upper-left X coordinate.
	 *
	 * @param nX1
	 *            The new upper-left X coordinate.
	 */
	public void setX1(float nX1) {
		x1 = nX1;
	}

	/**
	 * Set the upper-left Y coordinate.
	 *
	 * @param nY1
	 *            The new upper-left Y coordinate.
	 */
	public void setY1(float nY1) {
		y1 = nY1;
	}

	/**
	 * Set the upper-right X coordinate.
	 *
	 * @param nX2
	 *            The new upper-right X coordinate.
	 */
	public void setX2(float nX2) {
		x2 = nX2;
	}

	/**
	 * Set the upper-right Y coordinate.
	 *
	 * @param nY2
	 *            The new upper-right Y coordinate.
	 */
	public void setY2(float nY2) {
		y2 = nY2;
	}

	/**
	 * Set the lower-right X coordinate.
	 *
	 * @param nX3
	 *            The new lower-right X coordinate.
	 */
	public void setX3(float nX3) {
		x3 = nX3;
	}

	/**
	 * Set the lower-right Y coordinate.
	 *
	 * @param nY3
	 *            The new lower-right Y coordinate.
	 */
	public void setY3(float nY3) {
		y3 = nY3;
	}

	/**
	 * Set the lower-left X coordinate.
	 *
	 * @param nX4
	 *            The new lower-left X coordinate.
	 */
	public void setX4(float nX4) {
		x4 = nX4;
	}

	/**
	 * Set the lower-left Y coordinate.
	 *
	 * @param nY4
	 *            The new lower-left Y coordinate.
	 */
	public void setY4(float nY4) {
		y4 = nY4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#setX(float)
	 */
	@Override
	public void setX(float nX) {
		x = nX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#setY(float)
	 */
	@Override
	public void setY(float nY) {
		y = nY;
	}

	/**
	 * Set the speed of rotation.
	 *
	 * @param nRotation
	 *            The new speed of rotation
	 */
	public void setRotation(float nRotation) {
		rotation = nRotation;
	}

	/**
	 * Set the color.
	 *
	 * @param cColor
	 *            The new color
	 */
	public void setColor(Color cColor) {
		color = cColor;
	}

	/**
	 * Set the border strategy.
	 *
	 * @param iiStrategy
	 *            The new border strategy
	 */
	public void setStrategy(final IStrategy iiStrategy) {
		iStrategy = iiStrategy;
	}

	/**
	 * Set the shape size.
	 *
	 * @param size
	 *            The new shape size
	 */
	public void setSize(ShapeSize size) {
		this.size = size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#update()
	 */
	@Override
	public void update() {
		transform();

		iStrategy.execute(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getLineCollection()
	 */
	@Override
	public LineCollection getLineCollection() {
		return lineCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#draw(java.awt.Graphics)
	 */
	@Override
	public abstract void draw(Graphics g);

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMinX()
	 */
	@Override
	abstract public float getMinX();

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMinY()
	 */
	@Override
	abstract public float getMinY();

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMaxX()
	 */
	@Override
	abstract public float getMaxX();

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMaxY()
	 */
	@Override
	abstract public float getMaxY();

	/**
	 * Move shape object in X direction.
	 *
	 * @param nX
	 *            The X-axis speed.
	 */
	public void translateX(float nX) {
		x1 += nX;
		x2 += nX;
		x3 += nX;
		x4 += nX;
	}

	/**
	 * Move shape object in Y direction.
	 *
	 * @param nY
	 *            The Y-axis speed.
	 */
	public void translateY(float nY) {
		y1 += nY;
		y2 += nY;
		y3 += nY;
		y4 += nY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#translateXY(float, float)
	 */
	@Override
	public void translateXY(float nX, float nY) {
		translateX(nX);
		translateY(nY);
	}

	/**
	 * Move the whole shape object.
	 */
	private void transform() {
		// translate first
		translateXY(x, y);

		float midX = getMidpointX1X3();
		float midY = getMidpointY1Y3();

		translateXY(-midX, -midY);

		// then rotate
		float radians = (float) Math.toRadians(rotation);
		float sinR = (float) Math.sin(radians);
		float cosR = (float) Math.cos(radians);

		float xPrime1 = (float) ((x1 * cosR) - (y1 * sinR));
		float yPrime1 = (float) ((y1 * cosR) + (x1 * sinR));

		float xPrime2 = (float) ((x2 * cosR) - (y2 * sinR));
		float yPrime2 = (float) ((y2 * cosR) + (x2 * sinR));

		float xPrime3 = (float) ((x3 * cosR) - (y3 * sinR));
		float yPrime3 = (float) ((y3 * cosR) + (x3 * sinR));

		float xPrime4 = (float) ((x4 * cosR) - (y4 * sinR));
		float yPrime4 = (float) ((y4 * cosR) + (x4 * sinR));

		x1 = midX + xPrime1;
		x2 = midX + xPrime2;
		x3 = midX + xPrime3;
		x4 = midX + xPrime4;
		y1 = midY + yPrime1;
		y2 = midY + yPrime2;
		y3 = midY + yPrime3;
		y4 = midY + yPrime4;

		createLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMidpointX1X3()
	 */
	@Override
	public float getMidpointX1X3() {
		return ((x1 + x3) * 0.5f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IShape#getMidpointY1Y3()
	 */
	@Override
	public float getMidpointY1Y3() {
		return ((y1 + y3) * 0.5f);
	}
}
