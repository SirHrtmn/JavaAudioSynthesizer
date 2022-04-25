package synthesis.jsyn.configuration;

import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;

import configuration.EnvelopeConfiguration;
import configuration.FilterConfiguration;

public class ConfigurationHelper
{
	private ConfigurationHelper()
	{
		// Hide public constructor
	}

	public static void applyForEnvelope(EnvelopeDAHDSR envelope, EnvelopeConfiguration envConfig)
	{
		envelope.attack.set(envConfig.getAttack());
		envelope.decay.set(envConfig.getDecay());
		envelope.delay.set(envConfig.getDelay());
		envelope.hold.set(envConfig.getHold());
		envelope.release.set(envConfig.getRelease());
	}

	public static void applyForFilter(FilterLowPass lowPass, FilterBandPass bandPass,
			FilterHighPass highPass, FilterConfiguration filterConfig)
	{
		lowPass.frequency.set(filterConfig.getLowPass());
		bandPass.frequency.set(filterConfig.getBandPass());
		highPass.frequency.set(filterConfig.getHighPass());

		lowPass.amplitude.set(filterConfig.getAmplitude());
		bandPass.amplitude.set(filterConfig.getAmplitude());
		highPass.amplitude.set(filterConfig.getAmplitude());
	}
}
