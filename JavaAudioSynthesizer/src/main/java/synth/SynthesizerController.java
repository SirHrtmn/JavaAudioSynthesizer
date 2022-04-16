package synth;

import synth.circuits.VoiceCircuit;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		synthesizerPlayer = new SynthesizerPlayer(VoiceCircuit.SINUS_VOICE);
	}

	public void setUnitVoice(VoiceCircuit voiceCircuit)
	{
		synthesizerPlayer.setUnitVoice(voiceCircuit);
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		synthesizerPlayer.applyFilterConfiguration(filterConfig);
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		synthesizerPlayer.applyEnvelopeConfiguration(envelopeConfig);
	}

	public SynthesizerPlayer getSynthesizerPlayer()
	{
		return synthesizerPlayer;
	}
}
