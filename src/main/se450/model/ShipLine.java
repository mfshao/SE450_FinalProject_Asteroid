package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IDebris;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.strategies.PassThruStrategy;

/**
 * The Class ShipLine represents the lines that breaks from the ship when the
 * ship was destroyed.
 */
public class ShipLine extends Line implements IDebris {

	/** The life time. */
	private int lifeTime = 0;

	/**
	 * Instantiates a new ship line.
	 *
	 * @param nLeft
	 *            The minimum X coordinate of the new shape
	 * @param nTop
	 *            The minimum Y coordinate of the new shape
	 * @param nRight
	 *            The maximum X coordinate of the new shape
	 * @param nBottom
	 *            The maximum Y coordinate of the new shape
	 * @param nX
	 *            The speed in X direction of the new shape
	 * @param nY
	 *            The speed in Y direction of the new shape
	 * @param nRotation
	 *            The speed of rotation of the new shape
	 * @param cColor
	 *            The color of the new shape
	 */
	public ShipLine(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation,
			Color cColor) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor,
				new PassThruStrategy(DisplayManager.getDisplayManager().getDimension()), ShapeSize.SMALL, 0, 0, 0);

		lifeTime = ConfigurationManager.getConfigurationManager().getConfiguration().getShotLifetime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#update()
	 */
	@Override
	public void update() {
		--lifeTime;

		super.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IDebris#isExpired()
	 */
	@Override
	public boolean isExpired() {
		return (lifeTime < 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Shape#getLineCollection()
	 */
	@Override
	public LineCollection getLineCollection() {
		return super.getLineCollection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IDebris#setFrame(float, float)
	 */
	@Override
	public void setFrame(float minX, float minY) {
	}

}
