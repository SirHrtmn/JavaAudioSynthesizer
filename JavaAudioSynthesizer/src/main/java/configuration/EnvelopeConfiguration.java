package configuration;

public class EnvelopeConfiguration
{
	private double delay;
	private double attack;
	private double decay;
	private double hold;
	private double release;

	public EnvelopeConfiguration(double delay, double attack, double decay, double hold,
			double release)
	{
		super();
		this.delay = delay;
		this.attack = attack;
		this.decay = decay;
		this.hold = hold;
		this.release = release;
	}

	public double getDelay()
	{
		return delay;
	}

	public void setDelay(double delay)
	{
		this.delay = delay;
	}

	public double getAttack()
	{
		return attack;
	}

	public void setAttack(double attack)
	{
		this.attack = attack;
	}

	public double getDecay()
	{
		return decay;
	}

	public void setDecay(double decay)
	{
		this.decay = decay;
	}

	public double getHold()
	{
		return hold;
	}

	public void setHold(double hold)
	{
		this.hold = hold;
	}

	public double getRelease()
	{
		return release;
	}

	public void setRelease(double release)
	{
		this.release = release;
	}
}
