package main.se450.observable;

import main.se450.interfaces.IObservable;

/**
 * An asynchronous update interface for receiving notifications about an event
 * has occurred.
 */
public abstract class AbstractObserver {

	/** The i observable. */
	private IObservable iObservable = null;

	/**
	 * The default constructor.
	 */
	public AbstractObserver() {
	}

	/**
	 * This method is called when information about an event which was
	 * previously requested using an asynchronous interface becomes available,
	 * and the observer will starts to observe.
	 *
	 * @param iiObservable
	 *            An observer
	 */
	public synchronized void startObserver(final IObservable iiObservable) {
		iObservable = iiObservable;
	}

	/**
	 * This method is called when information about an event which was
	 * previously requested using an asynchronous interface becomes available,
	 * and the observer will stops to observe.
	 *
	 * @param iiObservable
	 *            An observer
	 */
	public synchronized void stopObserver(final IObservable iiObservable) {
		if (isObserving(iiObservable)) {
			iObservable = null;
		}
	}

	/**
	 * To check if a observer is observing an event.
	 *
	 * @param iiObservable
	 *            An observer
	 * @return true, if a observer is observing an event
	 */
	public synchronized boolean isObserving(final IObservable iiObservable) {
		return (iObservable == iiObservable);
	}

	/**
	 * This method is called to update the observer.
	 */
	protected void observed() {
		if (iObservable != null) {
			iObservable.update();
		}
	}
}
