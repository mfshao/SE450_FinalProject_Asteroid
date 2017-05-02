package main.se450.exceptions;

/**
 * The Class BadStrategyException defines a customized exception that will be
 * thrown out when the strategy parser has encountered with an unknown strategy
 * type.
 */
public class BadStrategyException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The strategy. */
	private String strategy = "Unknown";

	/**
	 * Instantiates a new bad strategy exception.
	 */
	public BadStrategyException() {
	}

	/**
	 * Instantiates a new bad strategy exception with customized strategy type
	 * information.
	 *
	 * @param sStrategy
	 *            The string that represents an unknown strategy type.
	 */
	public BadStrategyException(final String sStrategy) {
		strategy = sStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return "Bad Strategy : " + strategy;
	}
}
