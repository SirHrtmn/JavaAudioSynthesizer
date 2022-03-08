package synth.keyboard;

public class KeyboardFrequencyParser
{
	private static final double EQUAL_12_TONE_TEMPERMENT = Math.pow(2, 1.0 / 12.0);
	private static final double PITCH_FREQUENCY_NOTE_A3 = 220.0;

	private KeyboardFrequencyParser()
	{
		// static class -> no constructor needed
	}

	public double getFrequencyFromNoteIndex(int noteIndex)
	{
		int octave = noteIndex / 12;
		int semiTone = noteIndex % 12;

		return getFrequencyFromOctaveAndSemiTone(octave, semiTone);
	}

	public static double getFrequencyFromOctaveAndSemiTone(int octave, int semiTone)
	{
		int halfStepsFromA = getHalfStepsFromA(octave, semiTone);

		return getFrequency(halfStepsFromA);
	}

	private static double getFrequency(int halfStepsFromA)
	{
		double pitchFrequency = PITCH_FREQUENCY_NOTE_A3;
		double pitchRatio = Math.pow(EQUAL_12_TONE_TEMPERMENT, halfStepsFromA);

		return pitchRatio * pitchFrequency;
	}

	private static int getHalfStepsFromA(int octave, int semiTone)
	{
		int rawNoteIndex = getIndexOfNote(octave, semiTone);
		int noteIndexPitchNoteA3 = getIndexOfNote(3, 9);

		return rawNoteIndex - noteIndexPitchNoteA3;
	}

	private static int getIndexOfNote(int octave, int semiTone)
	{
		return (octave * 12) + semiTone;
	}
}
