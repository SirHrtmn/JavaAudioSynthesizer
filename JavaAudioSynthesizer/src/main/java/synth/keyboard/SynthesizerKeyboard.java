package synth.keyboard;

import java.util.ArrayList;
import java.util.List;

import note.Note;
import synth.SynthesizerPlayer;

public class SynthesizerKeyboard
{
	private SynthesizerPlayer synthesizerPlayer;
	private List<Note> notesPushed = new ArrayList<>();

	public SynthesizerKeyboard(SynthesizerPlayer synthesizerPlayer)
	{
		this.synthesizerPlayer = synthesizerPlayer;
	}

	public void pushButton(Note note)
	{
		synthesizerPlayer.noteOn(note.getFrequency());
		notesPushed.add(note);
	}

	public void releaseButton(Note note)
	{
		synthesizerPlayer.noteOff();
	}

	public boolean isButtonPushed(Note note)
	{
		return notesPushed.contains(note);
	}
}
