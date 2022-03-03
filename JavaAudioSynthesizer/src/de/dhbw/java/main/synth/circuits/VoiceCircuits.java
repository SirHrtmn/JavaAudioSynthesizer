package de.dhbw.java.main.synth.circuits;

public enum VoiceCircuits
{
	SinusVoice(new SineSynthCircuit());

	private OscillatorFilterVoice unitVoice;

	private VoiceCircuits(OscillatorFilterVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public OscillatorFilterVoice getUnit()
	{
		return unitVoice;
	}
}
