package main.se450.interfaces;

/**
 * The Interface IReverseThrustObservable represents a reverse thrust event observer.
 */
public interface IReverseThrustObservable extends IObservable {
	
	/**
	 * Execute a reverse thrust event.
	 */
	void reverseThrust();
}
