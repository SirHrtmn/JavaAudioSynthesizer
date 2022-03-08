package synth;

import synth.circuits.FilterEnvelopeVoice;
import synth.circuits.VoiceCircuits;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public class DefaultConstants
{	
	public static FilterConfiguration getFilterConfig()
	{
		return new FilterConfiguration(1.0, 1.0, 1.0);
	}
	
	public static EnvelopeConfiguration getEnvelopeConfig()
	{
		return new EnvelopeConfiguration(0.0, 0.2, 1.0, 1.0, 0.2);
	}
	
	public static FilterEnvelopeVoice getVoice()
	{
		return VoiceCircuits.SinusVoice.getUnit();
	}
}
