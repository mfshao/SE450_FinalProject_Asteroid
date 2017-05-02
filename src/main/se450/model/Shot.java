package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IShot;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.strategies.PassThruStrategy;

/**
 * The Class Shot represent a shot object fired by a ship.
 */
public class Shot extends Circle implements IShot {

	/** The circle. */
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);

	/** The life time. */
	private int lifeTime = 0;

	/**
	 * Instantiates a new shot object.
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
	 */
	public Shot(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation) {
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, Color.WHITE,
				new PassThruStrategy(DisplayManager.getDisplayManager().getDimension()), ShapeSize.SMALL, 0, 0, 0);

		lifeTime = ConfigurationManager.getConfigurationManager().getConfiguration().getShotLifetime() * 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.model.Circle#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		circle.setFrame(getMinX(), getMinY(),
				ConfigurationManager.getConfigurationManager().getConfiguration().getShotDiameter() * 2,
				ConfigurationManager.getConfigurationManager().getConfiguration().getShotDiameter() * 2);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(getColor());
		g2d.fill(circle);
		g2d.draw(circle);
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
	 * @see main.se450.interfaces.IShot#isExpired()
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
}
