package main.se450.interfaces;

/**
 * The Interface IShieldObservable represents a shield event observer.
 */
public interface IShieldObservable extends IObservable {
	
	/**
	 * Execute a shield event.
	 */
	void shield();
}
