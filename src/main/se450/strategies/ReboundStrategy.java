package main.se450.strategies;

import java.awt.Dimension;

import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;

/**
 * The Class ReboundStrategy defines a border strategy that an object will be
 * bounced back if it contacts the border.
 */
public class ReboundStrategy implements IStrategy {

	/** The dimension. */
	private Dimension dimension = new Dimension(0, 0);

	/**
	 * Instantiates a new rebound strategy.
	 *
	 * @param newDimension
	 *            The dimension of current screen area.
	 */
	public ReboundStrategy(Dimension newDimension) {
		dimension.setSize(newDimension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IStrategy#execute(main.se450.model.Shape)
	 */
	@Override
	public void execute(Shape shape) {
		float nMinX = shape.getMinX();
		float nMaxX = shape.getMaxX();

		float nMinY = shape.getMinY();
		float nMaxY = shape.getMaxY();

		if (nMinX < 0.0f) {
			shape.setX(Math.abs(shape.getX()));
		} else if (nMaxX > dimension.getWidth()) {
			shape.setX(-Math.abs(shape.getX()));
		}

		if (nMinY < 0.0f) {
			shape.setY(Math.abs(shape.getY()));
		} else if (nMaxY > dimension.getHeight()) {
			shape.setY(-Math.abs(shape.getY()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IStrategy#updateDimension(java.awt.Dimension)
	 */
	@Override
	public void updateDimension(Dimension newDimension) {
		dimension.setSize(newDimension);
	}
}