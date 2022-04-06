package synth;

import static org.mockito.Mockito.mock;

import javax.swing.JComponent;

import org.junit.Assert;
import org.junit.Test;

import gui.KeyboardGUI;
import gui.KeyboardKeyBinder;
import gui.NoteButton;
import musical.Note;
import synth.keyboard.SynthesizerKeyboard;

public class KeyboardKeyBinderTests
{

	@Test
	public void testConfigureKeyBindings()
	{
		KeyboardGUI keyboardGUIMock = new KeyboardGUI(mock(SynthesizerKeyboard.class));
		KeyboardKeyBinder keyBinder = new KeyboardKeyBinder(keyboardGUIMock);

		Note exampleNote = new Note("C4");
		NoteButton noteButton = new NoteButton(exampleNote);

		keyBinder.configureKeyBindings(noteButton);

		int inputMapCondition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		Assert.assertEquals(2, noteButton.getInputMap(inputMapCondition).size());
		Assert.assertEquals(2, noteButton.getActionMap().size());
	}

}
