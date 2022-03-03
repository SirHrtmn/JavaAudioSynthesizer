package de.dhbw.java.main.synth;

import de.dhbw.java.main.synth.circuits.OscillatorFilterVoice;
import de.dhbw.java.main.synth.circuits.VoiceCircuits;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		OscillatorFilterVoice defaultVoice = VoiceCircuits.SinusVoice.getUnit();
		synthesizerPlayer = new SynthesizerPlayer(defaultVoice);
	}

	public void setUnitVoice(OscillatorFilterVoice selectedVoice)
	{
		synthesizerPlayer.setUnitVoice(selectedVoice);
	}
}
