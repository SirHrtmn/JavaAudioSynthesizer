package synthesis.jsyn.synthesizers;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class SinusSynthesizer extends JSynSynthesizer
{
	private static final OscillatorType TYPE = OscillatorType.SINUS;

	static
	{
		JSynSynthesizer.registerJSynSynthesizer(TYPE,
				(filterConfig, envConfig) -> new SinusSynthesizer(filterConfig, envConfig));
	}

	private SinusSynthesizer(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super(filterConfig, envConfig);
	}

	@Override
	public OscillatorType getOscillatorType()
	{
		return TYPE;
	}

	@Override
	protected UnitOscillator getOscillator()
	{
		return new SineOscillator();
	}

}
