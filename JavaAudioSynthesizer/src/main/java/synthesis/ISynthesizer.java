package synthesis;

import java.util.Optional;

import musical.Note;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;

public interface ISynthesizer
{
	public void noteOn(Note note);

	public void noteOff();

	public Optional<Note> isPlaying();

	public OscillatorType getOscillatorType();

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envConfig);

	public void applyFilterConfiguration(FilterConfiguration filterConfig);
}
