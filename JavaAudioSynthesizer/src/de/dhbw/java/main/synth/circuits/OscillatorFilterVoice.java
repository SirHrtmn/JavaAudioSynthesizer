package de.dhbw.java.main.synth.circuits;

import com.jsyn.unitgen.UnitVoice;

import de.dhbw.java.main.synth.configuration.EnvelopeConfiguration;
import de.dhbw.java.main.synth.configuration.FilterConfiguration;

public interface OscillatorFilterVoice extends UnitVoice
{
	public void noteOn(double frequence, double amplitude);

	public void noteOff();

	public void setFilterConfiguration(FilterConfiguration filterConfig);

	public void setEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig);
}
