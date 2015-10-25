package com.awesome.puzzle.tests.solver;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awesome.puzzle.exceptions.BadRangeException;
import com.awesome.puzzle.exceptions.NotImplementedException;
import com.awesome.puzzle.model.Puzzle;
import com.awesome.puzzle.model.PuzzleFactory;
import com.awesome.puzzle.model.PuzzleTypes;
import com.awesome.puzzle.solver.HappyNumbers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HappyNumbersTest {
	
	private Puzzle p;
	
	@Before
	public void setup() throws NotImplementedException, BadRangeException
	{
		 p = PuzzleFactory.getPuzzle(PuzzleTypes.HAPPYNUMBERS, 1, 1000);
		
	}
	
	@After
	public void teardown()
	{
		p=null; //no more references to the puzzle the java garbage collector will do its thing (at some point)
	}
	
	
	/*Testing the internal isHappy private method to double check that the numbers we pass in actually returns the proper true/false
		@Test
		public void TestCase_isHappy_happynumber() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
		{
			
			Method m = null;
			m = p.getClass().getDeclaredMethod("isHappy", int.class);
			m.setAccessible(true);
			Object[] parameters = new Object[1];
			parameters[0] = 31;
			Boolean b =(Boolean) m.invoke(p, parameters);
			assertTrue(parameters[0]+" is a happy number",b);
		}
		
		//Testing the internal isHappy private method to double check that the numbers we pass in actually returns the proper true/false
			@Test
			public void TestCase_isHappy_unhappynumber() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
			{
				
				Method m = null;
				m = p.getClass().getDeclaredMethod("isHappy", int.class);
				m.setAccessible(true);
				Object[] parameters = new Object[1];
				parameters[0] = 20;
				Boolean b =(Boolean) m.invoke(p, parameters);
				assertFalse(parameters[0]+" is not a happy number",b);
			}
		*/
	
	@Test
	public void TestCase_createHappyNumbers()
	{
		HappyNumbers hN=null;
		try {
			hN = new HappyNumbers(1, 1);
		} catch (BadRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		assertTrue( hN instanceof HappyNumbers);
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.BadRangeException.class)
	public void TestCase_createHappyNumbersBadRange() throws BadRangeException
	{
		HappyNumbers hN = new HappyNumbers(1000, 1);
		
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.BadRangeException.class)
	public void TestCase_createHappyNumbersBadRangeEnd() throws BadRangeException
	{
		HappyNumbers hN = new HappyNumbers(1, -1);
		
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.BadRangeException.class)
	public void TestCase_createHappyNumbersBadRangeBothNegative() throws BadRangeException
	{
		HappyNumbers hN = new HappyNumbers(-1, -1);
		
	}
	
	
	
	
	
	@Test
	public void TestCase_checkRealType()
	{
		PuzzleTypes expected=PuzzleTypes.HAPPYNUMBERS;
		PuzzleTypes actual=p.getType();
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestCase_getDescription()
	{
		String expected = "A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number either equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers, while those that do not end in 1 are unhappy numbers (or sad numbers). --Wikipedia";
		String actual = p.getPuzzleDescription();
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestCase_getInstanceDescription()
	{
		String expected ="HAPPYNUMBERS puzzle instance with range = [ 1 - 1000 ]";
		String actual = p.getInstanceDescription();
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestCase_getParameters()
	{
		String expected="range = [ 1 - 1000 ]";
		String actual=p.getParameters();
		assertEquals(expected,actual);
	}
	
	@Test
	public void TestCase_solve()
	{
		ArrayList<Integer> expected= new ArrayList<Integer>(Arrays.asList(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000));
		p.solve();
		ArrayList <Integer> actual =p.getSolution();
		assertEquals(expected,actual);
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.NotSolvedException.class)
	public void TestCase_solve_shouldThrowNotSolvedException()
	{
		ArrayList<Integer> expected= new ArrayList<Integer>(Arrays.asList(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000));
		ArrayList <Integer> actual =p.getSolution();
	}
	
	@Test
	public void Testcase_showSolution()
	{
		String expected="HappyNumbers:[1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000]";
		p.solve();
		String actual=p.showSolution();
		
		assertEquals(expected,actual);
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.NotSolvedException.class)
	public void Testcase_showSolution_shouldThrowNotSolvedException()
	{
		String expected="HappyNumbers:[1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000]";
		String actual=p.showSolution();
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void TestCase_checkSanitization()
	{
		int expected=1;
		int actual = p.sanitize(1);
		assertEquals (expected,actual);		
	}
	
	@Test
	public void TestCase_checkSanitizationNegativeNumber()
	{
		int expected=1;
		int actual=p.sanitize(-1);
		
		assertEquals (expected,actual);		
	}
	
	
	@Test
	public void TestCase_checkInitalArray() throws NotImplementedException, BadRangeException
	{
		//reinitalize puzzle p because I am not going to write out 1->1000 for unit testing..
		p = PuzzleFactory.getPuzzle(PuzzleTypes.HAPPYNUMBERS, 1, 8);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));
		ArrayList<Integer> actual =p.getInitalNumbers();
		assertArrayEquals( expected.toArray(), actual.toArray() );
	}
	
	@Test(expected=com.awesome.puzzle.exceptions.BadRangeException.class)
	public void TestCase_checkInitalArray_negative_initalization() throws NotImplementedException, BadRangeException
	{
		
		p = PuzzleFactory.getPuzzle(PuzzleTypes.HAPPYNUMBERS, -1, 8);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));
		ArrayList<Integer> actual =p.getInitalNumbers();
		assertArrayEquals( expected.toArray(), actual.toArray() );
	}
	
	
	

}
