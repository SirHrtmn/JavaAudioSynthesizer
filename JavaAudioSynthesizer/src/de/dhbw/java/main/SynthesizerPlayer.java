package de.dhbw.java.main;

import com.jsyn.Synthesizer;

public class SynthesizerPlayer
{
	private SynthesizerPlayer()
	{
		// Static class -> no need for constructor
	}

	public static void playTone(int index)
	{
		Synthesizer synth = SynthesizerHandler.getSynthesizer();
		synth.start();
		
		// TODO

		synth.stop();
	}
}
