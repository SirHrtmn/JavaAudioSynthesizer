package synth.circuits;

public enum VoiceCircuit
{
	SINUS_VOICE(SineSynthCircuit::new);

	private VoiceFactory voiceFactory;

	private VoiceCircuit(VoiceFactory voiceFactory)
	{
		this.voiceFactory = voiceFactory;
	}

	public FilterEnvelopeVoice getUnit()
	{
		return voiceFactory.buildVoice();
	}
}
