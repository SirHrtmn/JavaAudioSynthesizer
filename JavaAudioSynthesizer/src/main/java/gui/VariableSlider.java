package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class VariableSlider extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JSlider slider;
	private JLabel valueLabel;
	private JLabel descriptionLabel;

	public VariableSlider(String name, int min, int max, int value)
	{
		super(new BorderLayout());
		slider = new JSlider(min, max, value);
		valueLabel = new JLabel(value + "");
		descriptionLabel = new JLabel(name);

		slider.addChangeListener(e -> valueLabel.setText(slider.getValue() + ""));
		valueLabel.setLabelFor(slider);
		descriptionLabel.setLabelFor(slider);

		super.add(slider, BorderLayout.EAST);
		super.add(descriptionLabel, BorderLayout.NORTH);
		super.add(valueLabel, BorderLayout.WEST);
	}

	public void addChangeListener(ChangeListener changeListener)
	{
		slider.addChangeListener(changeListener);
	}

	public int getValue()
	{
		return slider.getValue();
	}
}
