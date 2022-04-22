package synth.circuits;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TriangleOscillator;

public enum VoiceCircuit
{
	SINUS(() -> new OscillatorVoiceCircuit(new SineOscillator())),
	SAWTOOTH(() -> new OscillatorVoiceCircuit(new SawtoothOscillator())),
	PULSE(() -> new OscillatorVoiceCircuit(new PulseOscillator())),
	TRIANGLE(() -> new OscillatorVoiceCircuit(new TriangleOscillator()));

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
