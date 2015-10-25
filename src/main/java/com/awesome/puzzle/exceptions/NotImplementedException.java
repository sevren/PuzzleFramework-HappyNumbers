package com.awesome.puzzle.exceptions;

/**
 * PuzzleFramework Custom Exceptions - NotImplementedException
 * Simple Exception class that is thrown when the user tries to create a puzzle type that I have not yet gotten around to implementing.
 * Oct 25, 2015
 * @author Kiril Tzvetanov Goguev
 *
 */
public class NotImplementedException extends RuntimeException{
	
	
	private static final long serialVersionUID = -4115893038372634429L;
	
	public NotImplementedException(String message)
	{
		super (message+" is not yet implemented");
		
	}

}
