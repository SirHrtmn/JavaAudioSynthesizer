package synth;

import org.junit.Assert;
import org.junit.Test;

import synth.configuration.FilterConfiguration;
import synth.mock.FakeOscillatorVoiceCircuit;

public class FilterConfigurationTest
{
	private FakeOscillatorVoiceCircuit voiceCircuit = FakeOscillatorVoiceCircuit.build();

	@Test
	public void testFilterConfiguration()
	{
		double value = 0.0;
		testFilterAmplitude(value);
		testHighPassFilter(value);
		testBandPassFilter(value);
		testLowPassFilter(value);
	}

	private void testHighPassFilter(double value)
	{
		FilterConfiguration filterConfig = new FilterConfiguration(0.0, 0.0, 0.0, 0.0);
		filterConfig.setHighPass(value);
		voiceCircuit.applyFilterConfiguration(filterConfig);

		Assert.assertEquals(value, voiceCircuit.getUnitHighPass().frequency.get(), 0.0001);
	}

	private void testBandPassFilter(double value)
	{
		FilterConfiguration filterConfig = new FilterConfiguration(0.0, 0.0, 0.0, 0.0);
		filterConfig.setBandPass(value);
		voiceCircuit.applyFilterConfiguration(filterConfig);

		Assert.assertEquals(value, voiceCircuit.getUnitBandPass().frequency.get(), 0.0001);
	}

	private void testLowPassFilter(double value)
	{
		FilterConfiguration filterConfig = new FilterConfiguration(0.0, 0.0, 0.0, 0.0);
		filterConfig.setLowPass(value);
		voiceCircuit.applyFilterConfiguration(filterConfig);

		Assert.assertEquals(value, voiceCircuit.getUnitLowPass().frequency.get(), 0.0001);
	}

	private void testFilterAmplitude(double value)
	{
		FilterConfiguration filterConfig = new FilterConfiguration(0.0, 0.0, 0.0, 0.0);
		filterConfig.setAmplitude(value);
		voiceCircuit.applyFilterConfiguration(filterConfig);

		Assert.assertEquals(value, voiceCircuit.getUnitBandPass().amplitude.get(), 0.0001);
		Assert.assertEquals(value, voiceCircuit.getUnitLowPass().amplitude.get(), 0.0001);
		Assert.assertEquals(value, voiceCircuit.getUnitHighPass().amplitude.get(), 0.0001);
	}
}
