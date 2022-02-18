package de.dhbw.java.main.ui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.dhbw.java.main.SynthesizerPlayer;

public class GUIFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private List<JButton> toneButtons = new ArrayList<>();

	public GUIFrame()
	{
		super("Java Audio Synthesizer");

		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int i = 0; i < 8; i++)
		{
			toneButtons.add(getNewConfiguredToneButton(i));
		}

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		toneButtons.forEach(panel::add);

		this.add(panel);
		this.pack();
		this.setVisible(true);
	}

	private JButton getNewConfiguredToneButton(final int buttonIndex)
	{
		JButton jButton = new JButton("Tone " + buttonIndex);
		jButton.addActionListener(e -> SynthesizerPlayer.playTone(buttonIndex));
		return jButton;
	}
}
