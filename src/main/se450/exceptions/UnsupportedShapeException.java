package main.se450.exceptions;

/**
 * The Class UnsupportedShapeException a customized exception that will be
 * thrown out when the JSON shape parser has encountered with an unsupported
 * shape type.
 */
public class UnsupportedShapeException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	private String type = "Unknown";

	/**
	 * Instantiates a new unsupported shape exception.
	 */
	public UnsupportedShapeException() {
	}

	/**
	 * Instantiates a new unsupported shape exception with customized shape type
	 * information.
	 *
	 * @param sType
	 *            The string that represents an unsupported shape type.
	 */
	public UnsupportedShapeException(final String sType) {
		type = sType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "The Shape : " + type + " is no longer supported!";
	}
}
