package de.dhbw.java.main.synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import de.dhbw.java.main.synth.configuration.DefaultConfigurations;
import de.dhbw.java.main.synth.configuration.EnvelopeConfiguration;
import de.dhbw.java.main.synth.configuration.FilterConfiguration;

public class SineSynthCircuit extends Circuit implements FilterEnvelopeVoice
{
	private UnitOscillator oscillator;
	private FilterStateVariable filter;
	private EnvelopeDAHDSR envelope;
	private FilterConfiguration filterConfiguration;
	private EnvelopeConfiguration envelopeConfiguration;

	public SineSynthCircuit()
	{
		this(DefaultConfigurations.getFilterConfig(),
				DefaultConfigurations.getEnvelopeConfig());
	}

	public SineSynthCircuit(FilterConfiguration filterConfiguration,
			EnvelopeConfiguration envelopeConfiguration)
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

		applyEnvelopeConfiguration(envelopeConfiguration);
		applyFilterConfiguration(filterConfiguration);
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
	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		this.filterConfiguration = filterConfig;
	}

	@Override
	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		this.envelopeConfiguration = envelopeConfig;

	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp startTime)
	{
		oscillator.frequency.set(frequency);
		oscillator.amplitude.set(amplitude);

		waitUntilPointOfTime(startTime);

		envelope.amplitude.set(amplitude);
	}

	@Override
	public void noteOff(TimeStamp stopTime)
	{
		waitUntilPointOfTime(stopTime);

		envelope.amplitude.set(0.0);
	}

	private void waitForASpecifiedTime(long timeToWait)
	{
		try
		{
			Thread.sleep(timeToWait);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private void waitUntilPointOfTime(TimeStamp pointOfTime)
	{
		double currentTime = oscillator.getSynthesizer().getCurrentTime();
		long timeToWait = (long) (pointOfTime.getTime() - currentTime);

		if (timeToWait > 0)
		{
			waitForASpecifiedTime(timeToWait);
		}
	}
}
