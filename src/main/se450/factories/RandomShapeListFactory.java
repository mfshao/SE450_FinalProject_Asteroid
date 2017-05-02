package main.se450.factories;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import main.se450.constants.ShapeSize;
import main.se450.constants.ShapeType;
import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.BadStrategyException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IDebris;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Configuration;
import main.se450.model.DebrisPoint;
import main.se450.model.PlayerShip;
import main.se450.model.ShipLine;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DebrisList;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;

/**
 * A factory for creating randomized shapes and putting them into the ShapeList
 * object..
 */
public class RandomShapeListFactory {

	/** The Constant CONFIGURATION. */
	private static final Configuration CONFIGURATION = ConfigurationManager.getConfigurationManager()
			.getConfiguration();

	/** The Constant MAX_SHAPE_SPEED. */
	private final static float MAX_SHAPE_SPEED = CONFIGURATION.getShotSpeed() / 2.0f;

	/**
	 * Instantiates a new random shape list factory.
	 */
	private RandomShapeListFactory() {
	}

	/**
	 * Fillup the existing ShapeList with random generated shapes until the
	 * number of shapes equals to the predefined number of shapes value in the
	 * configuration file.
	 */
	public static void fillupShapeList() {
		ArrayList<IShape> newShapes = new ArrayList<IShape>();
		ArrayList<IShape> currentShapeList = ShapeList.getShapeList().getShapes();

		int numOfNewShapes = CONFIGURATION.getShapes() - currentShapeList.size();

		if (numOfNewShapes > 0) {
			for (int i = 0; i < numOfNewShapes; i++) {
				ShapeSize newShapeSize = ShapeSize.randomShapeSize();

				int newXCoordinate = 0;
				int newYCoordinate = 0;
				if (Math.random() < 0.5) {
					newXCoordinate = generateRandomXCoordinate();
				} else {
					newYCoordinate = generateRandomYCoordinate();
				}

				try {
					IShape iShape = ShapeFactory.makeShape(ShapeType.randomBasicShapeType().name(), newXCoordinate,
							newYCoordinate, newXCoordinate + newShapeSize.length(),
							newYCoordinate + newShapeSize.length(), generateRandomSpeed(), generateRandomSpeed(),
							generateRandomSpeed(), generateRandomColor(), generateRandomStrategy(), newShapeSize,
							newShapeSize.score(), newShapeSize.multiplier(), newShapeSize.children());
					newShapes.add(iShape);
				} catch (BadShapeException | UnsupportedShapeException e) {
					System.out.println(e);
				}
			}
		}

		currentShapeList.addAll(newShapes);
	}

	/**
	 * A wrapper method used to respawn shapes from a single shape.
	 *
	 * @param iShape
	 *            The single IShape object that will be used to generate
	 *            children from.
	 */
	public static void respawnShapes(IShape iShape) {
		respawn(iShape);
	}

	/**
	 * A wrapper method used to respawn shapes from a list of shapes.
	 *
	 * @param iShapes
	 *            An ArrayList of IShape objects that will be used to generate
	 *            children from
	 */
	public static void respawnShapes(ArrayList<IShape> iShapes) {
		Iterator<IShape> iiShapes = iShapes.iterator();
		while (iiShapes.hasNext()) {
			IShape iShape = iiShapes.next();
			respawn(iShape);
		}
	}

	/**
	 * The actual shape respawn method.
	 *
	 * @param iShape
	 *            The single IShape object that will be used to generate
	 *            children from.
	 */
	private static void respawn(IShape iShape) {
		ArrayList<IShape> newShapes = new ArrayList<IShape>();

		if (iShape instanceof PlayerShip) {
			ArrayList<IShape> shipLines = iShape.getLineCollection().getLines();
			ArrayList<IDebris> debrisShipLines = new ArrayList<>();

			for (int i = 0; i < shipLines.size(); i++) {
				IShape shipLine = shipLines.get(i);
				IDebris debrisShipLine = new ShipLine(shipLine.getMinX(), shipLine.getMinY(), shipLine.getMaxX(),
						shipLine.getMaxY(), generateRandomSpeed(), generateRandomSpeed(), shipLine.getRotation(),
						shipLine.getColor());
				debrisShipLines.add(debrisShipLine);
			}
			DebrisList.getDebrisList().addDebris(debrisShipLines);
		} else if (iShape.getChildren() > 0) {
			ShapeSize currentShapeSize = iShape.getShapeSize();
			ShapeSize newShapeSize = ShapeSize.values()[currentShapeSize.ordinal() + 1];
			float newShapeDiameter = newShapeSize.length() / 2.0f;

			for (int i = 0; i < iShape.getChildren(); i++) {
				try {
					IShape newShape = ShapeFactory.makeShape(iShape.getClass().getSimpleName().toUpperCase(),
							iShape.getMidpointX1X3() - newShapeDiameter, iShape.getMidpointY1Y3() - newShapeDiameter,
							iShape.getMidpointX1X3() + newShapeDiameter, iShape.getMidpointY1Y3() + newShapeDiameter,
							generateRandomSpeed(), generateRandomSpeed(), iShape.getRotation(), iShape.getColor(),
							iShape.getStrategy(), newShapeSize, iShape.getScore(), iShape.getMultiplier(),
							newShapeSize.children());
					newShapes.add(newShape);
				} catch (BadShapeException | UnsupportedShapeException e) {
					System.out.println(e);
				}
			}
		} else {
			ArrayList<IDebris> debris = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				IDebris newDebris = new DebrisPoint(iShape.getMidpointX1X3(), iShape.getMidpointY1Y3(),
						iShape.getMidpointX1X3(), iShape.getMidpointY1Y3(), generateRandomSpeed(),
						generateRandomSpeed(), generateRandomSpeed(), iShape.getColor());
				debris.add(newDebris);
			}
			DebrisList.getDebrisList().addDebris(debris);
		}

		ShapeList.getShapeList().addShapes(newShapes);
	}

	/**
	 * Generate a random speed within the range of [-MAX_SHAPE_SPEED,
	 * MAX_SHAPE_SPEED].
	 *
	 * @return the float A random speed in the range of [-MAX_SHAPE_SPEED,
	 *         MAX_SHAPE_SPEED].
	 */
	private static float generateRandomSpeed() {
		int sign = (Math.random() < 0.5) ? -1 : 1;
		return (float) (sign * Math.random() * MAX_SHAPE_SPEED);
	}

	/**
	 * Generate a random X coordinate.
	 *
	 * @return the int A random X coordinate.
	 */
	private static int generateRandomXCoordinate() {
		Random random = new Random();
		return random.nextInt(DisplayManager.getDisplayManager().getWidth());
	}

	/**
	 * Generate a random Y coordinate.
	 *
	 * @return the int A random Y coordinate.
	 */
	private static int generateRandomYCoordinate() {
		Random random = new Random();
		return random.nextInt(DisplayManager.getDisplayManager().getHeight());
	}

	/**
	 * Generate random visible color using HSB color model.
	 *
	 * @return the color The HSB color which has a 100% brightness and >80%
	 *         saturation to guarantee it is visible.
	 */
	private static Color generateRandomColor() {
		float h = (float) Math.random();
		float s = 0.0f;

		while (s < 0.8f) {
			s = (float) Math.random();
		}

		return Color.getHSBColor(h, s, 1.0f);
	}

	/**
	 * Generate a random strategy.
	 *
	 * @return the IStrategy A random strategy from "PassThru" or "Rebound".
	 */
	private static IStrategy generateRandomStrategy() {
		try {
			if (Math.random() < 0.5) {
				return StrategyFactory.makeStrategy("PassThru", DisplayManager.getDisplayManager().getDimension());
			} else {
				return StrategyFactory.makeStrategy("Rebound", DisplayManager.getDisplayManager().getDimension());
			}
		} catch (BadStrategyException e) {
			System.out.println(e);
			return null;
		}
	}
}
