package synth;

import synth.circuits.FilterEnvelopeVoice;
import synth.circuits.VoiceCircuits;

public class SynthesizerController
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerController()
	{
		FilterEnvelopeVoice defaultVoice = VoiceCircuits.SinusVoice.getUnit();
		synthesizerPlayer = new SynthesizerPlayer(defaultVoice);
	}

	public void setUnitVoice(FilterEnvelopeVoice selectedVoice)
	{
		synthesizerPlayer.setUnitVoice(selectedVoice);
	}
}
