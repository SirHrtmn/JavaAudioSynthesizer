package synthesis;

import java.util.Optional;

import exceptions.SynthesizerNotFoundException;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.utils.DefaultConstants;
import synthesis.jsyn.synthesizers.JSynSynthesizer;

public class SynthesizerFactory
{
	private SynthesizerFactory()
	{
		// Hide the public constructor
	}

	public static ISynthesizer build(OscillatorType type)
	{
		FilterConfiguration filterConfig = DefaultConstants.getFilterConfig();
		EnvelopeConfiguration envConfig = DefaultConstants.getEnvelopeConfig();

		return build(type, filterConfig, envConfig);
	}

	public static ISynthesizer build(OscillatorType type, FilterConfiguration filterConfig,
			EnvelopeConfiguration envConfig)
	{
		Optional<ISynthesizer> optionalSynth = getSynthesizer(type, filterConfig, envConfig);
		if (optionalSynth.isPresent())
		{
			return optionalSynth.get();
		}

		String message = String.format("Could not find synthesizer for %s", type.toString());
		throw new SynthesizerNotFoundException(message);
	}

	private static Optional<ISynthesizer> getSynthesizer(OscillatorType type,
			FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		return JSynSynthesizer.getSynthesizer(type, filterConfig, envConfig);
	}
}
