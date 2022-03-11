package synth.configuration;

import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.TunableFilter;

public class FilterConfiguration
{

	private double frequency;
	private FilterType filterType;

	public FilterConfiguration(double frequency, FilterType filterType)
	{
		super();
		this.frequency = frequency;
		this.filterType = filterType;
	}

	public double getFrequency()
	{
		return frequency;
	}

	public void setFrequency(double frequency)
	{
		this.frequency = frequency;
	}

	public FilterType getFilterType()
	{
		return filterType;
	}

	public void setFilterType(FilterType filterType)
	{
		this.filterType = filterType;
	}

	public enum FilterType
	{
		BAND_PASS(() -> new FilterBandPass()), 
		HIGH_PASS(() -> new FilterHighPass()), 
		LOW_PASS(() -> new FilterLowPass());

		private FilterFactory filterFactory;

		FilterType(FilterFactory filterFactory)
		{
			this.filterFactory = filterFactory;
		}

		public TunableFilter getNewFilter()
		{
			return filterFactory.build();
		}
	}
}
