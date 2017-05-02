package main.se450.interfaces;

/**
 * The Interface IFireObservable represents a fire event observer.
 */
public interface IFireObservable extends IObservable {
	
	/**
	 * Execute a fire event.
	 */
	void fire();
}
