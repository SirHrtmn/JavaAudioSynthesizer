package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import configuration.EnvelopeConfiguration;
import configuration.FilterConfiguration;
import controlling.Controller;
import controlling.Player;
import gui.panels.EnvelopeConfigurationPanel;
import gui.panels.FilterConfigurationPanel;
import gui.panels.OscillatorSelectionPanel;
import utils.DefaultConstants;

public class SynthesizerGUI extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controller controller;

	private FilterConfigurationPanel filterPanel;
	private EnvelopeConfigurationPanel envelopePanel;
	private KeyboardGUI keyboard;
	private JPanel mainPanel;
	private OscillatorSelectionPanel selectionPanel;

	public SynthesizerGUI(Controller controller)
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
		mainPanel.add(selectionPanel, BorderLayout.NORTH);
	}

	private void initializeKeyboard()
	{
		Player player = controller.getPlayer();
		keyboard = new KeyboardGUI(player);
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
		selectionPanel = new OscillatorSelectionPanel();
		selectionPanel.addOscillatorSelectionListener(type -> controller.setOscillatorType(type));
	}

	public static void main(String[] args)
	{
		Controller controller = new Controller();
		new SynthesizerGUI(controller);
	}
}
