package main.se450.singletons;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * The singleton Class DisplayManager handles and manages all the accesses to
 * properties related to graphics and display.
 */
public class DisplayManager {

	/** The display manager. */
	private static DisplayManager displayManager = null;

	/** The width. */
	private int width = 0;

	/** The height. */
	private int height = 0;

	/** The graphics. */
	private Graphics graphics = null;

	static {
		displayManager = new DisplayManager();
	}

	/**
	 * Instantiates a new display manager.
	 */
	private DisplayManager() {
	}

	/**
	 * Get the display manager.
	 *
	 * @return The display manager
	 */
	public final static DisplayManager getDisplayManager() {
		return displayManager;
	}

	/**
	 * Set a new display size.
	 *
	 * @param nWidth
	 *            The width of a display area.
	 * @param nHeight
	 *            The height of a display area.
	 */
	public void setDisplaySize(int nWidth, int nHeight) {
		width = nWidth;
		height = nHeight;
	}

	/**
	 * Set the graphics that currently being used by the display.
	 *
	 * @param oGraphics
	 *            The graphics that currently being used by the display.
	 */
	public void setGraphics(Graphics oGraphics) {
		graphics = oGraphics;
	}

	/**
	 * Get the graphics that currently being used by the display.
	 *
	 * @return The graphics that currently being used by the display.
	 */
	public Graphics getGraphics() {
		return graphics;
	}

	/**
	 * Get the width of current display area.
	 *
	 * @return The width of current display area.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of current display area.
	 *
	 * @return The height of current display area.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the dimension of current display area.
	 *
	 * @return The dimension of current display area.
	 */
	public Dimension getDimension() {
		return new Dimension(width, height);
	}
}
