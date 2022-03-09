package synth;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import synth.keyboard.KeyboardFrequencyParser;

public class KeyboardFrequencyParserTest
{

	private static final double DELTA = 0.001;

	@Test
	public void testLowestANote()
	{
		double a0Frequency = parseFrequency(0, 9);
		Assert.assertEquals(27.50, a0Frequency, DELTA);

		double a0NoteIndexFrequency = parseFrequency(9);
		Assert.assertEquals(27.50, a0NoteIndexFrequency, DELTA);
	}

	@Test
	public void testValidNoteIndexes()
	{
		Map<Integer, Double> a_noteFrequencyPairs = new HashMap<>();
		a_noteFrequencyPairs.put(21, 55.00); /* Octave 0 ... */
		a_noteFrequencyPairs.put(33, 110.00);
		a_noteFrequencyPairs.put(45, 220.00);
		a_noteFrequencyPairs.put(57, 440.00);
		a_noteFrequencyPairs.put(69, 880.00);
		a_noteFrequencyPairs.put(81, 1760.00);
		a_noteFrequencyPairs.put(93, 3520.00);
		a_noteFrequencyPairs.put(105, 7040.00); /* ...Octave 8 */

		a_noteFrequencyPairs.forEach((index, freq) -> {
			assertFrequencyWithNoteIndex(freq, index);
		});

		Map<Integer, Double> c_noteFrequencyPairs = new HashMap<>();
		c_noteFrequencyPairs.put(0, 16.3516);
		c_noteFrequencyPairs.put(12, 32.7032);
		c_noteFrequencyPairs.put(24, 65.4064);
		c_noteFrequencyPairs.put(36, 130.8128);
		c_noteFrequencyPairs.put(48, 261.6256);
		c_noteFrequencyPairs.put(60, 523.2511);
		c_noteFrequencyPairs.put(72, 1046.502);
		c_noteFrequencyPairs.put(84, 2093.005);
		c_noteFrequencyPairs.put(96, 4186.009);

		c_noteFrequencyPairs.forEach((index, freq) -> {
			assertFrequencyWithNoteIndex(freq, index);
		});
	}

	@Test
	public void testInvalidNoteIndexes()
	{
		assertFrequencyFromInvalidNoteIndex(-1);
		assertFrequencyFromInvalidNoteIndex(109);
		assertFrequencyFromInvalidNoteIndex((int) 9E9);
		assertFrequencyFromInvalidNoteIndex((int) -9E9);
	}

	@Test
	public void testValidOctaveAndSemiTones()
	{
		assertFrequencyFromOctaveAndSemitones(0, 0, 16.3516);
		assertFrequencyFromOctaveAndSemitones(1, 0, 32.7032);
		assertFrequencyFromOctaveAndSemitones(2, 0, 65.4064);
		assertFrequencyFromOctaveAndSemitones(3, 0, 130.8128);
		assertFrequencyFromOctaveAndSemitones(4, 0, 261.6256);
		assertFrequencyFromOctaveAndSemitones(5, 0, 523.2511);
		assertFrequencyFromOctaveAndSemitones(6, 0, 1046.502);
		assertFrequencyFromOctaveAndSemitones(7, 0, 2093.005);
		assertFrequencyFromOctaveAndSemitones(8, 0, 4186.009);

		assertFrequencyFromOctaveAndSemitones(1, -12, 16.3516);
		assertFrequencyFromOctaveAndSemitones(0, 12, 32.7032);
		assertFrequencyFromOctaveAndSemitones(0, 24, 65.4064);
		assertFrequencyFromOctaveAndSemitones(0, 36, 130.8128);
	}

	@Test
	public void testInvalidOctaveAndSemiTones()
	{
		assertFrequencyFromInvalidOctaveAndSemitones(0, -12);
		assertFrequencyFromInvalidOctaveAndSemitones(8, 13);
		assertFrequencyFromInvalidOctaveAndSemitones(0, -24);
		assertFrequencyFromInvalidOctaveAndSemitones(0, -(int)9E9);
		assertFrequencyFromInvalidOctaveAndSemitones(8, 24);
		assertFrequencyFromInvalidOctaveAndSemitones(8, (int)9E9);
		
		assertFrequencyFromInvalidOctaveAndSemitones(-1, 0);
		assertFrequencyFromInvalidOctaveAndSemitones(-(int)9E9, 0);
		assertFrequencyFromInvalidOctaveAndSemitones((int)9E9, (int)9E9);
		assertFrequencyFromInvalidOctaveAndSemitones(-(int)9E9, -(int)9E9);
		assertFrequencyFromInvalidOctaveAndSemitones(9, 0);
		assertFrequencyFromInvalidOctaveAndSemitones(9, -12);
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
			int noteIndex)
	{
		String message = String.format("Parsed note index %s -", noteIndex);
		double parsedFrequency = parseFrequency(noteIndex);

		Assert.assertEquals(message, expectedFrequency, parsedFrequency, DELTA);
	}

	private void assertFrequencyFromInvalidNoteIndex(int noteIndex)
	{
		try
		{
			parseFrequency(noteIndex);
			Assert.fail("Expected exception thrown because of invalid note index "
					+ noteIndex);
		}
		catch (IndexOutOfBoundsException e)
		{
			// Test passed
		}
	}

	private void assertFrequencyFromOctaveAndSemitones(int octave, int semiTones,
			double frequency)
	{
		String message = String.format("Parsed octave: %s, semitone: %s -", octave,
				semiTones);
		double parsedFrequency = parseFrequency(octave, semiTones);

		Assert.assertEquals(message, frequency, parsedFrequency, DELTA);
	}

	private void assertFrequencyFromInvalidOctaveAndSemitones(int octave, int semiTones)
	{
		try
		{
			parseFrequency(octave, semiTones);
			Assert.fail("Expected exception thrown because of invalid octave "
					+ octave + " or semiTone " + semiTones);
		}
		catch (IndexOutOfBoundsException e)
		{
			// Test passed
		}
	}
}
