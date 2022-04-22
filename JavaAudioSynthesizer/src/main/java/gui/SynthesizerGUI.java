package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panels.EnvelopeConfigurationPanel;
import gui.panels.FilterConfigurationPanel;
import gui.panels.VoiceSelectionPanel;
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
	private VoiceSelectionPanel voiceSelectionPanel;

	public SynthesizerGUI(SynthesizerController controller)
	{
		super("Java Audio Synthesizer");
		this.controller = controller;
		setupGUI();
	}

	private void setupGUI()
	{
		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeFilterPanel();
		initializeEnvelopePanel();
		initializeKeyboard();
		initializeVoiceSelectionPanel();

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
		mainPanel.add(voiceSelectionPanel, BorderLayout.NORTH);
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

	private void initializeVoiceSelectionPanel()
	{
		voiceSelectionPanel = new VoiceSelectionPanel();
		voiceSelectionPanel.addVoiceSelectionListener(voice -> controller.setUnitVoice(voice));
	}

	public static void main(String[] args)
	{
		SynthesizerController controller = new SynthesizerController();
		new SynthesizerGUI(controller);
	}
}
