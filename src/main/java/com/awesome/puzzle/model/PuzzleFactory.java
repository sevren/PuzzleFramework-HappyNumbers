package com.awesome.puzzle.model;

/**
 * PuzzleFramework model - PuzzleFactory
 * 
 * Returns instanciated objects based on the enumerated type passed in.
 * Presently only supports the HappyNumbers puzzle.
 * Oct 25,2015
 * @author Kiril Tzvetanov Goguev
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awesome.puzzle.exceptions.BadRangeException;
import com.awesome.puzzle.exceptions.NotImplementedException;
import com.awesome.puzzle.solver.HappyNumbers;

public class PuzzleFactory  {
	
	private static final Logger logger = LogManager.getLogger(PuzzleFactory.class);
	
	//creation of puzzles based on parameters
	public static Puzzle getPuzzle(PuzzleTypes pt, int Start, int End) throws NotImplementedException, BadRangeException {
		logger.entry();
		switch (pt)
		{
			case HAPPYNUMBERS:

				logger.info("Attempting to create a {} puzzle with the following paramters: range [{}] ",pt.toString(),Start+"-"+End);
				logger.exit();
				return new HappyNumbers(Start,End);
				
			default:
				logger.info("Attempting to create a {} puzzle with the following paramters: range [{}] ",pt.toString(),Start+"-"+End);
				
				logger.exit();
				throw new NotImplementedException(pt.toString());
				
				
		}
	}
	
	
	

}
