package synth.configuration;

public class FilterConfiguration
{
	private double highPass;
	private double bandPass;
	private double lowPass;

	public FilterConfiguration(double highPass, double bandPass, double lowPass)
	{
		super();
		this.highPass = highPass;
		this.bandPass = bandPass;
		this.lowPass = lowPass;
	}

	public double getHighPass()
	{
		return highPass;
	}

	public void setHighPass(double highPass)
	{
		this.highPass = highPass;
	}

	public double getBandPass()
	{
		return bandPass;
	}

	public void setBandPass(double bandPass)
	{
		this.bandPass = bandPass;
	}

	public double getLowPass()
	{
		return lowPass;
	}

	public void setLowPass(double lowPass)
	{
		this.lowPass = lowPass;
	}
}
