package de.dhbw.java.main;

import com.jsyn.unitgen.UnitVoice;

import de.dhbw.java.main.circuits.UnitVoiceCircuits;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		UnitVoice defaultVoice = UnitVoiceCircuits.SinusVoice.getUnit();
		synthesizerPlayer = new SynthesizerPlayer(defaultVoice);
	}

	public void setUnitVoice(UnitVoice selectedVoice)
	{
		synthesizerPlayer.setUnitVoice(selectedVoice);
	}
}
