package synth.circuits;

import com.jsyn.unitgen.UnitVoice;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public interface FilterEnvelopeVoice extends UnitVoice
{
	public void noteOn(double frequence, double amplitude);

	public void noteOff();

	public void applyFilterConfiguration(FilterConfiguration filterConfig);

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig);
}
