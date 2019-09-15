package com.kowsercse.experiment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloJunit5 {

  private static Object[][] methodSourceTest() {
    return new Object[0][];
  }

  private static Stream<String> testMethodSource() {
    return Stream.of("a", "b");
  }

  private static Stream<Arguments> testMethodArgument() {
    return Stream.of(
        Arguments.arguments(1, "5"),
        Arguments.arguments(2, "9"),
        Arguments.arguments(3, "1")
    );
  }

  @Test
  void testSupplier() {
    assertTrue(true, () -> "a message");
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4})
  void testParameterized(int parameter) {
    System.out.println(parameter);
  }

  @ParameterizedTest
  @MethodSource
  void testMethodSource(String parameter) {
    System.out.println(parameter);
  }

  @ParameterizedTest
  @MethodSource
  void testMethodArgument(int parameter1, String parameter2) {
    System.out.println(parameter1 + ": " + parameter2);
  }

  @RepeatedTest(3)
  void testRepeated() {
    System.out.println("this will be printed 3 times");
  }

  @Disabled
  void testDisabled() {
    System.out.println("This will never be printed");
  }
}

