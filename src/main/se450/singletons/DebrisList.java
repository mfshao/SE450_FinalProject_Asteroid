package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IDebris;

/**
 * The singleton Class DebrisList holds and manages all IDebris objects.
 */
public class DebrisList {

	/** The debris list. */
	private static DebrisList debrisList = null;

	/** The i debris. */
	private ArrayList<IDebris> iDebris = null;

	static {
		debrisList = new DebrisList();
	}

	/**
	 * Instantiates a new debris list.
	 */
	private DebrisList() {
		iDebris = new ArrayList<IDebris>();
	}

	/**
	 * Get the debris list.
	 *
	 * @return The debris list
	 */
	public final static DebrisList getDebrisList() {
		return debrisList;
	}

	/**
	 * Get the debris.
	 *
	 * @return The debris
	 */
	public final ArrayList<IDebris> getDebris() {
		return iDebris;
	}

	/**
	 * Adds a list of debris into debris list.
	 *
	 * @param debris
	 *            The list of debris that are being added into debris list.
	 */
	public void addDebris(final ArrayList<IDebris> debris) {
		iDebris.addAll(debris);
	}

	/**
	 * Adds a single debris into debris list.
	 *
	 * @param debris
	 *            A single debris that are being added into debris list.
	 */
	public void addDebris(IDebris debris) {
		iDebris.add(debris);
	}
}
