package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class Fire represents the observer for the fire event.
 */
public class Fire extends AbstractObserver implements IObservable {

	/** The fire. */
	private static Fire fire = new Fire();

	/** The fire observables. */
	private ArrayList<IFireObservable> fireObservables = new ArrayList<>();

	/**
	 * Instantiates a new fire event observer.
	 */
	private Fire() {
		startObserver(this);
	}

	/**
	 * Start observing fire event.
	 *
	 * @param iFireObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IFireObservable iFireObservable) {
		fire.addObserver(iFireObservable);
	}

	/**
	 * Stop observing fire event.
	 *
	 * @param iFireObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IFireObservable iFireObservable) {
		fire.removeObserver(iFireObservable);
	}

	/**
	 * Trigger a fire event.
	 */
	public static void fire() {
		fire.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iFireObservable
	 *            A fire observer.
	 */
	private synchronized void addObserver(final IFireObservable iFireObservable) {
		if (iFireObservable != null) {
			if (!fireObservables.contains(iFireObservable)) {
				fireObservables.add(iFireObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iFireObservable
	 *            A fire observer.
	 */
	public synchronized void removeObserver(final IFireObservable iFireObservable) {
		fireObservables.remove(iFireObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (fireObservables != null) {
			Iterator<IFireObservable> iiFireObservables = fireObservables.iterator();
			while (iiFireObservables.hasNext()) {
				IFireObservable iFireObservable = iiFireObservables.next();
				if (iFireObservable != null) {
					iFireObservable.fire();
				}
			}
		}
	}

}
