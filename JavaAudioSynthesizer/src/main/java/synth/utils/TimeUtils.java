package synth.utils;

import com.softsynth.shared.time.TimeStamp;

public class TimeUtils
{
	public static void waitForASpecifiedTime(long timeToWait)
	{
		try
		{
			Thread.sleep(timeToWait);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void waitUntilPointOfTime(double currentTime, TimeStamp pointOfTime)
	{
		long timeToWait = (long) (pointOfTime.getTime() - currentTime);

		if (timeToWait > 0)
		{
			waitForASpecifiedTime(timeToWait);
		}
	}
}
