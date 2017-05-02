package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartObservable;

/**
 * The Class Start represents the observer for the start event.
 */
public class Start extends AbstractObserver implements IObservable {

	/** The start. */
	private static Start start = new Start();

	/** The start observables. */
	private ArrayList<IStartObservable> startObservables = new ArrayList<>();

	/**
	 * Instantiates a new start event observer.
	 */
	private Start() {
		startObserver(this);
	}

	/**
	 * Start observing start event.
	 *
	 * @param iStartObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IStartObservable iStartObservable) {
		start.addObserver(iStartObservable);
	}

	/**
	 * Stop observing start event.
	 *
	 * @param iStartObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IStartObservable iStartObservable) {
		start.removeObserver(iStartObservable);
	}

	/**
	 * Trigger a start event.
	 */
	public static void start() {
		start.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iStartObservable
	 *            A start observer.
	 */
	private synchronized void addObserver(final IStartObservable iStartObservable) {
		if (iStartObservable != null) {
			if (!startObservables.contains(iStartObservable)) {
				startObservables.add(iStartObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iStartObservable
	 *            A start observer.
	 */
	public synchronized void removeObserver(final IStartObservable iStartObservable) {
		startObservables.remove(iStartObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (startObservables != null) {
			Iterator<IStartObservable> iiStartObservables = startObservables.iterator();
			while (iiStartObservables.hasNext()) {
				IStartObservable iStartObservable = iiStartObservables.next();
				if (iStartObservable != null) {
					iStartObservable.start();
				}
			}
		}
	}

}
