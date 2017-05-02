package main.se450.singletons;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.factories.RandomShapeListFactory;
import main.se450.interfaces.IDebris;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShot;
import main.se450.model.PlayerShip;
import main.se450.model.ShieldCircle;
import main.se450.observable.GameOver;
import main.se450.utilities.Collide;

/**
 * The singleton Class ModelMotionManager handles and manages all the location
 * updates and collision checks for all the shapes, shots, debris and the player
 * ship.
 */
public class ModelMotionManager {

	/** The model motion manager. */
	private static ModelMotionManager modelMotionManager = null;

	/** The player ship. */
	private PlayerShip playerShip = null;

	/** The i shots. */
	private ArrayList<IShot> iShots = null;

	/** The i shapes. */
	private ArrayList<IShape> iShapes = null;

	/** The i debris list. */
	private ArrayList<IDebris> iDebrisList = null;

	static {
		modelMotionManager = new ModelMotionManager();
	}

	/**
	 * Instantiates a new model motion manager.
	 */
	private ModelMotionManager() {
		playerShip = PlayerShipManager.getPlayerShipManager().getPlayerShip();
		iShots = ShotList.getShotList().getShots();
		iShapes = ShapeList.getShapeList().getShapes();
		iDebrisList = DebrisList.getDebrisList().getDebris();
	}

	/**
	 * Get the model motion manager.
	 *
	 * @return The model motion manager
	 */
	public final static ModelMotionManager getModelMotionManager() {
		return modelMotionManager;
	}

	/**
	 * Update the location for all artifacts (shots and debris).
	 */
	public void updateArtifacts() {
		if (iShots != null) {
			Iterator<IShot> iiShots = iShots.iterator();
			while (iiShots.hasNext()) {
				IShot iShot = iiShots.next();
				if (iShot.isExpired()) {
					iiShots.remove();
				} else {
					iShot.update();
				}
			}
		}

		iDebrisList = DebrisList.getDebrisList().getDebris();
		if (iDebrisList != null) {
			Iterator<IDebris> iiDebris = iDebrisList.iterator();
			while (iiDebris.hasNext()) {
				IDebris iDebris = iiDebris.next();
				if (iDebris.isExpired()) {
					iiDebris.remove();
					if (iDebris instanceof ShieldCircle) {
						playerShip.turnOffShield();
					}
				} else {
					iDebris.update();
				}
			}
		}
	}

	/**
	 * Update collisions for all the shapes, shots and the player ship.
	 *
	 * @param isEndless
	 *            The flag indicates that whether the game is running in endless
	 *            mode.
	 */
	public void updateCollisions(boolean isEndless) {
		ArrayList<IShape> removedShapes = new ArrayList<IShape>();

		if (iShots != null && iShapes != null) {
			Iterator<IShape> iiShapes = iShapes.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();

				if (playerShip != null) {
					if (Collide.collided(iShape.getLineCollection(), playerShip.getLineCollection())) {
						if (playerShip.isShieldOn()) {
							iShape.setX(-iShape.getX());
							iShape.setY(-iShape.getY());
						} else {
							InformationManager.getInformationManager().decreaseLives();
							removedShapes.add(playerShip);
							playerShip.destroy();
							PlayerShipManager.getPlayerShipManager().respawnPlayerShip();
							playerShip = PlayerShipManager.getPlayerShipManager().getPlayerShip();

							if (InformationManager.getInformationManager().getLives() == 0) {
								GameOver.gameOver();
							}
						}
					}
				}

				Iterator<IShot> iiShots = iShots.iterator();
				while (iiShots.hasNext()) {
					IShot iShot = iiShots.next();
					if (Collide.collided(iShape.getLineCollection(), iShot.getLineCollection())) {
						switch (iShape.getShapeSize()) {
						case LARGE:
							SoundManager.getSoundManager().playBigExplosion();
							break;
						case MEDIUM:
							SoundManager.getSoundManager().playMediumExplosion();
							break;
						case SMALL:
							SoundManager.getSoundManager().playSmallExplosion();
							break;
						default:
							break;
						}

						removedShapes.add(iShape);
						iiShots.remove();
						iiShapes.remove();
						InformationManager.getInformationManager().addScore(iShape.getScore()
								* (int) Math.pow(iShape.getMultiplier(), iShape.getShapeSize().ordinal()));
					}
				}
			}
		}

		if (!removedShapes.isEmpty()) {
			RandomShapeListFactory.respawnShapes(removedShapes);
			if (isEndless) {
				RandomShapeListFactory.fillupShapeList();
			}
		}
	}
}
