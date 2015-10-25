package com.awesome.puzzle.exceptions;

/**
 * PuzzleFramework Custom Exceptions - NotSolvedException
 * Simple exception class throwing a error message when the user has tried to get a solution or show the solution if the puzzle has not yet been solved.
 * Oct 25, 2015
 * @author Kiril Tzvetanov Goguev 
 *
 */

public class NotSolvedException extends RuntimeException {
	
	
	private static final long serialVersionUID = 8930087126752926416L;

	public NotSolvedException()
	{
		super("You must call the solve() method on the instanciated puzzle before showing or getting a solution!");
	}

}
