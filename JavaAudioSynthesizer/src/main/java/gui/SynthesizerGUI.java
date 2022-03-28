package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panels.EnvelopeConfigurationPanel;
import gui.panels.FilterConfigurationPanel;
import synth.SynthesizerController;
import synth.SynthesizerPlayer;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.keyboard.SynthesizerKeyboard;
import synth.utils.DefaultConstants;

public class SynthesizerGUI extends JFrame
{
	private static final long serialVersionUID = 1L;

	private SynthesizerController controller;

	private FilterConfigurationPanel filterPanel;
	private EnvelopeConfigurationPanel envelopePanel;
	private KeyboardGUI keyboard;
	private JPanel mainPanel;

	public SynthesizerGUI(SynthesizerController controller)
	{
		super("Java Audio Synthesizer");
		this.controller = controller;
		controller = new SynthesizerController();
		setupGUI();
	}

	private void setupGUI()
	{
		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeFilterPanel();
		initializeEnvelopePanel();
		initializeKeyboard();

		setupMainPanel();

		this.add(mainPanel);

		this.pack();
		this.setVisible(true);
	}

	private void setupMainPanel()
	{
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(filterPanel, BorderLayout.EAST);
		mainPanel.add(envelopePanel, BorderLayout.WEST);
		mainPanel.add(keyboard, BorderLayout.SOUTH);
	}

	private void initializeKeyboard()
	{
		SynthesizerPlayer player = controller.getSynthesizerPlayer();
		keyboard = new KeyboardGUI(new SynthesizerKeyboard(player));
	}

	private void initializeEnvelopePanel()
	{
		EnvelopeConfiguration envConfig = DefaultConstants.getEnvelopeConfig();
		envelopePanel = new EnvelopeConfigurationPanel(envConfig);
		envelopePanel.addChangeListener(
				e -> controller.applyEnvelopeConfiguration(envelopePanel.getConfig()));
	}

	private void initializeFilterPanel()
	{
		FilterConfiguration filterConfig = DefaultConstants.getFilterConfig();
		filterPanel = new FilterConfigurationPanel(filterConfig);
		filterPanel.addChangeListener(
				e -> controller.applyFilterConfiguration(filterPanel.getConfig()));
	}

	public static void main(String[] args)
	{
		SynthesizerController controller = new SynthesizerController();
		new SynthesizerGUI(controller);
	}
}
