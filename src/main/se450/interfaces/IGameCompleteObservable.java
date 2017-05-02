package main.se450.interfaces;

/**
 * The Interface IGameCompleteObservable represents a game complete event observer.
 */
public interface IGameCompleteObservable extends IObservable {
	
	/**
	 * Execute a game complete event.
	 */
	void gameComplete();
}
