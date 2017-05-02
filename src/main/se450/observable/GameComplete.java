package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IGameCompleteObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class GameComplete represents the observer for the game complete event.
 */
public class GameComplete extends AbstractObserver implements IObservable {

	/** The game complete. */
	private static GameComplete gameComplete = new GameComplete();

	/** The game complete observables. */
	private ArrayList<IGameCompleteObservable> gameCompleteObservables = new ArrayList<>();

	/**
	 * Instantiates a new game complete event observer.
	 */
	private GameComplete() {
		startObserver(this);
	}

	/**
	 * Start observing game complete event.
	 *
	 * @param iGameCompleteObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IGameCompleteObservable iGameCompleteObservable) {
		gameComplete.addObserver(iGameCompleteObservable);
	}

	/**
	 * Stop observing game complete event.
	 *
	 * @param iGameCompleteObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IGameCompleteObservable iGameCompleteObservable) {
		gameComplete.removeObserver(iGameCompleteObservable);
	}

	/**
	 * Trigger a game complete event.
	 */
	public static void gameComplete() {
		gameComplete.observed();
	}

	/**
	 * Adds the observer.
	 *
	 * @param iGameCompleteObservable
	 *            A game complete observer.
	 */
	private synchronized void addObserver(final IGameCompleteObservable iGameCompleteObservable) {
		if (iGameCompleteObservable != null) {
			if (!gameCompleteObservables.contains(iGameCompleteObservable)) {
				gameCompleteObservables.add(iGameCompleteObservable);
			}
		}
	}

	/**
	 * Removes the observer.
	 *
	 * @param iGameCompleteObservable
	 *            A game complete observer.
	 */
	public synchronized void removeObserver(final IGameCompleteObservable iGameCompleteObservable) {
		gameCompleteObservables.remove(iGameCompleteObservable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (gameCompleteObservables != null) {
			Iterator<IGameCompleteObservable> iiGameCompleteObservables = gameCompleteObservables.iterator();
			while (iiGameCompleteObservables.hasNext()) {
				IGameCompleteObservable iGameCompleteObservable = iiGameCompleteObservables.next();
				if (iGameCompleteObservable != null) {
					iGameCompleteObservable.gameComplete();
				}
			}
		}
	}

}
