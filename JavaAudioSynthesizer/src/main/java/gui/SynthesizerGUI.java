package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import synth.SynthesizerController;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.utils.DefaultConstants;

public class SynthesizerGUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private FilterConfigurationPanel filterPanel;
	private EnvelopeConfigurationPanel envelopePanel;
	private KeyboardGUI keyboard;
	private JPanel mainPanel;
	private SynthesizerController controller;

	public SynthesizerGUI()
	{
		super("Java Audio Synthesizer");
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
		keyboard = new KeyboardGUI();
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
		new SynthesizerGUI();
	}
}
