package de.dhbw.java.main;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;

public class SynthesizerHandler
{
	private static Synthesizer synthesizer;
	private static SineOscillator oscillator;
	private static LineOut output;
	private static FilterStateVariable filter;

	private SynthesizerHandler()
	{
		/* Static class -> no constructor needed */
	}

	public static Synthesizer getSynthesizer()
	{
		if (synthesizer == null)
		{
			synthesizer = createSynthesizer();
		}
		return synthesizer;
	}

	public static LineOut getLineOutput()
	{
		if (output == null)
		{
			getSynthesizer();
		}
		return output;
	}

	private static Synthesizer createSynthesizer()
	{
		Synthesizer rawSynthesizer = JSyn.createSynthesizer();
		oscillator = new SineOscillator();
		output = new LineOut();
		filter = new FilterStateVariable();

		rawSynthesizer.add(oscillator);
		rawSynthesizer.add(filter);
		rawSynthesizer.add(output);

		oscillator.output.connect(filter.input);
		filter.output.connect(output.input);

		return rawSynthesizer;
	}
}
