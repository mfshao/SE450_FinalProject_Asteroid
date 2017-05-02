package main.se450.interfaces;

/**
 * The Interface IForwardThrustObservable represents a forward thrust event observer.
 */
public interface IForwardThrustObservable extends IObservable {
	
	/**
	 * Execute a forward thrust.
	 */
	void forwardThrust();
}
