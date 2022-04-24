package synthesis.jsyn.synthesizers;

import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class TriangleSynthesizer extends JSynSynthesizer
{
	static
	{
		JSynSynthesizer.registerJSynSynthesizer(OscillatorType.TRIANGLE,
				(filterConfig, envConfig) -> new TriangleSynthesizer(filterConfig, envConfig));
	}

	private TriangleSynthesizer(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super(filterConfig, envConfig);
	}

	@Override
	public OscillatorType getOscillatorType()
	{
		return OscillatorType.TRIANGLE;
	}

	@Override
	protected UnitOscillator getOscillator()
	{
		return new TriangleOscillator();
	}

}
