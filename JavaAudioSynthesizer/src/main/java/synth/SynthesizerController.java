package synth;

import synth.circuits.FilterEnvelopeVoice;
import synth.circuits.VoiceCircuits;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		synthesizerPlayer = new SynthesizerPlayer(VoiceCircuits.SinusVoice);
	}

	public void setUnitVoice(FilterEnvelopeVoice selectedVoice)
	{
		synthesizerPlayer.setUnitVoice(selectedVoice);
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
