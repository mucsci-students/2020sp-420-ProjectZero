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
    void testAddFieldReturnsTrueOnSuccess() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
    }

    @Test
    void testAddFieldReturnsFalseOnBadName() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertFalse(umlClass.addField(new Field("!field")));
    }

    @Test
    void testAddFieldReturnsFalseOnDuplicateField() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
        Assertions.assertFalse(umlClass.addField(new Field("field")));
    }
}
