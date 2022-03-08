package synth.circuits;

public enum VoiceCircuits
{
	SinusVoice(() -> new SineSynthCircuit());

	private VoiceFactory voiceFactory;

	private VoiceCircuits(VoiceFactory voiceFactory)
	{
		this.voiceFactory = voiceFactory;
	}

	public FilterEnvelopeVoice getUnit()
	{
		return voiceFactory.buildVoice();
	}
}
