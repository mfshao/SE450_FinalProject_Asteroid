package main.se450.interfaces;

/**
 * The Interface IStopObservable represents a stop event observer.
 */
public interface IStopObservable extends IObservable{
	
	/**
	 * Execute a stop event.
	 */
	void stop();
}
