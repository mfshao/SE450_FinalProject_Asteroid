package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShot;

/**
 * The singleton Class ShotList holds and manages all IShot objects.
 */
public class ShotList {

	/** The shot list. */
	private static ShotList shotList = null;

	/** The i shots. */
	private ArrayList<IShot> iShots = null;

	static {
		shotList = new ShotList();
	}

	/**
	 * Instantiates a new shot list.
	 */
	private ShotList() {
		iShots = new ArrayList<IShot>();
	}

	/**
	 * Get the shot list.
	 *
	 * @return The shot list
	 */
	public final static ShotList getShotList() {
		return shotList;
	}

	/**
	 * Get the shots.
	 *
	 * @return The shots
	 */
	public final ArrayList<IShot> getShots() {
		return iShots;
	}

	/**
	 * Add a List of IShot objects into the shot list.
	 *
	 * @param iShotList
	 *            A List of new IShot objects that will be added in current shot
	 *            list.
	 */
	public void addShots(final ArrayList<IShot> iShotList) {
		iShots.addAll(iShotList);
	}
}
