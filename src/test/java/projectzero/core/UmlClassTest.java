package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UmlClassTest {

    @Test
    void testAddMethodReturnsTrueOnSuccess() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
    }

    @Test
    void testAddMethodReturnsFalseOnBadName() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertFalse(umlClass.addMethod(new Method("!method")));
    }

    @Test
    void testAddMethodReturnsFalseOnDuplicateMethod() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
        Assertions.assertFalse(umlClass.addMethod(new Method("method")));
    }


}
