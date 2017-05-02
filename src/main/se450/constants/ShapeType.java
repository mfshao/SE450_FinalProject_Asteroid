package main.se450.constants;

import java.util.Random;

/**
 * The Enum ShapeType keeps predefined shape type properties.
 */
public enum ShapeType {

	/** The square. */
	SQUARE,
	/** The circle. */
	CIRCLE,
	/** The triangle. */
	TRIANGLE,
	/** The ship. */
	SHIP,
	/** The line. */
	LINE;

	/** The Constant VALUES. */
	private static final ShapeType[] VALUES = values();

	/** The Constant SIZE. */
	private static final int SIZE = VALUES.length;

	/** The Constant RANDOM. */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * Get a random shape type from the list of ShapeType enum.
	 *
	 * @return This returns a random ShapeType.
	 */
	public static ShapeType randomShapeType() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}

	/**
	 * Get a random basic shape type from the list of ShapeType enum. Basic
	 * shape types includes square, triangle and circle only.
	 *
	 * @return This returns a random basic ShapeType (square, triangle and
	 *         circle only).
	 */
	public static ShapeType randomBasicShapeType() {
		return VALUES[RANDOM.nextInt(SIZE - 2)];
	}
}
