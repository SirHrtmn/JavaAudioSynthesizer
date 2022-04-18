package gui.panels;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import gui.listeners.VoiceSelectionListener;
import synth.circuits.VoiceCircuit;

public class VoiceSelectionPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JComboBox<VoiceCircuit> comboBox;
	private List<VoiceSelectionListener> selectionListeners = new ArrayList<>();

	public VoiceSelectionPanel()
	{
		super(new FlowLayout(FlowLayout.CENTER));
		super.setBorder(new BevelBorder(BevelBorder.RAISED));

		initializePanel();
	}

	private void initializePanel()
	{
		JLabel label = new JLabel("Select your voice:");

		comboBox = new JComboBox<>(VoiceCircuit.values());
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(e -> triggerSelectionListeners());

		this.add(label);
		this.add(comboBox);
	}

	public void addVoiceSelectionListener(VoiceSelectionListener listener)
	{
		selectionListeners.add(listener);
	}

	private void triggerSelectionListeners()
	{
		for (VoiceSelectionListener selectionListener : selectionListeners)
		{
			selectionListener.voiceHasBeenSelected(getSelectedVoice());
		}
	}

	private VoiceCircuit getSelectedVoice()
	{
		return (VoiceCircuit) comboBox.getSelectedItem();
	}
}
