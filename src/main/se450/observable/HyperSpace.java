package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class HyperSpace represents the observer for the hyper space event.
 */
public class HyperSpace extends AbstractObserver implements IObservable {
	
	/** The hyper space. */
	private static HyperSpace hyperSpace = new HyperSpace();
	
	/** The hyper space observables. */
	private ArrayList<IHyperSpaceObservable> hyperSpaceObservables = new ArrayList<>();
	
	/**
	 * Instantiates a new hyper space event observer.
	 */
	private HyperSpace() {
		startObserver(this);
	}
	
	/**
	 * Start observing hyper space event.
	 *
	 * @param iHyperSpaceObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IHyperSpaceObservable iHyperSpaceObservable) {
		hyperSpace.addObserver(iHyperSpaceObservable);
	}
	
	/**
	 * Stop observing hyper space event.
	 *
	 * @param iHyperSpaceObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IHyperSpaceObservable iHyperSpaceObservable) {
		hyperSpace.removeObserver(iHyperSpaceObservable);
	}

	/**
	 * Trigger a hyper space event.
	 */
	public static void hyperSpace() {
		hyperSpace.observed();
	}
	
	/**
	 * Adds the observer.
	 *
	 * @param iHyperSpaceObservable
	 *            A hyper space observer.
	 */
	private synchronized void addObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		if (iHyperSpaceObservable != null) {
			if (!hyperSpaceObservables.contains(iHyperSpaceObservable)) {
				hyperSpaceObservables.add(iHyperSpaceObservable);
			}
		}
	}
	
	/**
	 * Removes the observer.
	 *
	 * @param iHyperSpaceObservable
	 *            A hyper space observer.
	 */
	public synchronized void removeObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		hyperSpaceObservables.remove(iHyperSpaceObservable);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (hyperSpaceObservables != null) {
			Iterator<IHyperSpaceObservable> iiHyperSpaceObservables = hyperSpaceObservables.iterator();
			while (iiHyperSpaceObservables.hasNext()) {
				IHyperSpaceObservable iHyperSpaceObservable = iiHyperSpaceObservables.next();
				if (iHyperSpaceObservable != null) {
					iHyperSpaceObservable.hyperSpace();
				}
			}
		}
	}

}
