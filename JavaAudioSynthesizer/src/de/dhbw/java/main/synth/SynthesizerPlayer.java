package de.dhbw.java.main.synth;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;

import de.dhbw.java.main.synth.circuits.OscillatorFilterVoice;

public class SynthesizerPlayer
{
	private Synthesizer synthesizer;
	private LineOut lineOut;
	private OscillatorFilterVoice unitVoice;

	public SynthesizerPlayer(OscillatorFilterVoice unitVoice)
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

	public void setUnitVoice(OscillatorFilterVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public void noteOn(double frequency, double amplitude)
	{
		unitVoice.noteOn(frequency, amplitude, synthesizer.createTimeStamp());
	}

	public void noteOff()
	{
		unitVoice.noteOff(synthesizer.createTimeStamp());
	}
}
