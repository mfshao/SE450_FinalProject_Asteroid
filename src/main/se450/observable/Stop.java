package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class Stop represents the observer for the stop event.
 */
public class Stop extends AbstractObserver implements IObservable {

	/** The stop. */
	private static Stop stop = new Stop();

	/** The stop observables. */
	private ArrayList<IStopObservable> stopObservables = new ArrayList<>();

	/**
	 * Instantiates a new stop event observer.
	 */
	private Stop() {
		startObserver(this);
	}

	/**
	 * Start observing stop event.
	 *
	 * @param iStopObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IStopObservable iStopObservable) {
		stop.addObserver(iStopObservable);
	}

	/**
	 * Stop observing stop event.
	 *
	 * @param iStopObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IStopObservable iStopObservable) {
		stop.removeObserver(iStopObservable);
	}

	/**
	 * Trigger a stop event.
	 */
	public static void stop() {
		stop.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iStopObservable
	 *            A stop observer.
	 */
	private synchronized void addObserver(final IStopObservable iStopObservable) {
		if (iStopObservable != null) {
			if (!stopObservables.contains(iStopObservable)) {
				stopObservables.add(iStopObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iStopObservable
	 *            A stop observer.
	 */
	public synchronized void removeObserver(final IStopObservable iStopObservable) {
		stopObservables.remove(iStopObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (stopObservables != null) {
			Iterator<IStopObservable> iiStopObservables = stopObservables.iterator();
			while (iiStopObservables.hasNext()) {
				IStopObservable iStopObservable = iiStopObservables.next();
				if (iStopObservable != null) {
					iStopObservable.stop();
				}
			}
		}
	}

}
