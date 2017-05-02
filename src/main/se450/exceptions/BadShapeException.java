package main.se450.exceptions;

/**
 * The Class BadShapeException defines a customized exception that will be
 * thrown out when the JSON shape parser has encountered with an unknown shape
 * type.
 */
public class BadShapeException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	private String type = "Unknown";

	/**
	 * Instantiates a new bad shape exception.
	 */
	public BadShapeException() {
	}

	/**
	 * Instantiates a new bad shape exception with customized shape type
	 * information.
	 *
	 * @param sType
	 *            The string that represents an unknown shape type.
	 */
	public BadShapeException(final String sType) {
		type = sType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "Bad Shape : " + type;
	}
}
