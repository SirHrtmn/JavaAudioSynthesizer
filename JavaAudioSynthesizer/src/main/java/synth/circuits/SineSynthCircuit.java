package synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TunableFilter;
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
	private TunableFilter voiceOutput;

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
		return voiceOutput.getOutput();
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
		voiceOutput = new FilterStateVariable();

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
		super.add(voiceOutput);
	}

	private void connectUnits()
	{
		envelope.output.connect(oscillator.amplitude);
		oscillator.output.connect(lowPass.input);
		lowPass.output.connect(bandPass.input);
		bandPass.output.connect(highPass.input);
		highPass.output.connect(voiceOutput.input);
	}

	private double getCurrentTime()
	{
		return oscillator.getSynthesizer().getCurrentTime();
	}
}
