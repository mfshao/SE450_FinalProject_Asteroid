package main.se450.interfaces;

/**
 * The Interface ILeftObservable represents a left event observer.
 */
public interface ILeftObservable extends IObservable {
	
	/**
	 * Execute a left event.
	 */
	void left();
}
