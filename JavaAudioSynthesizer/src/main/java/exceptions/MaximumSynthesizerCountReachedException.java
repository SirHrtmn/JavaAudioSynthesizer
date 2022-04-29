package exceptions;

public class MaximumSynthesizerCountReachedException extends RuntimeException
{

	public MaximumSynthesizerCountReachedException()
	{
		super();
	}

	public MaximumSynthesizerCountReachedException(String message)
	{
		super(message);
	}
}
