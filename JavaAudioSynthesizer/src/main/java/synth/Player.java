package synth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import exceptions.SynthesizerNotFoundException;
import gui.listeners.NoteReleaseListener;
import musical.Note;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.keyboard.PlayState;
import synthesis.ISynthesizer;
import synthesis.OscillatorType;
import synthesis.SynthesizerFactory;

public class Player
{
	private static final int DEFAULT_MAX_VOICES = 4;

	private Map<ISynthesizer, PlayState> synthPlayStatePairs = new LinkedHashMap<>();
	private List<NoteReleaseListener> noteReleaseListeners = new ArrayList<>();

	public Player(OscillatorType type)
	{
		prepareSynthesizers(type, DEFAULT_MAX_VOICES);
	}

	public void setOscillatorType(OscillatorType type)
	{
		for (Entry<ISynthesizer, PlayState> playStateEntry : synthPlayStatePairs.entrySet())
		{
			PlayState playState = playStateEntry.getValue();
			if (playState.isPlaying())
			{
				noteOff(playState.getNote());
			}
		}

		synthPlayStatePairs.clear();
		prepareSynthesizers(type, DEFAULT_MAX_VOICES);
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		getSynthesizers().forEach(synth -> synth.applyFilterConfiguration(filterConfig));
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envConfig)
	{
		getSynthesizers().forEach(synth -> synth.applyEnvelopeConfiguration(envConfig));
	}

	public void noteOn(Note note)
	{
		Optional<ISynthesizer> optionalSynth = getUnplayedSynth();
		if (optionalSynth.isEmpty())
		{
			releaseOldestSynth();
			optionalSynth = getUnplayedSynth();
		}

		if (optionalSynth.isEmpty())
		{
			String message = "Could not find an unplayed synth, neither release a played synth";
			throw new SynthesizerNotFoundException(message);
		}

		ISynthesizer synth = optionalSynth.get();
		synth.noteOn(note);
		synthPlayStatePairs.get(synth).setPlayingOn(note);
	}

	public void noteOff(Note note)
	{
		Optional<ISynthesizer> optionalSynth = getSynthForNote(note);
		if (optionalSynth.isEmpty())
		{
			return;
		}

		ISynthesizer synth = optionalSynth.get();
		synth.noteOff();
		synthPlayStatePairs.get(synth).setPlayingOff();
	}

	public void addNoteReleaseListener(NoteReleaseListener listener)
	{
		noteReleaseListeners.add(listener);
	}

	private void triggerNoteReleaseListeners(Note note)
	{
		noteReleaseListeners.forEach(listener -> listener.noteReleased(note));
	}

	private Optional<ISynthesizer> getUnplayedSynth()
	{
		for (Entry<ISynthesizer, PlayState> entry : synthPlayStatePairs.entrySet())
		{
			if (!entry.getValue().isPlaying())
			{
				return Optional.of(entry.getKey());
			}
		}
		return Optional.empty();
	}

	private Optional<ISynthesizer> getSynthForNote(Note note)
	{
		for (Entry<ISynthesizer, PlayState> entry : synthPlayStatePairs.entrySet())
		{
			PlayState playState = entry.getValue();
			if (playState.getNote() == note && playState.isPlaying())
			{
				return Optional.of(entry.getKey());
			}
		}
		return Optional.empty();
	}

	private void releaseOldestSynth()
	{
		Optional<ISynthesizer> oldestVoiceOptional = getOldestSynth();
		if (oldestVoiceOptional.isPresent())
		{
			ISynthesizer oldestVoice = oldestVoiceOptional.get();
			Note noteToRelease = synthPlayStatePairs.get(oldestVoice).getNote();
			triggerNoteReleaseListeners(noteToRelease);
			noteOff(noteToRelease);
		}
	}

	private Optional<ISynthesizer> getOldestSynth()
	{
		Comparator<? super ISynthesizer> comparator = (synthA, synthB) -> {
			Date startTimeA = synthPlayStatePairs.get(synthA).getStartTime();
			Date startTimeB = synthPlayStatePairs.get(synthB).getStartTime();
			return startTimeA.compareTo(startTimeB);
		};

		Stream<ISynthesizer> sortedVoices = getSynthesizers().stream().sorted(comparator);
		return sortedVoices.findFirst();
	}

	private List<ISynthesizer> getSynthesizers()
	{
		return synthPlayStatePairs.keySet().stream().toList();
	}

	private void prepareSynthesizers(OscillatorType type, int maximumVoices)
	{
		for (int i = 0; i < maximumVoices; i++)
		{
			ISynthesizer newSynth = SynthesizerFactory.build(type);
			synthPlayStatePairs.put(newSynth, new PlayState());
		}
	}

}
