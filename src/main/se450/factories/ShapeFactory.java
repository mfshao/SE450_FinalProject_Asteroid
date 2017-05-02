package main.se450.factories;

import java.awt.Color;

import main.se450.constants.ShapeSize;
import main.se450.constants.ShapeType;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Circle;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;

/**
 * A factory for creating Shape objects with given input parameters.
 */
public class ShapeFactory {

	/**
	 * Instantiates a new shape factory.
	 */
	private ShapeFactory() {
	}

	/**
	 * Make a new shape.
	 *
	 * @param type
	 *            The type of the new shape
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
	 * @return the i shape A created IShape with given input parameters.
	 * @throws BadShapeException
	 *             the bad shape exception
	 * @throws UnsupportedShapeException
	 *             the unsupported shape exception
	 */
	public static IShape makeShape(final String type, float nLeft, float nTop, float nRight, float nBottom, float nX,
			float nY, float nRotation, Color cColor, IStrategy iStrategy, ShapeSize sSize, int nScore, int nMultiplier,
			int nChildren) throws BadShapeException, UnsupportedShapeException {
		IShape iShape = null;

		if (type.equals(ShapeType.CIRCLE.name())) {
			iShape = new Circle(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore,
					nMultiplier, nChildren);
		} else if (type.equals(ShapeType.SQUARE.name())) {
			iShape = new Square(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore,
					nMultiplier, nChildren);
		} else if (type.equals(ShapeType.LINE.name())) {
			throw new UnsupportedShapeException(type);
		} else if (type.equals(ShapeType.TRIANGLE.name())) {
			iShape = new Triangle(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore,
					nMultiplier, nChildren);
		} else if (type.equals(ShapeType.SHIP.name())) {
			iShape = new Ship(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy, sSize, nScore,
					nMultiplier, nChildren);
		} else {
			throw new BadShapeException(type);
		}

		return iShape;
	}

	/**
	 * Randomly move the shape to one border of the screen to make it appears
	 * from the border.
	 *
	 * @param iShape
	 *            The IShape object that will be moved.
	 */
	public static void moveShapeToBorderRandom(IShape iShape) {
		if (Math.random() < 0.5) {
			iShape.translateXY(-iShape.getMaxX(), 0.0f);
		} else {
			iShape.translateXY(0.0f, -iShape.getMaxY());
		}
	}
}
