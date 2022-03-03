package de.dhbw.java.main.synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

public class SineSynthCircuit extends Circuit implements UnitVoice
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
	public void noteOff(TimeStamp stopTime)
	{
		envelope.input.set(0.0);
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp startTime)
	{
		oscillator.frequency.set(frequency);
		
		envelope.input.set(1.0);
	}
}
