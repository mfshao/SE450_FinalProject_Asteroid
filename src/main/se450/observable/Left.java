package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.ILeftObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class Left represents the observer for the left event.
 */
public class Left extends AbstractObserver implements IObservable {

	/** The left. */
	private static Left left = new Left();

	/** The left observables. */
	private ArrayList<ILeftObservable> leftObservables = new ArrayList<>();

	/**
	 * Instantiates a new left event observer.
	 */
	private Left() {
		startObserver(this);
	}

	/**
	 * Start observing left event.
	 *
	 * @param iLeftObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final ILeftObservable iLeftObservable) {
		left.addObserver(iLeftObservable);
	}

	/**
	 * Stop observing left event.
	 *
	 * @param iLeftObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final ILeftObservable iLeftObservable) {
		left.removeObserver(iLeftObservable);
	}

	/**
	 * Trigger a left event.
	 */
	public static void left() {
		left.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iLeftObservable
	 *            A left observer.
	 */
	private synchronized void addObserver(final ILeftObservable iLeftObservable) {
		if (iLeftObservable != null) {
			if (!leftObservables.contains(iLeftObservable)) {
				leftObservables.add(iLeftObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iLeftObservable
	 *            A left observer.
	 */
	public synchronized void removeObserver(final ILeftObservable iLeftObservable) {
		leftObservables.remove(iLeftObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (leftObservables != null) {
			Iterator<ILeftObservable> iiLeftObservables = leftObservables.iterator();
			while (iiLeftObservables.hasNext()) {
				ILeftObservable iLeftObservable = iiLeftObservables.next();
				if (iLeftObservable != null) {
					iLeftObservable.left();
				}
			}
		}
	}

}
