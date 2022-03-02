package de.dhbw.java.main;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitVoice;

public class SynthesizerPlayer
{
	private Synthesizer synthesizer;
	private LineOut lineOut;
	private UnitVoice unitVoice;

	public SynthesizerPlayer(UnitVoice unitVoice)
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
	}

	public void setUnitVoice(UnitVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}
}
