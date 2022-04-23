package synthesis.jsyn.synthesizers;

import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class SawtoothSynthesizer extends JSynSynthesizer
{
	public SawtoothSynthesizer(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super(filterConfig, envConfig);
	}

	@Override
	public OscillatorType getOscillatorType()
	{
		return OscillatorType.SAWTOOTH;
	}

	@Override
	protected UnitOscillator getOscillator()
	{
		return new SawtoothOscillator();
	}

}
