package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class ForwardThrust represents the observer for the forward thrust event.
 */
public class ForwardThrust extends AbstractObserver implements IObservable {

	/** The forward thrust. */
	private static ForwardThrust forwardThrust = new ForwardThrust();

	/** The forward thrust observables. */
	private ArrayList<IForwardThrustObservable> forwardThrustObservables = new ArrayList<>();

	/**
	 * Instantiates a new forward thrust event observer.
	 */
	private ForwardThrust() {
		startObserver(this);
	}

	/**
	 * Start observing forward thrust event.
	 *
	 * @param iForwardThrustObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IForwardThrustObservable iForwardThrustObservable) {
		forwardThrust.addObserver(iForwardThrustObservable);
	}

	/**
	 * Stop observing forward thrust event.
	 *
	 * @param iForwardThrustObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IForwardThrustObservable iForwardThrustObservable) {
		forwardThrust.removeObserver(iForwardThrustObservable);
	}

	/**
	 * Trigger a forward thrust event.
	 */
	public static void forwardThrust() {
		forwardThrust.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iForwardThrustObservable
	 *            A forward thrust observer.
	 */
	private synchronized void addObserver(final IForwardThrustObservable iForwardThrustObservable) {
		if (iForwardThrustObservable != null) {
			if (!forwardThrustObservables.contains(iForwardThrustObservable)) {
				forwardThrustObservables.add(iForwardThrustObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iForwardThrustObservable
	 *            A forward thrust observer.
	 */
	public synchronized void removeObserver(final IForwardThrustObservable iForwardThrustObservable) {
		forwardThrustObservables.remove(iForwardThrustObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (forwardThrustObservables != null) {
			Iterator<IForwardThrustObservable> iiForwardThrustObservables = forwardThrustObservables.iterator();
			while (iiForwardThrustObservables.hasNext()) {
				IForwardThrustObservable iForwardThrustObservable = iiForwardThrustObservables.next();
				if (iForwardThrustObservable != null) {
					iForwardThrustObservable.forwardThrust();
					;
				}
			}
		}
	}

}
