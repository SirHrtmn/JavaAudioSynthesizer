package synth.mock;

import static org.mockito.Mockito.mock;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitOscillator;

import synth.circuits.OscillatorVoiceCircuit;

public class FakeOscillatorVoiceCircuit extends OscillatorVoiceCircuit
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

	public FilterLowPass getUnitLowPass()
	{
		return lowPass;
	}

	public FilterBandPass getUnitBandPass()
	{
		return bandPass;
	}

	public FilterHighPass getUnitHighPass()
	{
		return highPass;
	}

	public PassThrough getUnitOutput()
	{
		return output;
	}

	public static FakeOscillatorVoiceCircuit build()
	{
		UnitOscillator oscillatorMock = mock(UnitOscillator.class);
		oscillatorMock.amplitude = new UnitInputPort("ampl");
		oscillatorMock.frequency = new UnitInputPort("freq");
		oscillatorMock.output = new UnitOutputPort();
		return new FakeOscillatorVoiceCircuit(oscillatorMock);
	}
}
