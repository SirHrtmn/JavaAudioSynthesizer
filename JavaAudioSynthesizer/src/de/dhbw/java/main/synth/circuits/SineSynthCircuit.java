package de.dhbw.java.main.synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import de.dhbw.java.main.synth.configuration.EnvelopeConfiguration;
import de.dhbw.java.main.synth.configuration.FilterConfiguration;

public class SineSynthCircuit extends Circuit implements OscillatorFilterVoice
{
	private UnitOscillator oscillator;
	private UnitFilter filter;
	private EnvelopeDAHDSR envelope;

	public SineSynthCircuit()
	{
		super();
		this.oscillator = new SineOscillator();
		this.filter = new FilterStateVariable();
		this.envelope = new EnvelopeDAHDSR();

		super.add(envelope);
		super.add(oscillator);
		super.add(filter);

		envelope.output.connect(oscillator.amplitude);
		oscillator.output.connect(filter.input);
	}

	@Override
	public UnitOutputPort getOutput()
	{
		return filter.output;
	}

	@Override
	public void noteOn(double frequency, double amplitude)
	{
		oscillator.frequency.set(frequency);

		envelope.input.set(1.0);
	}

	@Override
	public void noteOff()
	{
		envelope.input.set(0.0);
	}

	@Override
	public void setFilterConfiguration(FilterConfiguration filterConfig)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnvelopeCOnfiguration(EnvelopeConfiguration envelopeConfig)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp startTime)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noteOff(TimeStamp stopTime)
	{
		// TODO Auto-generated method stub
		
	}
}
