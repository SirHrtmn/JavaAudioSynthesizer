package controlling;

import configuration.EnvelopeConfiguration;
import configuration.FilterConfiguration;
import synthesis.OscillatorType;
import utils.DefaultConstants;

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
