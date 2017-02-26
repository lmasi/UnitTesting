package com.example;

import org.junit.Test;
import static org.junit.Assert.*;


public class ExecutedByTestsButNotActuallyTestedByThemTest {

  @Test
  public void doesntReallyTestAnything() {
    ExecutedByTestsButNotActuallyTestedByThem testee = new ExecutedByTestsButNotActuallyTestedByThem();
    assertEquals(1, testee.returnOne());
  }
  
}
