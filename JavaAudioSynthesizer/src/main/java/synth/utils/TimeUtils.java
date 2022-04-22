package synth.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.softsynth.shared.time.TimeStamp;

import exceptions.InvalidTimeFrameException;

public class TimeUtils
{
	private static final Logger LOGGER = Logger.getLogger("TimeUtils");

	private TimeUtils()
	{
		// Hide public constructor
	}

	public static void waitForASpecifiedTime(long timeToWait)
	{
		try
		{
			Thread.sleep(timeToWait);
		}
		catch (InterruptedException e)
		{
			LOGGER.log(Level.WARNING, "Interrupted sleeping of thread!", e);
			Thread.currentThread().interrupt();
		}
	}

	public static void waitUntilPointOfTime(TimeStamp currentTime, TimeStamp pointOfTime)
	{
		long timeToWait = (long) (pointOfTime.getTime() - currentTime.getTime());

		if (timeToWait > 0)
		{
			waitForASpecifiedTime(timeToWait);
		}

		String logMessage = constructInvalidTimeFrameLogMessage(currentTime, pointOfTime);
		LOGGER.log(Level.WARNING, logMessage);

		String message = "Given timestamps result to invalid time frame. Cannot wait till specified point of time, because it is in the past";
		throw new InvalidTimeFrameException(message);

	}

	private static String constructInvalidTimeFrameLogMessage(TimeStamp currentTime,
			TimeStamp pointOfTime)
	{
		String unformatted = "Calculated timeframe to wait is invalid. current time: %s - specified point of time: %s";
		return String.format(unformatted, currentTime.getTime(), pointOfTime.getTime());
	}
}
