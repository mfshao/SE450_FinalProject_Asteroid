package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IGameOverObservable;
import main.se450.interfaces.IObservable;

/**
 * The Class GameOver represents the observer for the game over event.
 */
public class GameOver extends AbstractObserver implements IObservable {

	/** The game over. */
	private static GameOver gameOver = new GameOver();
	
	/** The game over observables. */
	private ArrayList<IGameOverObservable> gameOverObservables = new ArrayList<>();
	
	/**
	 * Instantiates a new game over event observer.
	 */
	private GameOver() {
		startObserver(this);
	}
	
	/**
	 * Start observing game over event.
	 *
	 * @param iGameOverObservable
	 *            The object to be observed.
	 */
	public static void startObserving(final IGameOverObservable iGameOverObservable) {
		gameOver.addObserver(iGameOverObservable);
	}
	
	/**
	 * Stop observing game over event.
	 *
	 * @param iGameOverObservable
	 *            The object being observed.
	 */
	public static void stopObserving(final IGameOverObservable iGameOverObservable) {
		gameOver.removeObserver(iGameOverObservable);
	}

	/**
	 * Trigger a game over event.
	 */
	public static void gameOver() {
		gameOver.observed();
	}
	
	/**
	 * Adds the observer.
	 *
	 * @param iGameOverObservable
	 *            A game over observer.
	 */
	private synchronized void addObserver(final IGameOverObservable iGameOverObservable) {
		if (iGameOverObservable != null) {
			if (!gameOverObservables.contains(iGameOverObservable)) {
				gameOverObservables.add(iGameOverObservable);
			}
		}
	}
	
	/**
	 * Removes the observer.
	 *
	 * @param iGameOverObservable
	 *            A game over observer.
	 */
	public synchronized void removeObserver(final IGameOverObservable iGameOverObservable) {
		gameOverObservables.remove(iGameOverObservable);
	}
	
	/* (non-Javadoc)
	 * @see main.se450.interfaces.IObservable#update()
	 */
	@Override
	public synchronized void update() {
		if (gameOverObservables != null) {
			Iterator<IGameOverObservable> iiGameOverObservables = gameOverObservables.iterator();
			while (iiGameOverObservables.hasNext()) {
				IGameOverObservable iGameOverObservable = iiGameOverObservables.next();
				if (iGameOverObservable != null) {
					iGameOverObservable.gameOver();
				}
			}
		}
	}

}
