package synthesis.jsyn.synthesizers;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class SinusSynthesizer extends JSynSynthesizer
{
	public SinusSynthesizer(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super(filterConfig, envConfig);
	}

	@Override
	public OscillatorType getOscillatorType()
	{
		return OscillatorType.SINUS;
	}

	@Override
	protected UnitOscillator getOscillator()
	{
		return new SineOscillator();
	}

}
