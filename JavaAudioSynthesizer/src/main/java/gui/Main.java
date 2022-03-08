package gui;

import synth.SynthesizerPlayer;
import synth.circuits.VoiceCircuits;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		SynthesizerPlayer synthesizerPlayer = new SynthesizerPlayer(VoiceCircuits.SinusVoice.getUnit());
		for (int i = 0; i < 10; i++)
		{
			upAndDown(synthesizerPlayer);
		}
		Runtime.getRuntime().exit(0);
	}

	private static void upAndDown(SynthesizerPlayer synthesizerPlayer)
			throws InterruptedException
	{
		for (int i = 0; i < 12; i++)
		{
			playTone(synthesizerPlayer, i);
		}
		for (int i = 12; i >= 0; i--)
		{
			playTone(synthesizerPlayer, i);
		}
	}

	private static void playTone(SynthesizerPlayer synthesizerPlayer, int index)
			throws InterruptedException
	{
//		synthesizerPlayer.noteOn(KeyboardFrequencyParser.getFrequencyFromHalfStepsFromA(index), 1.0);
		Thread.sleep(20);
		synthesizerPlayer.noteOff();
	}
}