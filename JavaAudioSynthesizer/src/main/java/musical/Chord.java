package musical;

import controlling.Player;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import utils.TimeUtils;

@XmlRootElement
public class Chord implements Playable
{
	@XmlElement
	private long duration;
	@XmlElement
	private Note[] notesToPlay;

	public Chord(long durationInMillis, Note... notes)
	{
		this.duration = durationInMillis;
		notesToPlay = notes;
	}

	@Override
	public void play(Player player)
	{
		for (Note note : notesToPlay)
		{
			player.noteOn(note);
		}
		TimeUtils.waitForASpecifiedTime(duration);
		for (Note note : notesToPlay)
		{
			player.noteOff(note);
		}
	}

}
