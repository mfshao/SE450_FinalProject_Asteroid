package main.se450.strategies;

import java.awt.Dimension;

import main.se450.interfaces.IStrategy;
import main.se450.model.Shape;

/**
 * The Class PassThruStrategy defines a border strategy that an object will pass
 * through the border and re-appears an the other side.
 */
public class PassThruStrategy implements IStrategy {

	/** The dimension. */
	private Dimension dimension = new Dimension();

	/**
	 * Instantiates a new pass thru strategy.
	 *
	 * @param newDimension
	 *            The dimension of current screen area.
	 */
	public PassThruStrategy(Dimension newDimension) {
		dimension.setSize(newDimension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IStrategy#execute(main.se450.model.Shape)
	 */
	@Override
	public void execute(Shape shape) {
		// Check X's
		float maxX = shape.getMaxX();
		float minX = shape.getMinX();

		if (maxX < 0.0f) {
			shape.translateX(-minX + dimension.width);
		} else if (minX > dimension.width) {
			shape.translateX(-maxX);
		}

		// Check Y's
		float maxY = shape.getMaxY();
		float minY = shape.getMinY();

		if (maxY < 0.0f) {
			shape.translateY(-minY + dimension.height);
		} else if (minY > dimension.height) {
			shape.translateY(-maxY);
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