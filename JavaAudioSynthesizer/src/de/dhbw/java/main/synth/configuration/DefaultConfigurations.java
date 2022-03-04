package de.dhbw.java.main.synth.configuration;

public class DefaultConfigurations
{	
	public static FilterConfiguration getFilterConfig()
	{
		return new FilterConfiguration(1.0, 1.0, 1.0);
	}
	
	public static EnvelopeConfiguration getEnvelopeConfig()
	{
		return new EnvelopeConfiguration(0.0, 0.2, 1.0, 1.0, 0.2);
	}
}
