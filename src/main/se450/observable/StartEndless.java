package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartEndlessObservable;

/**
 * The Class StartEndless represents the observer for the start endless event.
 */
public class StartEndless extends AbstractObserver implements IObservable {

	/** The start endless. */
	private static StartEndless startEndless = new StartEndless();

	/** The start endless observables. */
	private ArrayList<IStartEndlessObservable> startEndlessObservables = new ArrayList<>();

	/**
	 * Instantiates a new start endless event observer.
	 */
	private StartEndless() {
		startObserver(this);
	}

	/**
	 * Start observing start endless event.
	 *
	 * @param iStartEndlessObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IStartEndlessObservable iStartEndlessObservable) {
		startEndless.addObserver(iStartEndlessObservable);
	}

	/**
	 * Stop observing start endless event.
	 *
	 * @param iStartEndlessObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IStartEndlessObservable iStartEndlessObservable) {
		startEndless.removeObserver(iStartEndlessObservable);
	}

	/**
	 * Trigger a start endless event.
	 */
	public static void startEndless() {
		startEndless.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iStartEndlessObservable
	 *            A start endless observer.
	 */
	private synchronized void addObserver(final IStartEndlessObservable iStartEndlessObservable) {
		if (iStartEndlessObservable != null) {
			if (!startEndlessObservables.contains(iStartEndlessObservable)) {
				startEndlessObservables.add(iStartEndlessObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iStartEndlessObservable
	 *            A start endless observer.
	 */
	public synchronized void removeObserver(final IStartEndlessObservable iStartEndlessObservable) {
		startEndlessObservables.remove(iStartEndlessObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (startEndlessObservables != null) {
			Iterator<IStartEndlessObservable> iiStartEndlessObservables = startEndlessObservables.iterator();
			while (iiStartEndlessObservables.hasNext()) {
				IStartEndlessObservable iStartEndlessObservable = iiStartEndlessObservables.next();
				if (iStartEndlessObservable != null) {
					iStartEndlessObservable.startEndless();
				}
			}
		}
	}
}
