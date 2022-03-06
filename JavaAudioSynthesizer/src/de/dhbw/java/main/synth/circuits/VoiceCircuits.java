package de.dhbw.java.main.synth.circuits;

public enum VoiceCircuits
{
	SinusVoice(new SineSynthCircuit());

	private FilterEnvelopeVoice unitVoice;

	private VoiceCircuits(FilterEnvelopeVoice unitVoice)
	{
		this.unitVoice = unitVoice;
	}

	public FilterEnvelopeVoice getUnit()
	{
		return unitVoice;
	}
}
