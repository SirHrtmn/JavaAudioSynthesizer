package synthesis.jsyn.synthesizers;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public interface JSynBuilder
{
	public JSynSynthesizer build(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig);
}
