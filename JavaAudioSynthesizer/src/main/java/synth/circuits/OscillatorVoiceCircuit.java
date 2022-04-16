package synth.circuits;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public class OscillatorVoiceCircuit extends Circuit implements FilterEnvelopeVoice
{
	private UnitOscillator oscillator;
	private EnvelopeDAHDSR envelope;
	private FilterBandPass bandPass;
	private FilterLowPass lowPass;
	private FilterHighPass highPass;
	private PassThrough passThrough;
	private PassThrough output;

	public OscillatorVoiceCircuit(UnitOscillator oscillator)
	{
	}

	@Override
	public void noteOff(TimeStamp timeStamp)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noteOn(double frequency, double amplitude, TimeStamp timeStamp)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnitOutputPort getOutput()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void noteOn(double frequency, double amplitude)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void noteOff()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyEnvelopeConfiguration(EnvelopeConfiguration envelopeConfig)
	{
		// TODO Auto-generated method stub
		
	}
}
