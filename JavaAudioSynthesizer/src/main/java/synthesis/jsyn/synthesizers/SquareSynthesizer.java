package synthesis.jsyn.synthesizers;

import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class SquareSynthesizer extends JSynSynthesizer
{
	private static final OscillatorType SQUARE = OscillatorType.SQUARE;

	static
	{
		JSynSynthesizer.registerJSynSynthesizer(SQUARE,
				(filterConfig, envConfig) -> new SquareSynthesizer(filterConfig, envConfig));
	}

	private SquareSynthesizer(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super(filterConfig, envConfig);
	}

	@Override
	public OscillatorType getOscillatorType()
	{
		return SQUARE;
	}

	@Override
	protected UnitOscillator getOscillator()
	{
		return new SquareOscillator();
	}

}
