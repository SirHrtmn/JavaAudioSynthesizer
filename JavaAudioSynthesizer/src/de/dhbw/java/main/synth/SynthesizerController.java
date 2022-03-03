package de.dhbw.java.main.synth;

import com.jsyn.unitgen.UnitVoice;

import de.dhbw.java.main.synth.circuits.VoiceCircuits;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		UnitVoice defaultVoice = VoiceCircuits.SinusVoice.getUnit();
		synthesizerPlayer = new SynthesizerPlayer(defaultVoice);
	}

	public void setUnitVoice(UnitVoice selectedVoice)
	{
		synthesizerPlayer.setUnitVoice(selectedVoice);
	}
}
