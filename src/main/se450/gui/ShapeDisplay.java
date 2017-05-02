package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import main.se450.factories.RandomShapeListFactory;
import main.se450.interfaces.IDebris;
import main.se450.interfaces.IGameCompleteObservable;
import main.se450.interfaces.IGameOverObservable;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStartEndlessObservable;
import main.se450.interfaces.IStartObservable;
import main.se450.model.PlayerShip;
import main.se450.observable.GameComplete;
import main.se450.observable.GameOver;
import main.se450.observable.Motion;
import main.se450.observable.Start;
import main.se450.observable.StartEndless;
import main.se450.singletons.DebrisList;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ModelMotionManager;
import main.se450.singletons.PlayerShipManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;

/**
 * The Class ShapeDisplay defines and manages the main game screen where all the
 * shapes, shots, debris and player ship will be displayed.
 */
public class ShapeDisplay extends JPanel implements IObservable, IStartObservable, IStartEndlessObservable,
		IGameOverObservable, IGameCompleteObservable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The player ship. */
	private PlayerShip playerShip = null;

	/** The ShotList. */
	private ArrayList<IShot> iShots = null;

	/** The ShapeList. */
	private ArrayList<IShape> iShapes = null;

	/** The DebrisList. */
	private ArrayList<IDebris> iDebrisList = null;

	/** The is endless flag. */
	private boolean isEndless = false;

	/** The has started flag. */
	private boolean hasStarted = false;

	/**
	 * Instantiates a new shape display area.
	 */
	public ShapeDisplay() {
		Start.startObserving(this);
		StartEndless.startObserving(this);
		GameOver.startObserving(this);
		GameComplete.startObserving(this);
	}

	/**
	 * Set the Lists and the player ship object.
	 */
	public void setModels() {
		this.playerShip = PlayerShipManager.getPlayerShipManager().getPlayerShip();
		this.iShots = ShotList.getShotList().getShots();
		this.iDebrisList = DebrisList.getDebrisList().getDebris();
		this.iShapes = ShapeList.getShapeList().getShapes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#validateTree()
	 */
	@Override
	public void validateTree() {
		super.validateTree();

		Dimension dimension = getSize();

		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}

	/**
	 * Update graphics. Paint shots, shapes, debris and ship on to the display
	 * area.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(),
				DisplayManager.getDisplayManager().getHeight());

		playerShip = PlayerShipManager.getPlayerShipManager().getPlayerShip();

		if (playerShip != null) {
			playerShip.update();
			playerShip.draw(graphics);
		}
		ModelMotionManager.getModelMotionManager().updateArtifacts();
		if (playerShip != null) {
			ModelMotionManager.getModelMotionManager().updateCollisions(isEndless);
		}

		if (hasStarted && !isEndless && iShots.isEmpty() && iShapes.isEmpty() && iDebrisList.isEmpty()) {
			GameComplete.gameComplete();
		}

		if (iShots != null) {
			Iterator<IShot> iiShots = iShots.iterator();
			while (iiShots.hasNext()) {
				IShot iShot = iiShots.next();
				if (iShot != null) {
					iShot.draw(graphics);
				}
			}
		}

		if (iDebrisList != null) {
			Iterator<IDebris> iiDebris = iDebrisList.iterator();
			while (iiDebris.hasNext()) {
				IDebris iDebris = iiDebris.next();
				if (iDebris != null) {
					iDebris.draw(graphics);
				}
			}
		}

		if (iShapes != null) {
			Iterator<IShape> iiShapes = iShapes.iterator();
			while (iiShapes.hasNext()) {
				IShape iShape = iiShapes.next();
				if (iShape != null) {
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public void update() {
		repaint();
	}

	/**
	 * Start the game in Normal mode.
	 * 
	 * @see main.se450.interfaces.IStartObservable#start()
	 */
	@Override
	public void start() {
		isEndless = false;
		hasStarted = true;
		if (iShapes.isEmpty()) {
			RandomShapeListFactory.fillupShapeList();
		}
		Motion.startObserving(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IGameOverObservable#gameOver()
	 */
	@Override
	public void gameOver() {
		PlayerShipManager.getPlayerShipManager().dismissPlayerShip();
	}

	/**
	 * Start the game in Endless mode.
	 * 
	 * @see main.se450.interfaces.IStartEndlessObservable#startEndless()
	 */
	@Override
	public void startEndless() {
		isEndless = true;
		hasStarted = true;
		if (iShapes.isEmpty()) {
			RandomShapeListFactory.fillupShapeList();
		}
		Motion.startObserving(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IGameCompleteObservable#gameComplete()
	 */
	@Override
	public void gameComplete() {
		Motion.stopObserving(this);
	}
}
