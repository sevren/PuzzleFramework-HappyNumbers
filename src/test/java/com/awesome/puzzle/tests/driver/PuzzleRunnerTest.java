package com.awesome.puzzle.tests.driver;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.awesome.puzzle.tests.model.PuzzleFactoryTest;

public class PuzzleRunnerTest {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(PuzzleFactoryTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
} 