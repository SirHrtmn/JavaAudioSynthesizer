package de.dhbw.java.main.synth.circuits;

import com.jsyn.unitgen.UnitVoice;

import de.dhbw.java.main.synth.configuration.EnvelopeConfiguration;
import de.dhbw.java.main.synth.configuration.FilterConfiguration;

public interface FilterEnvelopeVoice extends UnitVoice
{
	public void noteOn(double frequence, double amplitude);

	public void noteOff();

	public void applyFilterConfiguration(FilterConfiguration filterConfig);

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig);
}
