package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class Right represents the observer for the right event.
 */
public class Right extends AbstractObserver implements IObservable {

	/** The right. */
	private static Right right = new Right();
	
	/** The right observables. */
	private ArrayList<IRightObservable> rightObservables = new ArrayList<>();
	
	/**
	 * Instantiates a new right event observer.
	 */
	private Right() {
		startObserver(this);
	}
	
	/**
	 * Start observing right event.
	 *
	 * @param iRightObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IRightObservable iRightObservable) {
		right.addObserver(iRightObservable);
	}
	
	/**
	 * Stop observing right event.
	 *
	 * @param iRightObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IRightObservable iRightObservable) {
		right.removeObserver(iRightObservable);
	}

	/**
	 * Trigger a right event.
	 */
	public static void right() {
		right.observed();
	}
	
	/**
	 * Adds the observer.
	 *
	 * @param iRightObservable
	 *            A right observer.
	 */
	private synchronized void addObserver(final IRightObservable iRightObservable) {
		if (iRightObservable != null) {
			if (!rightObservables.contains(iRightObservable)) {
				rightObservables.add(iRightObservable);
			}
		}
	}
	
	/**
	 * Removes the observer.
	 *
	 * @param iRightObservable
	 *            A right observer.
	 */
	public synchronized void removeObserver(final IRightObservable iRightObservable) {
		rightObservables.remove(iRightObservable);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (rightObservables != null) {
			Iterator<IRightObservable> iiRightObservables = rightObservables.iterator();
			while (iiRightObservables.hasNext()) {
				IRightObservable iRightObservable = iiRightObservables.next();
				if (iRightObservable != null) {
					iRightObservable.right();
				}
			}
		}
	}

}
