package synth.configuration;

public class FilterConfiguration
{

	private double lowPass;
	private double bandPass;
	private double highPass;

	public FilterConfiguration(double lowPass, double bandPass, double highPass)
	{
		super();
		this.lowPass = lowPass;
		this.bandPass = bandPass;
		this.highPass = highPass;
	}

	public double getLowPass()
	{
		return lowPass;
	}

	public void setLowPass(double lowPass)
	{
		this.lowPass = lowPass;
	}

	public double getBandPass()
	{
		return bandPass;
	}

	public void setBandPass(double bandPass)
	{
		this.bandPass = bandPass;
	}

	public double getHighPass()
	{
		return highPass;
	}

	public void setHighPass(double highPass)
	{
		this.highPass = highPass;
	}
}
