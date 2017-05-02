package main.se450.utilities;

import java.awt.Color;

/**
 * The parser for the JSON file.
 *
 * @author Anthony Freund
 */

public class Extractor {

	/**
	 * Extract color.
	 *
	 * @param object
	 *            The object to be extracted.
	 * @return The color extracted from the object.
	 */
	final public static Color extractColor(final Object object) {
		return new Color(extractInteger(object));
	}

	/**
	 * Extract integer.
	 *
	 * @param object
	 *            The object to be extracted.
	 * @return the integer extracted from the object.
	 */
	final public static Integer extractInteger(final Object object) {
		return Integer.parseInt(object.toString(), 10);
	}

	/**
	 * Extract float.
	 *
	 * @param object
	 *            The object to be extracted.
	 * @return the float extracted from the object.
	 */
	final public static Float extractFloat(final Object object) {
		return Float.parseFloat(object.toString());
	}
}
