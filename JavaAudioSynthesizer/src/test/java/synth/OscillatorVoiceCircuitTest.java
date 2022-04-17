package synth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import synth.mock.FakeOscillatorVoiceCircuit;

public class OscillatorVoiceCircuitTest
{
	private FakeOscillatorVoiceCircuit voiceCircuit;

	@Before
	public void setup()
	{
		voiceCircuit = FakeOscillatorVoiceCircuit.build();
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
