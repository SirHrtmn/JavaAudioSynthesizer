package synth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;

import exceptions.VoiceNotFoundException;
import gui.listeners.NoteReleaseListener;
import musical.Note;
import synth.circuits.FilterEnvelopeVoice;
import synth.circuits.VoiceCircuits;
import synth.configuration.EnvelopeConfiguration;
import synth.configuration.FilterConfiguration;
import synth.keyboard.PlayState;

public class SynthesizerPlayer
{
	private static final int DEFAULT_MAX_VOICES = 4;

	private Map<Synthesizer, FilterEnvelopeVoice> synthVoicePairs = new HashMap<>();
	private Map<FilterEnvelopeVoice, PlayState> playStates = new LinkedHashMap<>();
	private List<NoteReleaseListener> noteReleaseListeners = new ArrayList<>();

	public SynthesizerPlayer(VoiceCircuits voiceCircuit)
	{
		prepareSynthesizers(voiceCircuit, DEFAULT_MAX_VOICES);
		addShutdownHook();
	}

	public void setUnitVoice(VoiceCircuits voiceCircuit)
	{
		for (Entry<FilterEnvelopeVoice, PlayState> playStateEntry : playStates.entrySet())
		{
			PlayState playState = playStateEntry.getValue();
			if (playState.isPlaying())
			{
				noteOff(playState.getNote());
			}
		}

		synthVoicePairs.clear();
		playStates.clear();
		prepareSynthesizers(voiceCircuit, DEFAULT_MAX_VOICES);
	}

	public void applyFilterConfiguration(FilterConfiguration filterConfig)
	{
		synthVoicePairs.values().forEach(voice -> voice.applyFilterConfiguration(filterConfig));
	}

	public void applyEnvelopeConfiguration(EnvelopeConfiguration envConfig)
	{
		synthVoicePairs.values().forEach(voice -> voice.applyEnvelopeConfiguration(envConfig));
	}

	public void noteOn(Note note, double amplitude)
	{
		Optional<FilterEnvelopeVoice> optionalVoiceUnit = getUnplayedVoice();
		if (optionalVoiceUnit.isEmpty())
		{
			releaseOldestVoice();
			optionalVoiceUnit = getUnplayedVoice();
		}

		if (optionalVoiceUnit.isEmpty())
		{
			throw new VoiceNotFoundException();
		}

		FilterEnvelopeVoice voiceUnit = optionalVoiceUnit.get();
		voiceUnit.noteOn(note.getFrequency(), amplitude);

		playStates.get(voiceUnit).setPlayingOn(note);
	}

	public void noteOn(Note note)
	{
		noteOn(note, 1.0);
	}

	public void noteOff(Note note)
	{
		Optional<FilterEnvelopeVoice> optionalVoice = getVoiceForNote(note);
		if (optionalVoice.isEmpty())
		{
			return;
		}

		FilterEnvelopeVoice voice = optionalVoice.get();
		voice.noteOff();
		playStates.get(voice).setPlayingOff();
	}

	public void addNoteReleaseListener(NoteReleaseListener listener)
	{
		noteReleaseListeners.add(listener);
	}

	private void triggerNoteReleaseListeners(Note note)
	{
		noteReleaseListeners.forEach(listener -> listener.noteReleased(note));
	}

	private Optional<FilterEnvelopeVoice> getUnplayedVoice()
	{
		for (Entry<FilterEnvelopeVoice, PlayState> playStateEntry : playStates.entrySet())
		{
			if (!playStateEntry.getValue().isPlaying())
			{
				return Optional.of(playStateEntry.getKey());
			}
		}
		return Optional.empty();
	}

	private Optional<FilterEnvelopeVoice> getVoiceForNote(Note note)
	{
		for (Entry<FilterEnvelopeVoice, PlayState> playStateEntry : playStates.entrySet())
		{
			PlayState playState = playStateEntry.getValue();
			if (playState.getNote() == note && playState.isPlaying())
			{
				return Optional.of(playStateEntry.getKey());
			}
		}
		return Optional.empty();
	}

	private void releaseOldestVoice()
	{
		Optional<FilterEnvelopeVoice> oldestVoiceOptional = getOldestVoice();
		if (oldestVoiceOptional.isPresent())
		{
			FilterEnvelopeVoice oldestVoice = oldestVoiceOptional.get();
			Note noteToRelease = playStates.get(oldestVoice).getNote();
			triggerNoteReleaseListeners(noteToRelease);
			noteOff(noteToRelease);
		}
	}

	private Optional<FilterEnvelopeVoice> getOldestVoice()
	{
		Comparator<? super FilterEnvelopeVoice> comparator = (voice1, voice2) -> {
			Date voice1StartTime = playStates.get(voice1).getStartTime();
			Date voice2StartTime = playStates.get(voice2).getStartTime();
			return voice1StartTime.compareTo(voice2StartTime);
		};

		Stream<FilterEnvelopeVoice> sortedVoices = playStates.keySet().stream().sorted(comparator);
		return sortedVoices.findFirst();
	}

	private void addShutdownHook()
	{
		Runnable shutdownRunnable = () -> synthVoicePairs.keySet().forEach(Synthesizer::stop);
		Runtime.getRuntime().addShutdownHook(new Thread(shutdownRunnable));
	}

	private void prepareSynthesizers(VoiceCircuits voiceCircuit, int maximumVoices)
	{
		for (int i = 0; i < maximumVoices; i++)
		{
			FilterEnvelopeVoice unitVoice = voiceCircuit.getUnit();
			Synthesizer synth = createSynthesizer(unitVoice);
			synthVoicePairs.put(synth, unitVoice);
			playStates.put(unitVoice, new PlayState());
		}
	}

	private Synthesizer createSynthesizer(FilterEnvelopeVoice unitVoice)
	{
		Synthesizer synthesizer = JSyn.createSynthesizer();
		LineOut lineOut = new LineOut();

		synthesizer.setRealTime(true);
		synthesizer.add(unitVoice.getUnitGenerator());
		synthesizer.add(lineOut);

		unitVoice.getOutput().connect(0, lineOut.input, 0);
		unitVoice.getOutput().connect(0, lineOut.input, 1);

		synthesizer.start();
		synthesizer.startUnit(lineOut);

		return synthesizer;
	}
}
