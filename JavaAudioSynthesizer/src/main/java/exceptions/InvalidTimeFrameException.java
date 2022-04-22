package exceptions;

public class InvalidTimeFrameException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public InvalidTimeFrameException()
	{
		super();
	}

	public InvalidTimeFrameException(String message)
	{
		super(message);
	}
}
