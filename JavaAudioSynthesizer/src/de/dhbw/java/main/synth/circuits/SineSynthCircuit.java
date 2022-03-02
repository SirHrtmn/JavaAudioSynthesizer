package de.dhbw.java.main.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
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

	public SineSynthCircuit()
	{
		super();
		this.oscillator = new SineOscillator();
		this.filter = new FilterStateVariable();

		super.add(oscillator);
		super.add(filter);

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
		// TODO Auto-generated method stub

	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp startTime)
	{
		// TODO Auto-generated method stub

	}
}
