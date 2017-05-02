package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShape;

/**
 * The singleton Class ShapeList holds and manages all IShape objects.
 */
public class ShapeList {

	/** The shape list. */
	private static ShapeList shapeList = null;

	/** The i shapes. */
	private ArrayList<IShape> iShapes = null;

	static {
		shapeList = new ShapeList();
	}

	/**
	 * Instantiates a new shape list.
	 */
	private ShapeList() {
		iShapes = new ArrayList<IShape>();
	}

	/**
	 * Get the shape list.
	 *
	 * @return The shape list
	 */
	public final static ShapeList getShapeList() {
		return shapeList;
	}

	/**
	 * Get the shapes.
	 *
	 * @return The shapes
	 */
	public final ArrayList<IShape> getShapes() {
		return iShapes;
	}

	/**
	 * Add a List of IShape objects into the shape list.
	 *
	 * @param iShapeList
	 *            A List of new IShape objects that will be added in current
	 *            shape list.
	 */
	public void addShapes(final ArrayList<IShape> iShapeList) {
		iShapes.addAll(iShapeList);
	}
}
