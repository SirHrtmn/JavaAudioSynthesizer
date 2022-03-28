package synth;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import exceptions.InvalidNoteStringException;
import musical.Note;
import musical.NoteParser;

public class NoteParserTests
{

	@Test
	public void testAllNoteIdentifiers()
	{
		for (String note : Note.NOTE_IDENTIFIERS)
		{
			Assert.assertEquals(note, identifier(note));
		}
	}

	@Test
	public void testAllNoteIdentifiersWithOctaves()
	{
		for (int octave = 0; octave < 9; octave++)
		{
			for (String note : Note.NOTE_IDENTIFIERS)
			{
				Assert.assertEquals(note, identifier(note + octave));
			}
		}
	}

	@Test
	public void testAllOctaves()
	{
		for (int octave = 0; octave < 9; octave++)
		{
			final int octaveNumber = octave;
			Note.NOTE_IDENTIFIERS.forEach(note -> {
				Assert.assertEquals(octaveNumber, octave(note + octaveNumber));
			});
		}
	}

	@Test
	public void testIsSharpened()
	{
		List<String> sharpenedNotes = getSharpenedNotes();
		List<String> notSharpNotes = getNotSharpNotes();

		for (String sharpNote : sharpenedNotes)
		{
			Assert.assertTrue(isSharp(sharpNote));
		}

		for (String noteSharpNote : notSharpNotes)
		{
			Assert.assertFalse(isSharp(noteSharpNote));
		}
	}

	@Test
	public void testInvalidNotes()
	{
		testInvalidNote("Z");
		testInvalidNote("z");
		
		testInvalidNote("CC#0");
		testInvalidNote("CC#");
		testInvalidNote("CC");
		testInvalidNote("cc#0");
		testInvalidNote("cc#");
		testInvalidNote("cc");
		
		testInvalidNote("cb0");
		testInvalidNote("Cb0");
		testInvalidNote("Cb");
		testInvalidNote("cb");
		
		testInvalidNote("C # ");
		testInvalidNote("C # 0");
		testInvalidNote("C 0");
		testInvalidNote(" C");
		testInvalidNote("c # ");
		testInvalidNote("c # 0");
		testInvalidNote("c 0");
		testInvalidNote(" c");

		testInvalidNote("C9");
		testInvalidNote("C#9");
		testInvalidNote("c#9");
		testInvalidNote("c9");

		testInvalidNote("");
	}

	private String identifier(String noteString)
	{
		return NoteParser.getNoteIdentifier(noteString);
	}

	private int octave(String noteString)
	{
		return NoteParser.getOctave(noteString);
	}

	private boolean isSharp(String noteString)
	{
		return NoteParser.isNoteSharpened(noteString);
	}

	private void testInvalidNote(String noteString)
	{
		testInvalidNoteWithIdentifier(noteString);
		testInvalidNoteWithOctaves(noteString);
		testInvalidNoteWithSharpCheck(noteString);
	}

	private void testInvalidNoteWithIdentifier(String noteString)
	{
		try
		{
			NoteParser.getNoteIdentifier(noteString);
			Assert.fail("Expected InvalidNoteStringException");
		}
		catch (InvalidNoteStringException e)
		{
			// Test passed
		}
	}

	private void testInvalidNoteWithOctaves(String noteString)
	{
		try
		{
			NoteParser.getOctave(noteString);
			Assert.fail("Expected InvalidNoteStringException");
		}
		catch (InvalidNoteStringException e)
		{
			// Test passed
		}
	}

	private void testInvalidNoteWithSharpCheck(String noteString)
	{
		try
		{
			NoteParser.isNoteSharpened(noteString);
			Assert.fail("Expected InvalidNoteStringException");
		}
		catch (InvalidNoteStringException e)
		{
			// Test passed
		}
	}

	private List<String> getSharpenedNotes()
	{
		return Note.NOTE_IDENTIFIERS.stream().filter(note -> note.contains("#")).toList();
	}

	private List<String> getNotSharpNotes()
	{
		return Note.NOTE_IDENTIFIERS.stream().filter(note -> !note.contains("#")).toList();
	}
}
