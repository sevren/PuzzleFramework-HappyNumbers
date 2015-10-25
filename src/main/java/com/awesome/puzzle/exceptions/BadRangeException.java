package com.awesome.puzzle.exceptions;

/**
 * PuzzleFramework Custom Exceptions - BadRangeException
 * Generally thrown when a puzzle requires ranges.. i,e for array boundries or grid boundries.
 * Oct 25, 2015
 * @author Kiril Tzvetanov Goguev
 *
 */

public class BadRangeException extends Exception {

	private static final long serialVersionUID = 5712652252204305174L;
	
	public BadRangeException(String message)
	{
		super(message);
	}

}
