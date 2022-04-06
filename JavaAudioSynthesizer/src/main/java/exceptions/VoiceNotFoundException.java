package exceptions;

public class VoiceNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public VoiceNotFoundException(String message)
	{
		super(message);
	}

	public VoiceNotFoundException()
	{
		super();
	}
}
