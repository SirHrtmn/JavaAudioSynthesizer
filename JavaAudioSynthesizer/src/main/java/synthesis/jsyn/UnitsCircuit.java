package synthesis.jsyn;

import com.jsyn.ports.UnitOutputPort;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public interface UnitsCircuit
{
	public UnitOutputPort getOutput();

	public void noteOn(double frequency);

	public void noteOff();

	public void applyFilterConfiguration(FilterConfiguration filterConfig);

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig);
}
