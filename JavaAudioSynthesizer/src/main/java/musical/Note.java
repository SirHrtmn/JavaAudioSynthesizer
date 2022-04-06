package musical;

import java.util.ArrayList;
import java.util.List;

import synth.utils.DefaultConstants;

public class Note
{
	private double frequency;
	private NoteName noteName;
	private int octave;

	public Note(String note)
	{
		this(note, NoteParser.getOctave(note));
	}

	public Note(String note, int octave)
	{
		this(NoteParser.getNoteName(note), octave);
	}

	public Note(NoteName noteName, int octave)
	{
		this.noteName = noteName;
		this.octave = octave;
		this.frequency = NoteToFrequencyParser.getFrequencyFromOctaveAndNoteName(noteName, octave);
	}

	public static List<Note> getNoteList()
	{
		List<Note> notesList = new ArrayList<>();

		for (NoteName noteName : NoteName.values())
		{
			notesList.add(new Note(noteName, DefaultConstants.DEFAULT_OCTAVE_NUMBER));
		}

		return notesList;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public NoteName getNoteName()
	{
		return noteName;
	}

	@Override
	public String toString()
	{
		return noteName.getName() + getOctave();
	}

	public boolean isSharpended()
	{
		return noteName.isSharp();
	}

	public int getOctave()
	{
		return octave;
	}
}
