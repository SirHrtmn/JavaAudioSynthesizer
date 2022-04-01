package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import gui.listeners.NoteButtonClickListener;
import gui.listeners.NoteReleaseListener;
import musical.Note;
import synth.keyboard.SynthesizerKeyboard;

public class KeyboardGUI extends JPanel
{

	private static final long serialVersionUID = 1L;

	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT_FULLTONE = 200;
	private static final int BUTTON_HEIGHT_SEMITONE = 150;

	private SynthesizerKeyboard keyboard;
	private List<NoteButton> noteButtons = new ArrayList<>();

	public KeyboardGUI(SynthesizerKeyboard keyboard)
	{
		super(new FlowLayout());
		this.keyboard = keyboard;
		super.setBorder(new BevelBorder(BevelBorder.RAISED));

		setupKeyboardButtons();
		addNoteReleaseListener();
	}

	private void addNoteReleaseListener()
	{
		NoteReleaseListener listener = note -> {
			Optional<NoteButton> noteButtonOptional = getMatchingNoteButton(note);
			if (noteButtonOptional.isPresent())
			{
				noteButtonOptional.get().setPushed(false);
			}
		};

		keyboard.addNoteReleaseListener(listener);
	}

	private Optional<NoteButton> getMatchingNoteButton(Note note)
	{
		Predicate<? super NoteButton> predicate = noteButton -> noteButton.getNote().equals(note);
		Stream<NoteButton> filteredNoteButtonStream = noteButtons.stream().filter(predicate);

		Optional<NoteButton> optionalNoteButton = filteredNoteButtonStream.findFirst();
		return optionalNoteButton;
	}

	private void setupKeyboardButtons()
	{
		addNewButtons();

		assignClickListeners();
	}

	private void addNewButtons()
	{
		for (Note note : Note.getNoteList())
		{
			NoteButton noteButton = getNewButton(note);
			super.add(noteButton, new GridBagConstraints());
			noteButtons.add(noteButton);
		}
	}

	private NoteButton getNewButton(Note note)
	{
		if (note.isSharpended())
		{
			NoteButton sharpNoteButton = new NoteButton(note);
			sharpNoteButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT_SEMITONE));
			return sharpNoteButton;
		}

		NoteButton noteButton = new NoteButton(note);
		noteButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT_FULLTONE));
		return noteButton;
	}

	private void assignClickListeners()
	{
		NoteButtonClickListener listener = noteButton -> {
			if (!noteButton.isPushed())
			{
				keyboard.pushButton(noteButton.getNote());
				noteButton.setPushed(true);
				return;
			}

			keyboard.releaseButton(noteButton.getNote());
			noteButton.setPushed(false);
		};

		noteButtons.forEach(btn -> btn.addNoteButtonClickListener(listener));
	}
}
