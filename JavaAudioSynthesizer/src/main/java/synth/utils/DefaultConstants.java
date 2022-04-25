package synth.utils;

import java.util.EnumMap;
import java.util.Map;

import musical.NoteName;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synthesis.OscillatorType;

public class DefaultConstants
{
	private DefaultConstants()
	{
		// To hide the public constructor
	}

	private static final String[] KEYS = new String[]{"A", "W", "S", "E", "D", "F", "T", "G", "Z",
			"H", "U", "J"};

	public static final int DEFAULT_OCTAVE_NUMBER = 4;

	public static FilterConfiguration getFilterConfig()
	{
		return new FilterConfiguration(440.0, 440.0, 440.0, 1.0);
	}

	public static EnvelopeConfiguration getEnvelopeConfig()
	{
		return new EnvelopeConfiguration(0.0, 0.2, 1.0, 1.0, 0.2);
	}

	public static OscillatorType getOscillatorType()
	{
		return OscillatorType.SINUS;
	}

	public static Map<NoteName, String> getKeyBindings()
	{
		EnumMap<NoteName, String> map = new EnumMap<>(NoteName.class);

		NoteName[] noteNames = NoteName.values();
		for (int i = 0; i < noteNames.length; i++)
		{
			map.put(noteNames[i], KEYS[i]);
		}
		return map;
	}
}
