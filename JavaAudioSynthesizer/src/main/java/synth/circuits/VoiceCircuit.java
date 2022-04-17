package synth.circuits;

import com.jsyn.unitgen.SineOscillator;

public enum VoiceCircuit
{
	SINUS_VOICE(() -> new OscillatorVoiceCircuit(new SineOscillator()));

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
