package synth.circuits;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;

public enum VoiceCircuit
{
	SINUS(buildVoiceFactory(new SineOscillator())),
	SAWTOOTH(buildVoiceFactory(new SawtoothOscillator())),
	PULSE(buildVoiceFactory(new PulseOscillator())),
	TRIANGLE(buildVoiceFactory(new TriangleOscillator()));

	private VoiceFactory voiceFactory;

	private VoiceCircuit(VoiceFactory voiceFactory)
	{
		this.voiceFactory = voiceFactory;
	}

	public FilterEnvelopeVoice getUnit()
	{
		return voiceFactory.buildVoice();
	}

	private static VoiceFactory buildVoiceFactory(UnitOscillator oscillator)
	{
		return () -> new OscillatorVoiceCircuit(oscillator);
	}
}
