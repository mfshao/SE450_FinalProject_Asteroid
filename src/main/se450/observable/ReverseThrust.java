package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IReverseThrustObservable;

/**
 * The Class ReverseThrust represents the observer for the reverse thrust event.
 */
public class ReverseThrust extends AbstractObserver implements IObservable {

/** The reverse thrust. */
private static ReverseThrust reverseThrust = new ReverseThrust();
	
	/** The reverse thrust observables. */
	private ArrayList<IReverseThrustObservable> reverseThrustObservables = new ArrayList<>();
	
	/**
	 * Instantiates a new reverse thrust event observer.
	 */
	private ReverseThrust() {
		startObserver(this);
	}
	
	/**
	 * Start observing reverse thrust event.
	 *
	 * @param iReverseThrustObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IReverseThrustObservable iReverseThrustObservable) {
		reverseThrust.addObserver(iReverseThrustObservable);
	}
	
	/**
	 * Stop observing reverse thrust event.
	 *
	 * @param iReverseThrustObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IReverseThrustObservable iReverseThrustObservable) {
		reverseThrust.removeObserver(iReverseThrustObservable);
	}

	/**
	 * Trigger a reverse thrust event.
	 */
	public static void reverseThrust() {
		reverseThrust.observed();
	}
	
	/**
	 * Adds the observer.
	 *
	 * @param iReverseThrustObservable
	 *            A reverse thrust observer.
	 */
	private synchronized void addObserver(final IReverseThrustObservable iReverseThrustObservable) {
		if (iReverseThrustObservable != null) {
			if (!reverseThrustObservables.contains(iReverseThrustObservable)) {
				reverseThrustObservables.add(iReverseThrustObservable);
			}
		}
	}
	
	/**
	 * Removes the observer.
	 *
	 * @param iReverseThrustObservable
	 *            A reverse thrust observer.
	 */
	public synchronized void removeObserver(final IReverseThrustObservable iReverseThrustObservable) {
		reverseThrustObservables.remove(iReverseThrustObservable);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (reverseThrustObservables != null) {
			Iterator<IReverseThrustObservable> iiReverseThrustObservables = reverseThrustObservables.iterator();
			while (iiReverseThrustObservables.hasNext()) {
				IReverseThrustObservable iReverseThrustObservable = iiReverseThrustObservables.next();
				if (iReverseThrustObservable != null) {
					iReverseThrustObservable.reverseThrust();;
				}
			}
		}
	}

}
