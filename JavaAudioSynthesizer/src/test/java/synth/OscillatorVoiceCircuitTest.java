package synth;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

import synth.circuits.OscillatorVoiceCircuit;
import synth.configuration.EnvelopeConfiguration;

public class OscillatorVoiceCircuitTest
{
	private FakeOscillatorVoiceCircuit voiceCircuit;

	private class FakeOscillatorVoiceCircuit extends OscillatorVoiceCircuit
	{

		public FakeOscillatorVoiceCircuit(UnitOscillator oscillator)
		{
			super(oscillator);
		}

		public UnitOscillator getUnitOscillator()
		{
			return oscillator;
		}

		public EnvelopeDAHDSR getUnitEnvelope()
		{
			return envelope;
		}

		public PassThrough getUnitOutput()
		{
			return output;
		}
	}

	@Before
	public void setup()
	{
		UnitOscillator oscillatorMock = mock(UnitOscillator.class);
		oscillatorMock.amplitude = new UnitInputPort("ampl");
		oscillatorMock.frequency = new UnitInputPort("freq");
		oscillatorMock.output = new UnitOutputPort();
		voiceCircuit = new FakeOscillatorVoiceCircuit(new SineOscillator());
	}

	@Test
	public void testNoteOnAndOff()
	{
		double frequencyExpected = 12.34;
		double amplitudeExpected = 0.5;

		voiceCircuit.noteOn(frequencyExpected, amplitudeExpected);
		checkFrequency(frequencyExpected);
		checkAmplitude(amplitudeExpected);

		voiceCircuit.noteOff();
		checkAmplitude(0.0);
	}

	@Test
	public void testEnvelopeConfiguration()
	{
		for (int i = 0; i < 3; i++)
		{
			double value = 0.5 * i;
			testEnvelopeAttack(value);
			testEnvelopeDecay(value);
			testEnvelopeDelay(value);
			testEnvelopeHold(value);
			testEnvelopeRelease(value);
		}
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

	private void checkAmplitude(double amplitudeExpected)
	{
		double amplitudeActual = voiceCircuit.getUnitEnvelope().input.get();
		Assert.assertEquals(amplitudeExpected, amplitudeActual, 0.0001);
	}

	private void checkFrequency(double frequencyExpected)
	{
		double frequencyActual = voiceCircuit.getUnitOscillator().frequency.get();
		Assert.assertEquals(frequencyExpected, frequencyActual, 0.0001);
	}

}
