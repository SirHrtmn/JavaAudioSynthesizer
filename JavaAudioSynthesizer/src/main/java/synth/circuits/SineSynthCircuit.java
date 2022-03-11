package synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TunableFilter;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import synth.DefaultConstants;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.configuration.FilterConfiguration.FilterType;

public class SineSynthCircuit extends Circuit implements FilterEnvelopeVoice
{
	private UnitOscillator oscillator;
	private TunableFilter filter;
	private EnvelopeDAHDSR envelope;
	private FilterConfiguration filterConfiguration;
	private EnvelopeConfiguration envelopeConfiguration;

	public SineSynthCircuit()
	{
		this(DefaultConstants.getFilterConfig(),
				DefaultConstants.getEnvelopeConfig());
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

		this.filterConfiguration = filterConfiguration;
		this.envelopeConfiguration = envelopeConfiguration;
		applyEnvelopeConfiguration(envelopeConfiguration);
		applyFilterConfiguration(filterConfiguration);
	}

	@Override
	public UnitOutputPort getOutput()
	{
		return filter.getOutput();
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
	public void applyFilterConfiguration(FilterConfiguration newFilterConfig)
	{
		FilterType newFilterType = newFilterConfig.getFilterType();
		FilterType oldFilterType = this.filterConfiguration.getFilterType();
		
		if (!oldFilterType.equals(newFilterType))
		{
			replaceFilter(newFilterType.getNewFilter());
		}
		
		filter.frequency.set(newFilterConfig.getFrequency());
		this.filterConfiguration = newFilterConfig;
	}

	@Override
	public void applyEnvelopeConfiguration(EnvelopeConfiguration newEnvelopeConfig)
	{
		if (this.envelopeConfiguration.equals(newEnvelopeConfig))
		{
			return;
		}
		
		envelope.attack.set(newEnvelopeConfig.getAttack());
		envelope.decay.set(newEnvelopeConfig.getDecay());
		envelope.delay.set(newEnvelopeConfig.getDelay());
		envelope.hold.set(newEnvelopeConfig.getHold());
		envelope.release.set(newEnvelopeConfig.getRelease());
		
		this.envelopeConfiguration = newEnvelopeConfig;
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

	private void replaceFilter(TunableFilter newFilter)
	{
		oscillator.output.disconnect(filter.input);
		super.add(newFilter);
		filter = newFilter;
		oscillator.output.connect(filter.input);
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
