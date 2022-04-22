package synth;

import org.junit.Assert;
import org.junit.Test;

import synth.configuration.EnvelopeConfiguration;
import synth.mock.FakeOscillatorVoiceCircuit;

public class EnvelopeConfigurationTest
{
	private FakeOscillatorVoiceCircuit voiceCircuit = FakeOscillatorVoiceCircuit.build();

	@Test
	public void testEnvelopeConfiguration()
	{
		testEnvelopeConfigurationWithValue(0.0);
		testEnvelopeConfigurationWithValue(0.5);
		testEnvelopeConfigurationWithValue(1.0);
	}

	private void testEnvelopeConfigurationWithValue(double value)
	{
		testEnvelopeAttack(value);
		testEnvelopeDecay(value);
		testEnvelopeDelay(value);
		testEnvelopeHold(value);
		testEnvelopeRelease(value);
	}

	private void testEnvelopeAttack(double value)
	{
		EnvelopeConfiguration envConfig = new EnvelopeConfiguration(0.0, 0.0, 0.0, 0.0, 0.0);
		envConfig.setAttack(value);
		voiceCircuit.applyEnvelopeConfiguration(envConfig);
		Assert.assertEquals(value, voiceCircuit.getUnitEnvelope().attack.get(), 0.001);
	}

	private void testEnvelopeDelay(double value)
	{
		EnvelopeConfiguration envConfig = new EnvelopeConfiguration(0.0, 0.0, 0.0, 0.0, 0.0);
		envConfig.setDelay(value);
		voiceCircuit.applyEnvelopeConfiguration(envConfig);
		Assert.assertEquals(value, voiceCircuit.getUnitEnvelope().delay.get(), 0.001);
	}

	private void testEnvelopeDecay(double value)
	{
		EnvelopeConfiguration envConfig = new EnvelopeConfiguration(0.0, 0.0, 0.0, 0.0, 0.0);
		envConfig.setDecay(value);
		voiceCircuit.applyEnvelopeConfiguration(envConfig);
		Assert.assertEquals(value, voiceCircuit.getUnitEnvelope().decay.get(), 0.001);
	}

	private void testEnvelopeHold(double value)
	{
		EnvelopeConfiguration envConfig = new EnvelopeConfiguration(0.0, 0.0, 0.0, 0.0, 0.0);
		envConfig.setHold(value);
		voiceCircuit.applyEnvelopeConfiguration(envConfig);
		Assert.assertEquals(value, voiceCircuit.getUnitEnvelope().hold.get(), 0.001);
	}

	private void testEnvelopeRelease(double value)
	{
		EnvelopeConfiguration envConfig = new EnvelopeConfiguration(0.0, 0.0, 0.0, 0.0, 0.0);
		envConfig.setRelease(value);
		voiceCircuit.applyEnvelopeConfiguration(envConfig);
		Assert.assertEquals(value, voiceCircuit.getUnitEnvelope().release.get(), 0.001);
	}

}
