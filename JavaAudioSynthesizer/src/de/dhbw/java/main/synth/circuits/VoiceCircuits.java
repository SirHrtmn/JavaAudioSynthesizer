package de.dhbw.java.main.synth.circuits;

import com.jsyn.unitgen.UnitVoice;

public enum VoiceCircuits
{
	SinusVoice(new SineSynthCircuit());

	private UnitVoice unitVoice;

	private VoiceCircuits(UnitVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public UnitVoice getUnit()
	{
		return unitVoice;
	}
}
