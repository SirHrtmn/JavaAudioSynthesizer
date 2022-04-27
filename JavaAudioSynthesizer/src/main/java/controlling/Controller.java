package controlling;

import configuration.EnvelopeConfiguration;
import configuration.FilterConfiguration;
import synthesis.OscillatorType;
import utils.DefaultConstants;

public class Controller
{
	private Player player;

	public Controller()
	{
		player = new Player(DefaultConstants.getOscillatorType());
	}

	public void setOscillatorType(OscillatorType type)
	{
		player.setOscillatorType(type);
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		player.applyFilterConfiguration(filterConfig);
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		player.applyEnvelopeConfiguration(envelopeConfig);
	}

	public Player getPlayer()
	{
		return player;
	}
}
