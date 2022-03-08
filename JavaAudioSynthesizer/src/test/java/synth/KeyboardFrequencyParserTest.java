package synth;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import synth.keyboard.KeyboardFrequencyParser;

public class KeyboardFrequencyParserTest
{

	@Test
	public void testLowestANote()
	{
		double a0Frequency = parseFrequency(0, 9);
		Assert.assertEquals(27.50, a0Frequency, 0.001);

		double a0NoteIndexFrequency = parseFrequency(9);
		Assert.assertEquals(27.50, a0NoteIndexFrequency, 0.001);
	}

	@Test
	public void testAllPossible_A_Notes()
	{
		Map<Integer, Double> noteIndexFrequencyPairs = new HashMap<>();
		noteIndexFrequencyPairs.put(21, 55.00);
		noteIndexFrequencyPairs.put(33, 110.00);
		noteIndexFrequencyPairs.put(45, 220.00);
		noteIndexFrequencyPairs.put(57, 440.00);
		noteIndexFrequencyPairs.put(69, 880.00);
		noteIndexFrequencyPairs.put(81, 1760.00);
		noteIndexFrequencyPairs.put(93, 3520.00);
		noteIndexFrequencyPairs.put(105, 7040.00);
	
		noteIndexFrequencyPairs.forEach((index, freq) -> {
			assertFrequencyWithNoteIndex(freq, index, 0.001);
		});
	}

	@Test
	public void testInvalidNoteIndexes()
	{
		parseInvalidNoteIndex(-1);
		parseInvalidNoteIndex(109);
		parseInvalidNoteIndex((int)9E9);
	}

	private double parseFrequency(int noteIndex)
	{
		return KeyboardFrequencyParser.getFrequencyFromNoteIndex(noteIndex);
	}

	private double parseFrequency(int octave, int semiTone)
	{
		return KeyboardFrequencyParser.getFrequencyFromOctaveAndSemiTone(octave,
				semiTone);
	}

	private void assertFrequencyWithNoteIndex(double expectedFrequency,
			int noteIndex, double delta)
	{
		String message = String.format("Parsed note index %s -", noteIndex);
		double parsedFrequency = parseFrequency(noteIndex);

		Assert.assertEquals(message, expectedFrequency, parsedFrequency, delta);
	}

	private void parseInvalidNoteIndex(int noteIndex)
	{
		try
		{
			parseFrequency(noteIndex);
			Assert.fail("Expected exception thrown because of invalid note index "
					+ noteIndex);
		}
		catch (Exception e)
		{
			// Test passed
		}
	}

}
