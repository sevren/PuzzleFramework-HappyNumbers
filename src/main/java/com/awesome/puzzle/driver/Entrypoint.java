package com.awesome.puzzle.driver;

/**
 * Puzzle Framework Driver
 * Presently only has support for creating a puzzle of type HappyNumbers and solving it.
 * Oct 25th,2015
 * @author Kiril Tzvetanov Goguev
 */


import com.awesome.puzzle.exceptions.BadRangeException;
import com.awesome.puzzle.exceptions.NotImplementedException;
import com.awesome.puzzle.model.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Entrypoint {
	
	
	private static final Logger logger = LogManager.getLogger(Entrypoint.class);
	public static final String  DescriptionFile="src/main/resources/PuzzleDescriptions.xml";
	
	
public static void main(String[] args) {
		logger.entry();
		
		Puzzle puzzle = null;
		try
		{
			//Create the type of puzzle
			puzzle = PuzzleFactory.getPuzzle(PuzzleTypes.HAPPYNUMBERS, 1, 1000);
		}
		catch (NotImplementedException e)
		{
			logger.error(e.getMessage());
		} catch (BadRangeException e) {
			logger.error(e.getMessage());
		}
		
		if (puzzle != null)
		{
		
			logger.info(puzzle.getPuzzleDescription());
			logger.info("solving..");
			puzzle.solve();
			logger.info("----Solution ----");
			logger.info("For:"+puzzle.getInstanceDescription());
			logger.info(puzzle.showSolution());
		}
		logger.exit();
	
	}

}
