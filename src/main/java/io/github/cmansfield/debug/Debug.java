package io.github.cmansfield.debug;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.function.Supplier;
import java.io.IOException;


public class Debug {
  private static final Logger LOGGER = LoggerFactory.getLogger(Debug.class);
  
  private Debug() {}

  /**
   * Simple profiler to help optimize methods
   *
   * Wrap method we want to test in a lambda
   *
   * timeMethod(() -> {
   *   CardFilter.filter(cards, new Card.CardBuilder().name("awol2").build());
   *   return null;
   * });
   *
   * @param supplier    The method to time test
   */
  public static void timeMethod(Supplier supplier) throws IOException {
    final int testIterations = 100;
    long startTime = System.nanoTime();
    for (int i = 0; i < testIterations; i++) {
      supplier.get();
    }
    long endTime = System.nanoTime();
    long averageTime = ((endTime - startTime) / 1000000 / testIterations);

    LOGGER.info("{} ms", averageTime);
  }
}
