package com.kowsercse.experiment;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnnotationExperiment {

  @Test
  void testRepeatableAnnotation() {
    RepeatableAnnotationContainer repeatableAnnotationContainer = Parent.class.getAnnotation(RepeatableAnnotationContainer.class);

    assertEquals("default value is required", repeatableAnnotationContainer.anotherField());
    assertThat(Stream.of(repeatableAnnotationContainer.value()).map(RepeatableAnnotation::value))
        .containsExactly("one", "two", "three");
    assertThat(Stream.of(Parent.class.getAnnotationsByType(RepeatableAnnotation.class)).map(RepeatableAnnotation::value))
        .containsExactly("one", "two", "three");
  }

  @Test
  void testInheritedAnnotation() {
    assertNotNull(Parent.class.getAnnotation(InheritedAnnotation.class));
    assertNotNull(Child.class.getAnnotation(InheritedAnnotation.class));
    assertNull(Child.class.getDeclaredAnnotation(InheritedAnnotation.class));
  }

  @Test
  void testDefaultValueAnnotation() {
    assertThat(Parent.class.getAnnotation(DefaultValueAnnotation.class).value())
        .containsExactly("default value override");
  }

  @Test
  void testArrayValueAnnotation() {
    ArrayValueAnnotation arrayValueAnnotation = Parent.class.getAnnotation(ArrayValueAnnotation.class);

    assertNotNull(arrayValueAnnotation);
    assertThat(arrayValueAnnotation.requiredArray1()).containsExactly("one");
    assertThat(arrayValueAnnotation.requiredArray2()).containsExactly("one", "two");
    assertThat(arrayValueAnnotation.defaultArray1()).containsExactly("one");
    assertThat(arrayValueAnnotation.defaultArray2()).containsExactly("one", "two");
  }

  @Test
  void testSourceAnnotation() {
    assertNull(Parent.class.getAnnotation(SourceAnnotation.class));
  }

  @Test
  void testRuntimeAnnotation() {
    RuntimeAnnotation runtimeAnnotation = Parent.class.getAnnotation(RuntimeAnnotation.class);

    assertNotNull(runtimeAnnotation);
    assertEquals("required", runtimeAnnotation.requiredField());
    assertEquals("default value", runtimeAnnotation.defaultField());
    assertEquals(100, runtimeAnnotation.intDefault());
  }
}


@DefaultValueAnnotation("default value override")
@ArrayValueAnnotation(requiredArray1 = "one", requiredArray2 = {"one", "two"})
@SourceAnnotation(requiredField = "required")
@RuntimeAnnotation(requiredField = "required")
@InheritedAnnotation
@RepeatableAnnotation("one")
@RepeatableAnnotation("two")
@RepeatableAnnotation("three")
class Parent {
}

class Child extends Parent {

}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface RepeatableAnnotationContainer {
  RepeatableAnnotation[] value();

  String anotherField() default "default value is required";
}

@Repeatable(value = RepeatableAnnotationContainer.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface RepeatableAnnotation {
  String value();
}

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedAnnotation {
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface DefaultValueAnnotation {
  String[] value() default {"default value"};
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@interface SourceAnnotation {
  String requiredField();

  String defaultField() default "defaultValue";

  int intDefault() default 11;
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface ArrayValueAnnotation {

  String[] defaultArray1() default "one";

  String[] defaultArray2() default {"one", "two"};

  String[] requiredArray1();

  String[] requiredArray2();
}

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAnnotation {
  String requiredField();

  String defaultField() default "default value";

  int intDefault() default 100;
}

