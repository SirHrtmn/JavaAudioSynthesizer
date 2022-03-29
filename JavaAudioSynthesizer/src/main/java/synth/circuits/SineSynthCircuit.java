package synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.utils.DefaultConstants;
import synth.utils.TimeUtils;

public class SineSynthCircuit extends Circuit implements FilterEnvelopeVoice
{
	private UnitOscillator oscillator;
	private EnvelopeDAHDSR envelope;
	private FilterBandPass bandPass;
	private FilterLowPass lowPass;
	private FilterHighPass highPass;
	private PassThrough passThrough;
	private PassThrough output;

	public SineSynthCircuit()
	{
		this(DefaultConstants.getFilterConfig(), DefaultConstants.getEnvelopeConfig());
	}

	public SineSynthCircuit(FilterConfiguration filterConfig, EnvelopeConfiguration envConfig)
	{
		super();

		initializeUnits();
		addUnits();
		connectUnits();

		applyFilterConfiguration(filterConfig);
		applyEnvelopeConfiguration(envConfig);
	}

	@Override
	public UnitOutputPort getOutput()
	{
		return output.getOutput();
	}

	@Override
	public void noteOn(double frequency, double amplitude)
	{
		oscillator.frequency.set(frequency);
		envelope.input.set(amplitude);
	}

	@Override
	public void noteOff()
	{
		envelope.input.set(0.0);
	}

	@Override
	public void applyFilterConfiguration(FilterConfiguration newFilterConfig)
	{
		lowPass.frequency.set(newFilterConfig.getLowPass());
		bandPass.frequency.set(newFilterConfig.getBandPass());
		highPass.frequency.set(newFilterConfig.getHighPass());

		lowPass.amplitude.set(newFilterConfig.getAmplitude());
		bandPass.amplitude.set(newFilterConfig.getAmplitude());
		highPass.amplitude.set(newFilterConfig.getAmplitude());
	}

	@Override
	public void applyEnvelopeConfiguration(EnvelopeConfiguration newEnvelopeConfig)
	{
		envelope.attack.set(newEnvelopeConfig.getAttack());
		envelope.decay.set(newEnvelopeConfig.getDecay());
		envelope.delay.set(newEnvelopeConfig.getDelay());
		envelope.hold.set(newEnvelopeConfig.getHold());
		envelope.release.set(newEnvelopeConfig.getRelease());
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp startTime)
	{
		oscillator.frequency.set(frequency);
		oscillator.amplitude.set(amplitude);

		TimeUtils.waitUntilPointOfTime(getCurrentTime(), startTime);

		envelope.amplitude.set(amplitude);
	}

	@Override
	public void noteOff(TimeStamp stopTime)
	{
		TimeUtils.waitUntilPointOfTime(getCurrentTime(), stopTime);

		envelope.amplitude.set(0.0);
	}

	private void initializeUnits()
	{
		oscillator = new SineOscillator();
		envelope = new EnvelopeDAHDSR();
		passThrough = new PassThrough();
		output = new PassThrough();

		bandPass = new FilterBandPass();
		lowPass = new FilterLowPass();
		highPass = new FilterHighPass();
	}

	private void addUnits()
	{
		super.add(envelope);
		super.add(oscillator);
		super.add(bandPass);
		super.add(lowPass);
		super.add(highPass);
		super.add(passThrough);
		super.add(output);
	}

	private void connectUnits()
	{
		envelope.output.connect(oscillator.amplitude);
		oscillator.output.connect(passThrough.input);

		passThrough.output.connect(lowPass.input);
		passThrough.output.connect(bandPass.input);
		passThrough.output.connect(highPass.input);

		lowPass.output.connect(output.input);
		bandPass.output.connect(output.input);
		highPass.output.connect(output.input);
	}

	private double getCurrentTime()
	{
		return oscillator.getSynthesizer().getCurrentTime();
	}
}
