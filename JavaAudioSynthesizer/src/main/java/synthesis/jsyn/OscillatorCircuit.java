package synthesis.jsyn;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitOscillator;

import configuration.EnvelopeConfiguration;
import configuration.FilterConfiguration;
import synthesis.jsyn.configuration.ConfigurationHelper;

public class OscillatorCircuit extends Circuit implements UnitsCircuit
{
	private UnitOscillator oscillator;
	private EnvelopeDAHDSR envelope;
	private FilterBandPass bandPass;
	private FilterLowPass lowPass;
	private FilterHighPass highPass;
	private PassThrough passThrough;
	private PassThrough output;

	public OscillatorCircuit(UnitOscillator oscillator, FilterConfiguration filterConfig,
			EnvelopeConfiguration envConfig)
	{
		initializeUnits(oscillator);
		addUnits();
		connectUnits();

		applyEnvelopeConfiguration(envConfig);
		applyFilterConfiguration(filterConfig);
	}

	@Override
	public UnitOutputPort getOutput()
	{
		return output.getOutput();
	}

	@Override
	public void noteOn(double frequency)
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
