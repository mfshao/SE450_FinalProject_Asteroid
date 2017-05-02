package main.se450.model;

/**
 * The Class Vector represents moving vector object.
 */
public class Vector {

	/** The x. */
	private float X;

	/** The y. */
	private float Y;

	/**
	 * Instantiates a new moving vector object.
	 *
	 * @param X
	 *            The delta value in X direction.
	 * @param Y
	 *            The delta value in Y direction.
	 */
	public Vector(float X, float Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * Get the delta value in X direction..
	 *
	 * @return The delta value in X direction.
	 */
	public float getX() {
		return X;
	}

	/**
	 * Get the delta value in Y direction.
	 *
	 * @return The delta value in Y direction.
	 */
	public float getY() {
		return Y;
	}

}
