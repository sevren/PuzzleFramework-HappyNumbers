package com.awesome.puzzle.solver;

/**
 * PuzzleFramework solver - HappyNumbers
 * Implements the HappyNumbers puzzle from wikipedia
 * 
 * This class should never be called directly, use the PuzzleFactory.
 * Since it is a puzzle it requires the solve() method to be called before the show or getSolution() methods.
 * 
 * Cobertura coverage: 89% -- Tested private methods through normal work flow (no Java reflection)
 * Oct 25,2015
 * @ Kiril Tzvetanov Goguev
 */

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.awesome.puzzle.exceptions.BadRangeException;
import com.awesome.puzzle.exceptions.NotSolvedException;
import com.awesome.puzzle.model.*;

public class HappyNumbers implements Puzzle {
	
	private static PuzzleTypes type=PuzzleTypes.HAPPYNUMBERS;
	
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static XPathFactory xPathfactory = XPathFactory.newInstance();
	
	private static final String DescriptionFile = com.awesome.puzzle.driver.Entrypoint.DescriptionFile;
	private static final Logger logger = LogManager.getLogger(HappyNumbers.class);
	
	private ArrayList<Integer> numbers= new ArrayList<Integer>();
	private ArrayList<Integer> happyNumbers = new ArrayList<Integer>();
	
	private int Start=0;
	private int End=0;
	private boolean solved=false;
	
	public HappyNumbers(int Start,int End) throws BadRangeException
	{
		logger.entry();
		
		
		if (Start < 0  || End < 0 )
		{
			logger.error("Range numbers must be positive");
			throw new BadRangeException("Parameters: Start "+Start+ " End "+End);
		}
		
		
		if (End < Start)
		{
			logger.error("End range can not be smaller than the Start range");
			throw new BadRangeException("End range: "+End+" can not be smaller than the Start range: "+Start);
		}
		

		this.Start=sanitize(Start);
		this.End=sanitize(End);
		logger.debug("Creating a default HappyNumbers Puzzle with range {}-{}",Start,End);
		fillArray(this.Start,this.End);
		logger.exit();
	}
	
	/**
	 * Retrieves the PuzzleDescription specifically for HappyNumbers from the resources XML file PuzzleDescriptions.xml
	 */
	public String getPuzzleDescription()
	{
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(DescriptionFile);
			String puzzleDescription = null;
			logger.debug(DescriptionFile);
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/Puzzles/HappyNumbers/Description/text()");
			puzzleDescription =(String) expr.evaluate(doc, XPathConstants.STRING);
			
			return puzzleDescription.trim();
					
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "<Description Missing!>";
	}
	
	/**
	 * Returns the current object instance configuration.. type of puzzle and the parameters given to it before solving.
	 */
	public String getInstanceDescription()
	{
		String instanceDescription=PuzzleTypes.HAPPYNUMBERS.toString()+" puzzle instance with "+getParameters();
		
		return instanceDescription.trim();
	}
	
	/**
	 * Returns only the parameters used to configure the puzzle.
	 */
	public String getParameters() {

		return "range = [ "+Start+" - "+End+" ]";
	}

	public PuzzleTypes getType() {
		
		return HappyNumbers.type;
	}
	
	
	
	/**
	 * Iteratively solve the puzzle
	 */
	public void solve()
	{
		
		ListIterator<Integer> iter = this.numbers.listIterator();
	    while (iter.hasNext()) {
	    	int num = iter.next();
	    	if (isHappy(num))
	    		happyNumbers.add(num);
	    }
		this.solved=true;
		
	}
	
	/**
	 * Print out the solution to the user, MUST CALL solve() METHOD BEFORE IT IS USABLE!
	 */
	public String showSolution() throws NotSolvedException
	{
		if (this.solved)
		{
			logger.debug("Found happy numbers: {}",getHappyNumbers());
			return ("HappyNumbers:"+getHappyNumbers()).trim();
		}
		else
		{
			throw new NotSolvedException();
		}
	}
	
	/**
	 * Returns the solution to the user, MUST CALL solve() BEFORE THIS IS USABLE
	 */
	public ArrayList<Integer> getSolution() throws NotSolvedException
	{
		if (this.solved)
		{
			return getHappyNumbers();
		}
		throw new NotSolvedException();
	}
	
	/**
	 * Returns the Initalized Arraylist for the solver.
	 */
	public ArrayList<Integer> getInitalNumbers()
	{
		return this.numbers;
	}
	
	/**
	 * We must always sanitize users input, they tend to try really bad things or not follow instructions and break your program.
	 * TODO: Probably should extract this to a utils class
	 */
	public int sanitize(int number)
	{
		return Math.abs(number);
	}
	
	/**
	 * This function checks if the number is happy, uses a trick to speed up execution since we don't explicitly have to check for a cycle (thanks wikipedia!)
	 * Another way to detect cycles is to use a set as they do not allow duplicate numbers and just insert when testing the sum, but then the issue is that you have to keep a extra collection variable and refer to it as well as
	 * handling those exceptions.. 
	 * 
	 */
	private boolean isHappy(int num)
	{ 
		
		int sum=num;
		
		while (sum!=1)
		{
			if (happyNumbers.contains(sum))
			{
				return true;
			}
				if (sum==4) //since we know a cycle includes 4 why not just exit immediately?
				{
					return false;
				}
				
			
			sum=getSumOfSquares(sum,0); // calc the sum
		}
		
		return true;
	}
	
	/**
	 * This function gets the sum of the squares.
	 * Any number sent in will use modulo to split the digits in the number .. so 49 mod 10 gives 9.. and subsequently dividing that number by 10 will give the next digit
	 * Another way to do this would be using Java's string and then split + parse but it is a naive way of doing it.
	 */
	private int getSumOfSquares(int n,int sum)
	{
			
			int extractedDigit=0;
			
			if (n>0)
			{
				extractedDigit=n%10;
				sum+=extractedDigit*extractedDigit;
				return getSumOfSquares(n/10,sum);
			}
			return sum;
			
	}
	
	/**
	 * Returns the happynumbers found, this is only specific to the HappyNumbers puzzle.
	 * @return
	 */
	private ArrayList<Integer> getHappyNumbers()
	{
		return this.happyNumbers;
	}
	
	/**
	 * Fills the ArrayList with numbers from the puzzle configuration paramters Start - End range.
	 * TODO: probably should extract this to a utils class.
	 */
	private void fillArray(int Start, int End)
	{
		logger.entry();
		logger.debug("filling array with values {} - {}",Start,End);
		for(int i=Start;i<End+1;i++)
		{
			this.numbers.add(i);
		}
		logger.exit();
	}
	
	

	


	

}
