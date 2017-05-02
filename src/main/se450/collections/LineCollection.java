package main.se450.collections;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IShape;
import main.se450.model.Line;

/**
 * The Class LineCollection keeps a collection of Line objects which forms the
 * bounding box of another object.
 */
public class LineCollection {

	/** The min X. */
	private float minX = Long.MAX_VALUE;// Float.MAX_VALUE/MIN_VALUE isn't
										// reliable with Math functions

	/** The min Y. */
	private float minY = Long.MAX_VALUE;

	/** The max X. */
	private float maxX = Long.MIN_VALUE;

	/** The max Y. */
	private float maxY = Long.MIN_VALUE;

	/** The lines. */
	private ArrayList<IShape> lines = new ArrayList<IShape>();

	/**
	 * Helper function to update min and max values after new line was added.
	 */
	private void recalculateMinMax() {
		minX = Long.MAX_VALUE;// Float.MAX_VALUE/MIN_VALUE isn't reliable with
								// Math functions
		minY = Long.MAX_VALUE;
		maxX = Long.MIN_VALUE;
		maxY = Long.MIN_VALUE;

		Iterator<IShape> iSides = lines.iterator();
		while (iSides.hasNext()) {
			IShape iShape = iSides.next();

			minX = Math.min(minX, iShape.getMinX());
			maxX = Math.max(maxX, iShape.getMaxX());
			minY = Math.min(minY, iShape.getMinY());
			maxY = Math.max(maxY, iShape.getMaxY());
		}
	}

	/**
	 * Clear the current collection of Lines and updates min and max.
	 */
	public void clear() {
		lines.clear();

		recalculateMinMax();
	}

	/**
	 * Adds a new Line into existing collection.
	 *
	 * @param line
	 *            The new line got added.
	 */
	public void add(final Line line) {
		lines.add(line);

		minX = Math.min(minX, line.getMinX());
		maxX = Math.max(maxX, line.getMaxX());
		minY = Math.min(minY, line.getMinY());
		maxY = Math.max(maxY, line.getMaxY());
	}

	/**
	 * Get the current collection of Lines.
	 *
	 * @return the ArrayList of Lines
	 */
	public final ArrayList<IShape> getLines() {
		return lines;
	}

	/**
	 * Get the current min X value.
	 *
	 * @return the current min X value.
	 */
	public float getMinX() {
		return minX;
	}

	/**
	 * Get current min Y value.
	 *
	 * @return the current min Y value.
	 */
	public float getMinY() {
		return minY;
	}

	/**
	 * Get current max X value.
	 *
	 * @return the current max X value
	 */
	public float getMaxX() {
		return maxX;
	}

	/**
	 * Get current max Y value.
	 *
	 * @return the current max Y value
	 */
	public float getMaxY() {
		return maxY;
	}
}
