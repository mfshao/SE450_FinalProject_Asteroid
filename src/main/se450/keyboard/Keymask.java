package main.se450.keyboard;

/*
 * Name     : Mingfei Shao
 * Depaul#  : 1807687
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class Keyboard
 *
 */

/**
 * The Class Keymask to defines a series of maskings for several keys that
 * allows to be pressed simultaneously.
 */
public class Keymask {

	/** The Constant NO_KEY. */
	public static final int NO_KEY = 0x0;

	/** The Constant FIRE_KEY. */
	public static final int FIRE_KEY = 0x1;

	/** The Constant LEFT_KEY. */
	public static final int LEFT_KEY = 0x10;

	/** The Constant RIGHT_KEY. */
	public static final int RIGHT_KEY = 0x100;

	/** The Constant FORWARD_THRUST_KEY. */
	public static final int FORWARD_THRUST_KEY = 0x1000;

	/** The Constant REVERSE_THRUST_KEY. */
	public static final int REVERSE_THRUST_KEY = 0x10000;

	/** The Constant ALL_REPEAT_KEYS. */
	public static final int ALL_REPEAT_KEYS = (REVERSE_THRUST_KEY | FORWARD_THRUST_KEY | RIGHT_KEY | LEFT_KEY
			| FIRE_KEY);
}
