package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gui.listeners.NoteButtonClickListener;
import note.Note;

public class NoteButton extends JButton
{
	private static final long serialVersionUID = 1L;

	private Note note;
	private boolean isPushed;
	private List<NoteButtonClickListener> clickListeners = new ArrayList<>();

	public NoteButton(Note note)
	{
		this.note = note;
		super.setText(note.toString());
		super.addChangeListener(e -> triggerNoteButtonClickListeners());

		if (note.isSharpended())
		{
			super.setBackground(Color.GRAY);
		}
	}

	public boolean isPushed()
	{
		return isPushed;
	}

	public void setPushed(boolean isPushed)
	{
		this.isPushed = isPushed;
	}

	public Note getNote()
	{
		return note;
	}

	public void addNoteButtonClickListener(NoteButtonClickListener clickListener)
	{
		clickListeners.add(clickListener);
	}

	private void triggerNoteButtonClickListeners()
	{
		for (NoteButtonClickListener listener : clickListeners)
		{
			listener.noteButtonWasClicked(this);
		}
	}
}
