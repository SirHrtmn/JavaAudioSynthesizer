package synth;

import synth.circuits.FilterEnvelopeVoice;
import synth.circuits.VoiceCircuits;
import synth.configuration.FilterConfiguration;

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
	
	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		synthesizerPlayer.applyFilterConfiguration(filterConfig);
	}
}
