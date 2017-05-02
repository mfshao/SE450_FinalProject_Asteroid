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
 * The Class Keystate to defines a series of states for keys.
 */
public class Keystate {

	/** The key state. */
	private int keyState = Keymask.NO_KEY;

	/**
	 * Instantiates a new key state with no key assigned.
	 */
	public Keystate() {
		keyState = Keymask.NO_KEY;
	}

	/**
	 * Instantiates a new key state with a certain key state.
	 *
	 * @param keystate
	 *            The key state to be initialized.
	 */
	public Keystate(int keystate) {
		keyState = keystate;
	}

	/**
	 * Clear any existing key states.
	 */
	public void clear() {
		keyState = Keymask.NO_KEY;
	}

	/**
	 * Checks if key state has been cleared.
	 *
	 * @return true, if key state has been cleared
	 */
	public boolean isClear() {
		return (keyState == Keymask.NO_KEY);
	}

	/**
	 * Get the key state.
	 *
	 * @return The current key state
	 */
	public int getState() {
		return keyState;
	}

	/**
	 * Set one or more keys down.
	 *
	 * @param keyMask
	 *            The input key sequence.
	 */
	public void setKeyDown(int keyMask) {
		keyState |= keyMask;
	}

	/**
	 * Set one or more keys up.
	 *
	 * @param keyMask
	 *            The input key sequence
	 */
	public void setKeyUp(int keyMask) {
		keyState &= (~keyMask);
	}

	/**
	 * Checks if one or more keys is being pressed.
	 *
	 * @param keyMask
	 *            The input key sequence
	 * @return true, if the key sequence is being pressed
	 */
	public boolean isPressed(int keyMask) {
		return ((keyMask & keyState) == keyMask);
	}

	/**
	 * Checks if one or more keys have been released.
	 *
	 * @param keyMask
	 *            The input key sequence
	 * @return true, if one or more keys have been released
	 */
	public boolean isReleased(int keyMask) {
		return ((keyMask & keyState) == 0);
	}

	/**
	 * Get all key pressed states.
	 *
	 * @return The pressed states for all the keys
	 */
	public int allKeyPressedStates() {
		return (keyState & Keymask.ALL_REPEAT_KEYS);
	}

	/**
	 * Checks if any key is being pressed.
	 *
	 * @param keystate
	 *            The desired key state
	 * @return true, if any key is being pressed
	 */
	public static boolean isAnyKeyPressed(int keystate) {
		return (keystate != Keymask.NO_KEY);
	}

	/**
	 * Checks if a certain key is being pressed.
	 *
	 * @param keystate
	 *            The desired key state
	 * @param keymask
	 *            The input key sequence
	 * @return true, if a certain key is being pressed
	 */
	public static boolean isPressed(int keystate, int keymask) {
		return ((keymask & keystate) == keymask);
	}
}
