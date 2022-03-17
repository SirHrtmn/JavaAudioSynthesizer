package gui;

import javax.swing.event.ChangeListener;

import synth.configuration.FilterConfiguration;

public class FilterConfigurationPanel extends ConfigurationPanel
{
	private static final int rowCount = 4;
	private static final int columnCount = 1;

	private static final long serialVersionUID = 1L;

	private VariableSlider lowPassSlider;
	private VariableSlider bandPassSlider;
	private VariableSlider highPassSlider;
	private VariableSlider amplitudeSlider;

	private FilterConfiguration filterConfig;

	public FilterConfigurationPanel(FilterConfiguration filterConfiguration)
	{
		super(rowCount, columnCount);
		this.filterConfig = filterConfiguration;
		lowPassSlider = getNewSlider("LowPass", 0, 2500, filterConfig.getLowPass());
		bandPassSlider = getNewSlider("BandPass", 0, 2500, filterConfig.getBandPass());
		highPassSlider = getNewSlider("HighPass", 0, 2500, filterConfig.getHighPass());
		amplitudeSlider = getNewSlider("Filter Amplitude", 0, 1, filterConfig.getAmplitude());

		super.add(lowPassSlider);
		super.add(bandPassSlider);
		super.add(highPassSlider);
		super.add(amplitudeSlider);

		ChangeListener changeListener = e -> applyConfiguration();
		lowPassSlider.addChangeListener(changeListener);
		bandPassSlider.addChangeListener(changeListener);
		highPassSlider.addChangeListener(changeListener);
		amplitudeSlider.addChangeListener(changeListener);
	}

	protected void applyConfiguration()
	{
		updateConfiguration();
		triggerChangeListeners();
	}

	protected void updateConfiguration()
	{
		filterConfig.setAmplitude(amplitudeSlider.getValue());
		filterConfig.setLowPass(lowPassSlider.getValue());
		filterConfig.setBandPass(bandPassSlider.getValue());
		filterConfig.setHighPass(highPassSlider.getValue());
	}

	public FilterConfiguration getConfig()
	{
		return filterConfig;
	}
}
