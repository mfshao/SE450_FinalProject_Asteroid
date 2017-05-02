package main.se450.sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import main.se450.interfaces.ISound;

/**
 * The abstract Class Sound to represent a sound file for a certain sound
 * effect.
 */
public abstract class Sound implements ISound, LineListener {

	/** The file name. */
	private String fileName = "";

	/** The clip. */
	private Clip clip = null;

	/** The file. */
	private File file = null;

	/** The is playing. */
	private volatile boolean isPlaying = false;

	/**
	 * Instantiates a new sound effect from sound file.
	 *
	 * @param soundFile
	 *            A sound file
	 */
	public Sound(String soundFile) {
		fileName = soundFile;

		file = new File(fileName);

		try {
			clip = AudioSystem.getClip();
			clip.addLineListener(this);
			clip.open(AudioSystem.getAudioInputStream(file));
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() {
		clip.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.ISound#play()
	 */
	@Override
	public void play() {
		if (setPlaying(false, true)) {
			try {
				clip.setMicrosecondPosition(0);
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.sound.sampled.LineListener#update(javax.sound.sampled.LineEvent)
	 */
	@Override
	public void update(LineEvent myLineEvent) {
		if (myLineEvent.getType() == LineEvent.Type.STOP) {
			setPlaying(true, false);
		}
	}

	/**
	 * Set the playing flag.
	 *
	 * @param bCheck
	 *            The check flag.
	 * @param bIsPlaying
	 *            The is playing flag.
	 * @return true, if the is playing flag was set successfully.
	 */
	private boolean setPlaying(boolean bCheck, boolean bIsPlaying) {
		boolean bSet = false;

		if (bCheck == isPlaying) {
			isPlaying = bIsPlaying;

			bSet = true;
		}

		return bSet;
	}
}
