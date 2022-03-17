package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class GUIKeyboard extends JPanel
{

	private static final long serialVersionUID = 1L;

	private static final List<String> NOTES = Arrays
			.asList(new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"});

	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT_FULLTONE = 200;
	private static final int BUTTON_HEIGHT_SEMITONE = 150;

	private static final String SEMITONE_INDICATOR = "#";

	public GUIKeyboard()
	{
		super(new FlowLayout());
		super.setBorder(new BevelBorder(BevelBorder.RAISED));

		setupKeyboardButtons();
	}

	private void setupKeyboardButtons()
	{
		for (int i = 0; i < NOTES.size(); i++)
		{
			String noteName = NOTES.get(i);

			Component noteButton = getNewButton(noteName);
			super.add(noteButton, new GridBagConstraints());
		}
	}

	private Component getNewButton(String text)
	{
		JButton button = new JButton(text);
		if (text.contains(SEMITONE_INDICATOR))
		{
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel verticalSpace = new JLabel();
			int verticalSpacingHeight = BUTTON_HEIGHT_FULLTONE - BUTTON_HEIGHT_SEMITONE;

			button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT_SEMITONE));
			verticalSpace.setPreferredSize(new Dimension(BUTTON_WIDTH, verticalSpacingHeight));

			panel.add(button);
			panel.add(verticalSpace);

			return panel;
		}

		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT_FULLTONE));
		return button;
	}
}
