package main.se450.singletons;

/**
 * The singleton Class InformationManager handles and manages all the accesses
 * to properties related player's score, lives and available shields.
 */
public class InformationManager {

	/** The information manager. */
	private static InformationManager informationManager = null;

	/** The score. */
	private int score = 0;

	/** The lives. */
	private int lives = 0;

	/** The shields. */
	private int shields = 0;

	static {
		informationManager = new InformationManager();
	}

	/**
	 * Instantiates a new information manager.
	 */
	private InformationManager() {
		lives = ConfigurationManager.getConfigurationManager().getConfiguration().getLives();
		shields = ConfigurationManager.getConfigurationManager().getConfiguration().getShields();
	}

	/**
	 * Get the information manager.
	 *
	 * @return The information manager
	 */
	public final static InformationManager getInformationManager() {
		return informationManager;
	}

	/**
	 * Get the current score.
	 *
	 * @return The current score
	 */
	public final int getScore() {
		return score;
	}

	/**
	 * Add scores to the current score.
	 *
	 * @param iScore
	 *            The score to be added.
	 */
	public final void addScore(int iScore) {
		score += iScore;
	}

	/**
	 * Get the current lives.
	 *
	 * @return The current lives
	 */
	public final int getLives() {
		return lives;
	}

	/**
	 * Decrease lives by one.
	 */
	public final void decreaseLives() {
		lives--;
	}

	/**
	 * Get the current available lives.
	 *
	 * @return The current available lives.
	 */
	public final int getShields() {
		return shields;
	}

	/**
	 * Decrease shields by one.
	 */
	public final void decreaseShields() {
		shields--;
	}

	/**
	 * Reset all properties to their default values.
	 */
	public final void reset() {
		score = 0;
		lives = 10;
		shields = 3;
	}
}
