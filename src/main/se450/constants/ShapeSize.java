package main.se450.constants;

import java.util.Random;

/**
 * The Enum ShapeSize keeps predefined property values for shapes with different sizes.
 */
public enum ShapeSize {
    
    /** The large. */
    LARGE (45, 3), 
    
    /** The medium. */
    MEDIUM (30, 2), 
    
    /** The small. */
    SMALL (15, 0);
	
	/** The length. */
	private final int length;
	
	/** The children. */
	private final int children;
	
	/** The score. */
	private final int score;
	
	/** The multiplier. */
	private final int multiplier;
	
	/**
	 * Instantiates a new ShapeSize object.
	 *
	 * @param nLength The length of a single side of the bounding box of the shape.
	 * @param nChildren The total number of children will be generated after this shape has been destroyed.
	 */
	ShapeSize(int nLength, int nChildren) {
		this.length = nLength;
		this.children = nChildren;
		this.score = 10;
		this.multiplier = 10;
	}
	
	/**
	 * Get the length of the side.
	 *
	 * @return the int This returns the length of the side.
	 */
	public int length() {
		return this.length;
	}
	
	/**
	 * Get the number of children.
	 *
	 * @return the int This returns the number of children.
	 */
	public int children() {
		return this.children;
	}
	
	/**
	 * Get the value of score.
	 *
	 * @return the int This returns the value of score.
	 */
	public int score() {
		return this.score;
	}
	
	/**
	 * Get the value of multiplier.
	 *
	 * @return the int This returns the value of multiplier.
	 */
	public int multiplier() {
		return this.multiplier;
	}
	
	/** The Constant VALUES. */
	private static final ShapeSize[] VALUES = values();
	
	/** The Constant SIZE. */
	private static final int SIZE = VALUES.length;
	
	/** The Constant RANDOM. */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * Get a random shape size from the list of ShapeSize enum.
	 *
	 * @return This returns a random ShapeSize.
	 */
	public static ShapeSize randomShapeSize() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}
}