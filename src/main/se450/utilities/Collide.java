package main.se450.utilities;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IShape;

/**
 * The Class Collide is a helper (utility) to help to determine if two objects
 * (IShapes, IShots or PlayerShip) has collided bu using there bounding box
 * lines.
 */
public class Collide {

	/** The Constant EPSILON. */
	public static final float EPSILON = 0.000001f;

	/**
	 * Collided check if the owners of two line collections has collided.
	 *
	 * @param collectionA
	 *            The line collection of the first object.
	 * @param collectionB
	 *            The line collection of the second object.
	 * @return true, if the two objects has collided.
	 */
	public synchronized static boolean collided(final LineCollection collectionA, final LineCollection collectionB) {
		boolean bCollided = false;
		if ((collectionA != null) && (collectionB != null)) {
			if (boundingBoxesIntersect(collectionA, collectionB)) {
				ArrayList<IShape> linesA = collectionA.getLines();
				if (linesA != null) {
					Iterator<IShape> iiLinesA = linesA.iterator();
					while ((iiLinesA.hasNext()) && (!bCollided)) {
						IShape lineA = iiLinesA.next();
						if (lineA != null) {
							ArrayList<IShape> linesB = collectionB.getLines();
							if (linesA != null) {
								Iterator<IShape> iiLinesB = linesB.iterator();
								while ((iiLinesB.hasNext()) && (!bCollided)) {
									IShape lineB = iiLinesB.next();
									if (lineB != null) {
										bCollided = lineSegmentTouchesOrCrossesLine(lineA, lineB)
												&& lineSegmentTouchesOrCrossesLine(lineB, lineA);
									}
								}
							}
						}
					}
				}
			}
		}

		return bCollided;
	}

	/**
	 * A helper method to check if two bounding boxes, represented by two line
	 * collections, intersect with each other.
	 *
	 * @param collectionA
	 *            The line collection of the first object.
	 * @param collectionB
	 *            The line collection of the first object.
	 * @return true, if two bounding boxes intersect with each other.
	 */
	/* quick test to determine if its worth checking all the lines */
	private static boolean boundingBoxesIntersect(final LineCollection collectionA, final LineCollection collectionB) {
		return (collectionA.getMinX() <= collectionB.getMaxX() && collectionA.getMaxX() >= collectionB.getMinX()
				&& collectionA.getMinY() <= collectionB.getMaxY() && collectionA.getMaxY() >= collectionB.getMinY());
	}

	/**
	 * A helper method to check if a point is on a Line.
	 *
	 * @param lineA
	 *            A Line object.
	 * @param bX
	 *            The X coordinate of a point.
	 * @param bY
	 *            The Y coordinate of a point.
	 * @return true, if the point is on the Line.
	 */
	private static boolean isPointOnLine(IShape lineA, float bX, float bY) {
		float cross = crossProduct(lineA.getMaxX() - lineA.getMinX(), lineA.getMaxY() - lineA.getMinY(),
				bX - lineA.getMinX(), bY - lineA.getMinY());

		return Math.abs(cross) < EPSILON;
	}

	/**
	 * A helper method to check if a point is on the right of a Line.
	 *
	 * @param lineA
	 *            A Line object.
	 * @param bX
	 *            The X coordinate of a point.
	 * @param bY
	 *            The Y coordinate of a point.
	 * @return true, if a point is on the right of a Line.
	 */
	private static boolean isPointRightOfLine(IShape lineA, float xB, float yB) {
		return crossProduct(lineA.getMaxX() - lineA.getMinX(), lineA.getMaxY() - lineA.getMinY(), xB - lineA.getMinX(),
				yB - lineA.getMinY()) < 0;
	}

	/**
	 * A helper method to check if two Line objects touch or cross each other.
	 *
	 * @param iShapeA
	 *            The first Line object.
	 * @param iShapeB
	 *            The second Line object.
	 * @return true, if two Line objects touch or cross each other.
	 */
	private static boolean lineSegmentTouchesOrCrossesLine(IShape iShapeA, IShape iShapeB) {
		return isPointOnLine(iShapeA, iShapeB.getMinX(), iShapeB.getMinY())
				|| isPointOnLine(iShapeA, iShapeB.getMaxX(), iShapeB.getMaxY())
				|| (isPointRightOfLine(iShapeA, iShapeB.getMinX(), iShapeB.getMinY())
						^ isPointRightOfLine(iShapeA, iShapeB.getMaxX(), iShapeB.getMaxY()));
	}

	/**
	 * A helper method to calculate the cross product.
	 *
	 * @param xA
	 *            The X coordinate of the first object.
	 * @param yA
	 *            The Y coordinate of the first object.
	 * @param xB
	 *            The X coordinate of the second object.
	 * @param yB
	 *            The Y coordinate of the second object.
	 * @return The cross product of the two objects.
	 */
	private static float crossProduct(float xA, float yA, float xB, float yB) {
		return ((xA * yB) - (xB * yA));
	}
}