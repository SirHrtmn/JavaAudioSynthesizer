package de.dhbw.java.main.circuits;

import com.jsyn.unitgen.UnitVoice;

public enum UnitVoiceCircuits
{
	SinusVoice(new SineSynthCircuit());

	private UnitVoice unitVoice;

	private UnitVoiceCircuits(UnitVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public UnitVoice getUnit()
	{
		return unitVoice;
	}
}
