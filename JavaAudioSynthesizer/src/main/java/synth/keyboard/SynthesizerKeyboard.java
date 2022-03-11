package synth.keyboard;

import synth.SynthesizerPlayer;

public class SynthesizerKeyboard
{
	private SynthesizerPlayer synthesizerPlayer;

	public SynthesizerKeyboard(SynthesizerPlayer synthesizerPlayer)
	{
		this.synthesizerPlayer = synthesizerPlayer;
	}
	
	public void pushButton(int noteIndex)
	{
		double frequencyFromNoteIndex = KeyboardFrequencyParser.getFrequencyFromNoteIndex(noteIndex);
		synthesizerPlayer.noteOn(frequencyFromNoteIndex);
	}
	
	public void releaseButton(int noteIndex)
	{
		synthesizerPlayer.noteOff();
	}
}
