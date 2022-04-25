package synth;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.utils.DefaultConstants;
import synthesis.OscillatorType;

public class Controller
{
	private Player synthesizerPlayer;

	public Controller()
	{
		synthesizerPlayer = new Player(DefaultConstants.getOscillatorType());
	}

	public void setOscillatorType(OscillatorType type)
	{
		synthesizerPlayer.setOscillatorType(type);
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		synthesizerPlayer.applyFilterConfiguration(filterConfig);
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		synthesizerPlayer.applyEnvelopeConfiguration(envelopeConfig);
	}

	public Player getSynthesizerPlayer()
	{
		return synthesizerPlayer;
	}
}
