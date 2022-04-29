package configuration;

public class FilterConfiguration
{

	private double frequency;
	private double resonance;
	private double amplitude;

	public FilterConfiguration(double frequency, double resonance, double amplitude)
	{
		this.setFrequency(frequency);
		this.setResonance(resonance);
		this.setAmplitude(amplitude);
	}

	public double getAmplitude()
	{
		return amplitude;
	}

	public void setAmplitude(double amplitude)
	{
		this.amplitude = amplitude;
	}

	public double getResonance()
	{
		return resonance;
	}

	public void setResonance(double resonance)
	{
		this.resonance = resonance;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public void setFrequency(double frequency)
	{
		this.frequency = frequency;
	}
}
