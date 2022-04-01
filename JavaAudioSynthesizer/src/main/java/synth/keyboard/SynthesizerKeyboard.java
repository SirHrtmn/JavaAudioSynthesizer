package synth.keyboard;

import gui.listeners.NoteReleaseListener;
import musical.Note;
import synth.SynthesizerPlayer;

public class SynthesizerKeyboard
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerKeyboard(SynthesizerPlayer synthesizerPlayer)
	{
		this.synthesizerPlayer = synthesizerPlayer;
	}

	public void pushButton(Note note)
	{
		synthesizerPlayer.noteOn(note);
	}

	public void releaseButton(Note note)
	{
		synthesizerPlayer.noteOff(note);
	}

	public void addNoteReleaseListener(NoteReleaseListener listener)
	{
		synthesizerPlayer.addNoteReleaseListener(listener);
	}
}
