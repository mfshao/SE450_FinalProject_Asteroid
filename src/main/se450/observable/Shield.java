package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShieldObservable;

/**
 * The Class Shield represents the observer for the shield event.
 */
public class Shield extends AbstractObserver implements IObservable {

	/** The shield. */
	private static Shield shield = new Shield();

	/** The shield observables. */
	private ArrayList<IShieldObservable> shieldObservables = new ArrayList<>();

	/**
	 * Instantiates a new shield event observer.
	 */
	private Shield() {
		startObserver(this);
	}

	/**
	 * Start observing shield event.
	 *
	 * @param iShieldObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IShieldObservable iShieldObservable) {
		shield.addObserver(iShieldObservable);
	}

	/**
	 * Stop observing shield event.
	 *
	 * @param iShieldObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IShieldObservable iShieldObservable) {
		shield.removeObserver(iShieldObservable);
	}

	/**
	 * Trigger a shield event.
	 */
	public static void shield() {
		shield.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iShieldObservable
	 *            A shield observer.
	 */
	private synchronized void addObserver(final IShieldObservable iShieldObservable) {
		if (iShieldObservable != null) {
			if (!shieldObservables.contains(iShieldObservable)) {
				shieldObservables.add(iShieldObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iShieldObservable
	 *            A shield observer.
	 */
	public synchronized void removeObserver(final IShieldObservable iShieldObservable) {
		shieldObservables.remove(iShieldObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (shieldObservables != null) {
			Iterator<IShieldObservable> iiShieldObservables = shieldObservables.iterator();
			while (iiShieldObservables.hasNext()) {
				IShieldObservable iShieldObservable = iiShieldObservables.next();
				if (iShieldObservable != null) {
					iShieldObservable.shield();
				}
			}
		}
	}

}
