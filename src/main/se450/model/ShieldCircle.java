package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IDebris;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.strategies.PassThruStrategy;

/**
 * The Class ShieldCircle represent the circle as the protective shield around
 * the ship.
 */
public class ShieldCircle extends Circle implements IDebris {

	/** The life time. */
	private int lifeTime = 0;

	/** The circle. */
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);

	/**
	 * Instantiates a new shield circle object.
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
	 */
	public ShieldCircle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY) {
		super(nLeft - 2.0f, nTop - 2.0f, nRight + 2.0f, nBottom + 2.0f, 0.0f, 0.0f, 0.0f, Color.WHITE,
				new PassThruStrategy(DisplayManager.getDisplayManager().getDimension()), ShapeSize.SMALL, 0, 0, 0);

		lifeTime = ConfigurationManager.getConfigurationManager().getConfiguration().getFramesPerSecond() * 5;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.ShapeDroid#update()
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
	 * @see main.se450.model.Circle#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) (graphics);

		g2d.setColor(getColor());
		g2d.draw(circle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IDebris#setFrame(float, float)
	 */
	@Override
	public void setFrame(float minX, float minY) {
		circle.setFrame(minX - 2.0f, minY - 2.0f, getWidth() + 2.0f, getHeight() + 2.0f);
	}

}
