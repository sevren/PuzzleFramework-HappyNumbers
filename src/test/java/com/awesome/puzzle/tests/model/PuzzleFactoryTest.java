package com.awesome.puzzle.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.puzzle.exceptions.BadRangeException;
import com.awesome.puzzle.exceptions.NotImplementedException;
import com.awesome.puzzle.model.Puzzle;
import com.awesome.puzzle.model.PuzzleFactory;
import com.awesome.puzzle.model.PuzzleTypes;
import com.awesome.puzzle.solver.HappyNumbers;

public class PuzzleFactoryTest {
	
	@Test
	public void TestCase_createFactoryClassInstance()
	{
		PuzzleFactory pz= new PuzzleFactory();
		assertTrue (pz instanceof PuzzleFactory);
	}
	
	@Test
	public void createHappyNumbersPuzzle() throws NotImplementedException, BadRangeException
	{
		Puzzle p = PuzzleFactory.getPuzzle(PuzzleTypes.HAPPYNUMBERS, 1, 1000);
		assertTrue(p instanceof HappyNumbers);
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.NotImplementedException.class)
	public void createUnImplementedPuzzle() throws NotImplementedException, BadRangeException
	{
		
		Puzzle p = PuzzleFactory.getPuzzle(PuzzleTypes.QLEARNING_GRID, 1, 1000);
		assertFalse(p instanceof HappyNumbers);
	}
	
	

}
