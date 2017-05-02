package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.BigExplosion;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.MediumExplosion;
import main.se450.sound.ReverseThrust;
import main.se450.sound.SmallExplosion;

/**
 * The singleton Class SoundManager handles and manages all the accesses to
 * sound files that are used in the program.
 */
public class SoundManager {

	/** The sound manager. */
	private static SoundManager soundManager = null;

	/** The sounds. */
	private HashMap<String, ISound> sounds = null;

	/** The big explosion. */
	private ISound bigExplosion = null;

	/** The medium explosion. */
	private ISound mediumExplosion = null;

	/** The small explosion. */
	private ISound smallExplosion = null;

	static {
		soundManager = new SoundManager();
	}

	/**
	 * Instantiates a new sound manager and put fire, forward thrust, reverse
	 * thrust, big explosion, medium explosion and small explosion sound effects
	 * into it.
	 */
	private SoundManager() {
		sounds = new HashMap<String, ISound>();

		sounds.put(Constants.FIRE, new Fire());
		sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
		sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());

		bigExplosion = new BigExplosion();
		mediumExplosion = new MediumExplosion();
		smallExplosion = new SmallExplosion();
	}

	/**
	 * Get the sound manager.
	 *
	 * @return The sound manager
	 */
	public final static SoundManager getSoundManager() {
		return soundManager;
	}

	/**
	 * Play fire sound effect.
	 */
	public void playFire() {
		sounds.get(Constants.FIRE).play();
	}

	/**
	 * Play forward thrust sound effect.
	 */
	public void playForwardThrust() {
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}

	/**
	 * Play reverse thrust sound effect.
	 */
	public void playReverseThrust() {
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}

	/**
	 * Play big explosion sound effect.
	 */
	public void playBigExplosion() {
		bigExplosion.play();
	}

	/**
	 * Play medium explosion sound effect.
	 */
	public void playMediumExplosion() {
		mediumExplosion.play();
	}

	/**
	 * Play small explosion sound effect.
	 */
	public void playSmallExplosion() {
		smallExplosion.play();
	}
}
