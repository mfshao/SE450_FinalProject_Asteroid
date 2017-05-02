package main.se450.observable;

/*
 * Name     : Mingfei Shao
 * Depaul#  : 1807687
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class Motion
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import main.se450.factories.NamedThreadFactory;
import main.se450.interfaces.IObservable;
import main.se450.singletons.ConfigurationManager;

/**
 * The Class Motion observer observes all motion events.
 */
public class Motion implements Runnable
{
	
	/** The motion. */
	private static Motion motion = new Motion();
	
	/** The in motion. */
	private boolean inMotion = false;
	
	/** The scheduler. */
	private ScheduledThreadPoolExecutor scheduler = null;

	/** The Constant FRAMES_PER_SECOND. */
	private final static int FRAMES_PER_SECOND = ConfigurationManager.getConfigurationManager().getConfiguration().getFramesPerSecond(); 
	
	/** The Constant NANO_SECONDS_PER_SECOND. */
	private final static int NANO_SECONDS_PER_SECOND = 1000000000;
			
	/** The observables. */
	private ArrayList<IObservable> observables  = new ArrayList<IObservable>();
	
	/**
	 * Instantiates a new motion object.
	 */
	private Motion()
	{
	}

	/**
	 * Get a reference to the motion object.
	 *
	 * @return the motion
	 */
	private static Motion getMotion()
	{
		return motion;
	}
		
	/**
	 * Start observing.
	 *
	 * @param iObservable An observer
	 */
	public static void startObserving(final IObservable iObservable)
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.addObserver(iObservable);
	}
	
	/**
	 * Adds the observer.
	 *
	 * @param iObservable An observer
	 */
	private synchronized void addObserver(final IObservable iObservable)
	{
		if (iObservable != null)
		{
			if (!isObserving(iObservable))
			{
				observables.add(iObservable);
		
				if (!getIsInMotion())
					startMotion();
			}
		}
	}
	
	/**
	 * Stop observing.
	 *
	 * @param iObservable An observer
	 */
	public static void stopObserving(final IObservable iObservable)
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.removeObserver(iObservable);
	}

	/**
	 * Removes the observer.
	 *
	 * @param iObservable An observer
	 */
	private synchronized void removeObserver(final IObservable iObservable)
	{
		observables.remove(iObservable);

		if (observables.isEmpty())
			stopMotion();
	}
	
	/**
	 * Toggle observing.
	 */
	public static void toggleObserving()
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.toggleMotion();
	}
	
	/**
	 * Toggle motion.
	 */
	private synchronized void toggleMotion()
	{
		if (getIsInMotion())
		{
			stopMotion();
		}
		else
		{
			startMotion();
		}
	}
	
	/**
	 * Checks if is observing an event.
	 *
	 * @param iObservable An observer
	 * @return true, if is observing
	 */
	private synchronized boolean isObserving(final IObservable iObservable)
	{
		boolean bIsObserving = false;
		
		if (iObservable != null)
		{
			bIsObserving = observables.contains(iObservable);
		}
		
		return bIsObserving;
	}
	
	/**
	 * Start motion.
	 */
	private synchronized void startMotion()
	{
		setIsInMotion(true);
		
		if (FRAMES_PER_SECOND > 0)
		{
			if (scheduler == null)
			{
				NamedThreadFactory lectureFourThreadFactory = new NamedThreadFactory("Motion");
				
				scheduler = new ScheduledThreadPoolExecutor(1, lectureFourThreadFactory);
				if (scheduler != null)
					scheduler.scheduleAtFixedRate(this, 0, NANO_SECONDS_PER_SECOND / FRAMES_PER_SECOND, TimeUnit.NANOSECONDS);//~20 frames per second
			}
		}
	}

	/**
	 * Stop motion.
	 */
	private synchronized void stopMotion()
	{
		setIsInMotion(false);
		
		if (scheduler != null)
		{
			scheduler.shutdown();
			
			scheduler = null;
		}
	}
	
	/**
	 * Checks if is in motion.
	 *
	 * @return true, if is in motion
	 */
	public static final boolean isInMotion()
	{
		Motion motion = getMotion();
		return (motion != null ? motion.getIsInMotion() : false);
	}

	/**
	 * Get the flag of is in motion.
	 *
	 * @return The flag of is in motion.
	 */
	public final synchronized boolean getIsInMotion()
	{
		return inMotion;
	}
	
	/**
	 * Set the flag of is in motion.
	 *
	 * @param bIsInMotion The new flag of is in motion.
	 */
	private final synchronized void setIsInMotion(final boolean bIsInMotion)
	{
		inMotion = bIsInMotion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run()
	{
		if ((observables != null) && (isInMotion()))
		{
			Iterator<IObservable> iiObservables = observables.iterator();
			while (iiObservables.hasNext())
			{
				IObservable iObservable = iiObservables.next();
				if (iObservable != null)
				{
					iObservable.update();
				}
			}
		}
	}
}
