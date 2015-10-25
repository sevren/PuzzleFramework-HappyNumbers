package com.awesome.puzzle.model;
/**
 * PuzzleFramework Model
 * 
 * Each puzzle should implement this interface
 * Oct 25, 2015
 * @author Kiril Tzvetanov Goguev
 * 
 */
import java.util.ArrayList;

import com.awesome.puzzle.model.PuzzleTypes;
public interface Puzzle {
	
	public String getPuzzleDescription();
	public String getInstanceDescription();
	public String getParameters();
	public PuzzleTypes getType();
	public void solve();
	public String showSolution();
	public ArrayList<Integer> getSolution();
	public int sanitize(int number);
	public ArrayList<Integer> getInitalNumbers();
	

}
