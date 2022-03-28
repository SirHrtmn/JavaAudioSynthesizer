package note;

import exceptions.InvalidNoteStringException;

public class NoteParser
{
	private static final String NOTE_SCHEME_REGEX = "[a-gA-G]#?[0-8]?";
	private static final int DEFAULT_OCTAVE_NUMBER = 4;
	private static final int EXCLUSIVE_OCTAVE_MAXIMUM = 9;

	public static String getNoteIdentifier(String noteString)
	{
		checkNoteString(noteString);

		char noteName = noteString.charAt(0);
		String sharpSign = noteString.contains("#") ? "#" : "";

		String noteIdentifier = noteName + sharpSign;

		return noteIdentifier;
	}

	public static int getOctave(String noteString)
	{
		checkNoteString(noteString);

		if (noteString.matches(".*[0-8]"))
		{
			int indexOfOctaveNumber = noteString.length() - 1;
			char octaveNumber = noteString.charAt(indexOfOctaveNumber);

			return Character.digit(octaveNumber, EXCLUSIVE_OCTAVE_MAXIMUM);
		}

		return DEFAULT_OCTAVE_NUMBER;
	}

	public static boolean isNoteSharpened(String noteString)
	{
		checkNoteString(noteString);

		return noteString.contains("#");
	}

	private static void checkNoteString(String noteString)
	{
		if (noteString == null)
		{
			throw new NullPointerException("Provided note string was null!");
		}

		if (!noteString.matches(NOTE_SCHEME_REGEX))
		{
			throw new InvalidNoteStringException("Provided note string did not match scheme");
		}
	}
}
