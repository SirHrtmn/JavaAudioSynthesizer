package controlling;

import gui.listeners.NoteReleaseListener;
import musical.Note;

public class Keyboard
{
	private Player player;

	public Keyboard(Player player)
	{
		this.player = player;
	}

	public void pushButton(Note note)
	{
		player.noteOn(note);
	}

	public void releaseButton(Note note)
	{
		player.noteOff(note);
	}

	public void addNoteReleaseListener(NoteReleaseListener listener)
	{
		player.addNoteReleaseListener(listener);
	}
}
