package note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note
{
	private static final String SHARP_SIGN = "#";

	public static final List<String> NOTE_IDENTIFIERS = Arrays.asList("C", "C#", "D", "D#", "E",
			"F", "F#", "G", "G#", "A", "A#", "B");

	private double frequency;
	private String note;
	private boolean sharpended;
	private int octave;

	public Note(String noteIdentifier)
	{
		note = noteIdentifier;
	}

	public Note(String noteName, int octave)
	{
		note = noteName;
	}

	public static List<Note> getNoteList()
	{
		List<Note> notesList = new ArrayList<>();

		NOTE_IDENTIFIERS.forEach(noteName -> notesList.add(new Note(noteName)));

		return notesList;
	}

	public double getFrequency()
	{
		return frequency;
	}

	@Override
	public String toString()
	{
		String noteSharp = isSharpended() ? SHARP_SIGN : "";
		return note + noteSharp + getOctave();
	}

	public boolean isSharpended()
	{
		return sharpended;
	}

	public int getOctave()
	{
		return octave;
	}
}
