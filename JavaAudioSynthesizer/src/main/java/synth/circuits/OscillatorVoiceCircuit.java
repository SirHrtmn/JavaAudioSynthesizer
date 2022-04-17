package synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import synth.configuration.ConfigurationHelper;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.utils.DefaultConstants;
import synth.utils.TimeUtils;

public class OscillatorVoiceCircuit extends Circuit implements FilterEnvelopeVoice
{
	protected UnitOscillator oscillator;
	protected EnvelopeDAHDSR envelope;
	protected FilterBandPass bandPass;
	protected FilterLowPass lowPass;
	protected FilterHighPass highPass;
	private PassThrough passThrough;
	protected PassThrough output;

	public OscillatorVoiceCircuit(UnitOscillator oscillator)
	{
		this(oscillator, DefaultConstants.getFilterConfig(), DefaultConstants.getEnvelopeConfig());
	}

	public OscillatorVoiceCircuit(UnitOscillator oscillator, FilterConfiguration filterConfig,
			EnvelopeConfiguration envConfig)
	{
		super();

		initializeUnits(oscillator);
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
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp)
	{
		TimeStamp currentTime = super.getSynthesizer().createTimeStamp();
		TimeUtils.waitUntilPointOfTime(currentTime, timeStamp);
		noteOn(frequency, amplitude);
	}

	@Override
	public void noteOff(TimeStamp timeStamp)
	{
		TimeStamp currentTime = super.getSynthesizer().createTimeStamp();
		TimeUtils.waitUntilPointOfTime(currentTime, timeStamp);
		noteOff();
	}

	@Override
	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		ConfigurationHelper.applyForFilter(lowPass, bandPass, highPass, filterConfig);
	}

	@Override
	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		ConfigurationHelper.applyForEnvelope(envelope, envelopeConfig);
	}

	private void initializeUnits(UnitOscillator oscillator)
	{
		this.oscillator = oscillator;
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
}
