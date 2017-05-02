package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.se450.collections.LineCollection;
import main.se450.constants.ShapeSize;
import main.se450.interfaces.IDebris;
import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.ILeftObservable;
import main.se450.interfaces.IReverseThrustObservable;
import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IStrategy;
import main.se450.observable.Fire;
import main.se450.observable.ForwardThrust;
import main.se450.observable.HyperSpace;
import main.se450.observable.Left;
import main.se450.observable.ReverseThrust;
import main.se450.observable.Right;
import main.se450.observable.Shield;
import main.se450.observable.Stop;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DebrisList;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.InformationManager;
import main.se450.singletons.ShotList;
import main.se450.singletons.SoundManager;

/**
 * The Class PlayerShip represents player ship object.
 */
public class PlayerShip extends Ship implements IFireObservable, IForwardThrustObservable, IReverseThrustObservable,
		ILeftObservable, IRightObservable, IStopObservable, IHyperSpaceObservable, IShieldObservable {
	
	/** The shot speed. */
	private float shotSpeed = 0.0f;
	
	/** The forward thrust. */
	private float forwardThrust = 0.0f;
	
	/** The reverse thrust. */
	private float reverseThrust = 0.0f;
	
	/** The friction. */
	private float friction = 0.0f;
	
	/** The left right. */
	private float leftRight = 0.0f;
	
	/** The shield on. */
	private boolean shieldOn = false;
	
	/** The i shots. */
	private final ArrayList<IShot> iShots = ShotList.getShotList().getShots();
	
	/** The shield. */
	private IDebris shield = null;

	/** The Constant SHIP_WIDTH. */
	private final static float SHIP_WIDTH = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getShipWidth();
	
	/** The Constant SHIP_HEIGHT. */
	private final static float SHIP_HEIGHT = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getShipHeight();
	
	/** The Constant LEFT. */
	private final static float LEFT = (DisplayManager.getDisplayManager().getWidth() - SHIP_WIDTH) / 2.0f;
	
	/** The Constant TOP. */
	private final static float TOP = (DisplayManager.getDisplayManager().getHeight() - SHIP_HEIGHT) / 2.0f;
	
	/** The Constant SHOT_SPEED. */
	private final static float SHOT_SPEED = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getShotSpeed();
	
	/** The Constant SHIP_FORWARD_THRUST. */
	private final static float SHIP_FORWARD_THRUST = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getForwardThrust();
	
	/** The Constant SHIP_REVERSE_THRUST. */
	private final static float SHIP_REVERSE_THRUST = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getReverseThrust();
	
	/** The Constant FRICTION. */
	private final static float FRICTION = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getFriction();
	
	/** The Constant SHIP_LEFT_RIGHT_SPEED. */
	private final static float SHIP_LEFT_RIGHT_SPEED = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getLeftRight();
	
	/** The Constant SHIP_COLOR. */
	private final static Color SHIP_COLOR = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getColor();
	
	/** The Constant SHIP_STRATEGY. */
	private final static IStrategy SHIP_STRATEGY = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getBorders();
	
	/** The Constant SHOT_DIAMETER. */
	private final static float SHOT_DIAMETER = ConfigurationManager.getConfigurationManager().getConfiguration()
			.getShotDiameter();
	
	/** The Constant SHIP_TOP_SPEED. */
	private final static float SHIP_TOP_SPEED = SHOT_SPEED;

	/**
	 * Instantiates a new player ship object and register observers.
	 */
	public PlayerShip() {
		super(LEFT, TOP, LEFT + SHIP_WIDTH, TOP + SHIP_HEIGHT, 0.0f, 0.0f, 0.0f, SHIP_COLOR, SHIP_STRATEGY, ShapeSize.SMALL, 0, 0,
				0);
		
		updateStrategyBoarder();
		
		shotSpeed = SHOT_SPEED;
		forwardThrust = SHIP_FORWARD_THRUST;
		reverseThrust = SHIP_REVERSE_THRUST;
		friction = FRICTION;
		leftRight = SHIP_LEFT_RIGHT_SPEED;

		Fire.startObserving(this);
		ForwardThrust.startObserving(this);
		ReverseThrust.startObserving(this);
		Left.startObserving(this);
		Right.startObserving(this);
		Stop.startObserving(this);
		HyperSpace.startObserving(this);
		Shield.startObserving(this);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.model.Ship#addSides(main.se450.collections.LineCollection)
	 */
	@Override
	public void addSides(LineCollection lineCollection) {
		if (lineCollection != null) {
			lineCollection.add(new ShipLine(getX4(), getY4(), getMidpointX1X2(), getMidpointY1Y2(), getX(), getY(),
					getRotation(), getColor()));
			lineCollection.add(new ShipLine(getMidpointX1X2(), getMidpointY1Y2(), getX3(), getY3(), getX(), getY(),
					getRotation(), getColor()));
			lineCollection.add(new ShipLine(getX3(), getY3(), getMidpointX1X3(), getMidpointY1Y3(), getX(), getY(),
					getRotation(), getColor()));
			lineCollection.add(new ShipLine(getMidpointX1X3(), getMidpointY1Y3(), getX4(), getY4(), getX(), getY(),
					getRotation(), getColor()));
		}
	}
	
	/**
	 * Update boarder strategy dimensions.
	 */
	public void updateStrategyBoarder() {
		this.getStrategy().updateDimension(DisplayManager.getDisplayManager().getDimension());
	}
	
	/**
	 * Calculate move vector.
	 *
	 * @param fSpeed The speed at which the vector will be calculated.
	 * @return The move vector with the X-axis and Y-axis speeds.
	 */
	private Vector calculateMoveVector(float fSpeed) {
		float xB = getMidpointX1X2();// x tip of ship
		float xA = getMidpointX1X3();// x bottom of ship
		float yB = getMidpointY1Y2();// y tip of ship
		float yA = getMidpointY1Y3();// y bottom of ship

		float xDiff = xB - xA;
		float yDiff = yB - yA;

		float fX = 0.0f;
		float fY = 0.0f;
		if (xDiff == 0.0f) {
			fY += fSpeed * Math.signum(yDiff);
		} else if (yDiff == 0.0f) {
			fX += fSpeed * Math.signum(xDiff);
		} else {
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += fSpeed * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += fSpeed * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
		}

		return new Vector(fX, fY);
	}

	/**
	 * Moves the ship by setting its X-axis and Y-axis speed. The speed will not exceeds the speed limitations
	 *
	 * @param fSpeed The changed ship speed.
	 */
	public void move(float fSpeed) {
		if (getCurrentSpeed() < SHIP_TOP_SPEED) {
			Vector v = calculateMoveVector(fSpeed);
			setX(getX() + v.getX());
			setY(getY() + v.getY());
		} else {
			Vector v = calculateMoveVector(SHIP_TOP_SPEED);
			setX(v.getX());
			setY(v.getY());
		}
	}
	
	/**
	 * Get the current speed of the ship.
	 *
	 * @return The current speed of the ship
	 */
	private float getCurrentSpeed() {
		return (float) Math.sqrt(Math.pow(getX(), 2.0) + Math.pow(getY(), 2.0));
	}

	/**
	 * Update friction effect on the ship.
	 *
	 * @param nF The current speed
	 * @return The speed after friction effect
	 */
	private float friction(float nF) {
		if (Math.abs(nF) < friction) {
			nF = 0;
		} else {
			nF = nF * (1.0f - friction);
		}
		return nF;
	}

	/**
	 * Destroy the player ship and unregister all observers.
	 */
	public void destroy() {
		Fire.stopObserving(this);
		ForwardThrust.stopObserving(this);
		ReverseThrust.stopObserving(this);
		Left.stopObserving(this);
		Right.stopObserving(this);
		Stop.stopObserving(this);
		HyperSpace.stopObserving(this);
		Shield.stopObserving(this);
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IFireObservable#fire()
	 */
	@Override
	public void fire() {
		SoundManager.getSoundManager().playFire();
		
		Vector v = calculateMoveVector(shotSpeed);

		iShots.add(new Shot(getMidpointX1X2() - SHOT_DIAMETER, getMidpointY1Y2() - SHOT_DIAMETER,
				getMidpointX1X2() + SHOT_DIAMETER, getMidpointY1Y2() + SHOT_DIAMETER, v.getX(), v.getY(),
				getRotation()));
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IForwardThrustObservable#forwardThrust()
	 */
	@Override
	public void forwardThrust() {
		SoundManager.getSoundManager().playForwardThrust();
		
		move(forwardThrust);
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IReverseThrustObservable#reverseThrust()
	 */
	@Override
	public void reverseThrust() {
		SoundManager.getSoundManager().playReverseThrust();
		
		move(-reverseThrust);
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.ILeftObservable#left()
	 */
	@Override
	public void left() {
		setRotation(-leftRight - (getRotation() / 60.0f));
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IRightObservable#right()
	 */
	@Override
	public void right() {
		setRotation(leftRight - (getRotation() / 60.0f));
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IStopObservable#stop()
	 */
	@Override
	public void stop() {
		setX(0.0f);
		setY(0.0f);
	}

	/* (non-Javadoc)
	 * @see main.se450.model.ShapeDroid#update()
	 */
	@Override
	public void update() {
		setX(friction(getX()));
		setY(friction(getY()));
		super.update();
		setRotation(0.0f);
		
		if (shield != null) {
			shield.setFrame(getMinX(), getMinY());
		}
	}

	/* (non-Javadoc)
	 * @see main.se450.model.ShapeDroid#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
//		System.out.println(this.getMaxX() + "      " + this.getMaxY());
		
		if (shield != null) {
			shield.draw(graphics);
		}
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IHyperSpaceObservable#hyperSpace()
	 */
	@Override
	public void hyperSpace() {
		translateXY(-getMinX() + (float) Math.random() * (DisplayManager.getDisplayManager().getWidth() - getWidth()),
				-getMinY() + (float) Math.random() * (DisplayManager.getDisplayManager().getHeight() - getHeight()));
	}

	/* (non-Javadoc)
	 * @see main.se450.interfaces.IShieldObservable#shield()
	 */
	@Override
	public void shield() {
		if (InformationManager.getInformationManager().getShields() > 0 && !shieldOn) {
			shieldOn = true;
			shield = new ShieldCircle(getMinX(), getMinY(), getMaxX(), getMaxY(), getX(), getY());
			DebrisList.getDebrisList().addDebris(shield);
			InformationManager.getInformationManager().decreaseShields();
		}
	}
	
	/**
	 * Turn off the protective shield.
	 */
	public void turnOffShield() {
		shieldOn = false;
		shield = null;
	}
	
	/**
	 * Checks if the protective shield is on.
	 *
	 * @return true, if the protective shield is on
	 */
	public boolean isShieldOn() {
		return shieldOn;
	}

}
