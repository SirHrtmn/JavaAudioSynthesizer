package synth;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;

import synth.circuits.FilterEnvelopeVoice;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public class SynthesizerPlayer
{
	private Synthesizer synthesizer;
	private LineOut lineOut;
	private FilterEnvelopeVoice unitVoice;

	public SynthesizerPlayer(FilterEnvelopeVoice unitVoice)
	{
		this.unitVoice = unitVoice;
		this.lineOut = new LineOut();
		this.synthesizer = JSyn.createSynthesizer();

		synthesizer.setRealTime(true);
		synthesizer.add(unitVoice.getUnitGenerator());
		synthesizer.add(lineOut);

		this.unitVoice.getOutput().connect(0, lineOut.input, 0);
		this.unitVoice.getOutput().connect(0, lineOut.input, 1);

		synthesizer.start();
		synthesizer.startUnit(lineOut);

		Runtime.getRuntime().addShutdownHook(new Thread(synthesizer::stop));
	}

	public void setUnitVoice(FilterEnvelopeVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		unitVoice.applyFilterConfiguration(filterConfig);
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envConfig)
	{
		unitVoice.applyEnvelopeConfiguration(envConfig);
	}

	public void noteOn(double frequency, double amplitude)
	{
		unitVoice.noteOn(frequency, amplitude);
	}

	public void noteOn(double frequency)
	{
		noteOn(frequency, 1.0);
	}

	public void noteOff()
	{
		unitVoice.noteOff();
	}
}
