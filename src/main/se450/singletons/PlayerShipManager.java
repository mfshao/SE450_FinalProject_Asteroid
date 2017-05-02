package main.se450.singletons;

import main.se450.model.PlayerShip;

/**
 * The singleton Class PlayerShipManager handles and manages all the access to
 * the player ship object, including respawn or dismiss a ship.
 */
public class PlayerShipManager {

	/** The player ship manager. */
	private static PlayerShipManager playerShipManager = null;

	/** The player ship. */
	private PlayerShip playerShip = null;

	static {
		playerShipManager = new PlayerShipManager();
	}

	/**
	 * Instantiates a new player ship manager.
	 */
	private PlayerShipManager() {
		playerShip = new PlayerShip();
	}

	/**
	 * Get the player ship manager.
	 *
	 * @return The player ship manager
	 */
	public final static PlayerShipManager getPlayerShipManager() {
		return playerShipManager;
	}

	/**
	 * Get the player ship.
	 *
	 * @return The player ship
	 */
	public final PlayerShip getPlayerShip() {
		return playerShip;
	}

	/**
	 * Respawn a new player ship.
	 */
	public final void respawnPlayerShip() {
		playerShip = new PlayerShip();
	}

	/**
	 * Dismiss the current player ship (destory).
	 */
	public final void dismissPlayerShip() {
		playerShip = null;
	}

}
