package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class UmlClassManagerTest {
    private UmlClassManager umlClassManager;

    @BeforeEach
    public void makeNewManager() {
        umlClassManager = new UmlClassManager();
    }

    @Test
    public void testAddReturnsFalseOnDuplicate() {
        UmlClass umlclass = new UmlClass("zero");
        UmlClass umlClassTwo = new UmlClass("zero");
        umlClassManager.addUmlClass(umlclass);

        Assertions.assertFalse(umlClassManager.addUmlClass(umlClassTwo));
    }

    @Test
    public void testAddReturnsTrue() {
        UmlClass umlClass = new UmlClass("zero");

        Assertions.assertTrue(umlClassManager.addUmlClass(umlClass));
        Assertions.assertEquals(1, umlClassManager.listUmlClasses().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenEmpty() {
        UmlClass umlClass = new UmlClass("zero");
        umlClassManager.deleteUmlClass(umlClass.getName());

        Assertions.assertFalse(umlClassManager.deleteUmlClass(umlClass.getName()));
    }

    @Test
    public void testDeleteReturnsTrue() {
        UmlClass umlClass = new UmlClass("zero");
        umlClassManager.addUmlClass(umlClass);

        Assertions.assertTrue(umlClassManager.deleteUmlClass(umlClass.getName()));
        Assertions.assertEquals(0, umlClassManager.listUmlClasses().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenNotInList() {
        UmlClass umlClass = new UmlClass("zero");
        UmlClass umlClassTwo = new UmlClass("project");

        umlClassManager.addUmlClass(umlClass);

        Assertions.assertFalse(umlClassManager.deleteUmlClass(umlClassTwo.getName()));
    }

    @Test
    public void testUMLClassSetName() {
        UmlClass umlClass = new UmlClass("zero");
        umlClass.setName("project");

        Assertions.assertEquals("project", umlClass.getName());
    }

    @Test
    public void testLoadReturnsTrueOnSuccess(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.when(umlClassYamlMapper.read(ArgumentMatchers.anyString())).thenReturn(ArgumentMatchers.anyMap());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertTrue(umlClassManager.load("test.yaml"));
    }

    @Test
    public void testLoadReturnsFalseOnFailure(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.doThrow(IOException.class).when(umlClassYamlMapper).read(ArgumentMatchers.anyString());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertFalse(umlClassManager.load("test.yaml"));
    }

    @Test
    public void testSaveReturnsTrueOnSuccess(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.doNothing().when(umlClassYamlMapper).write(ArgumentMatchers.anyString(), ArgumentMatchers.anyMap());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertTrue(umlClassManager.save("test.yaml"));
    }

    @Test
    public void testSaveReturnsFalseOnFailure(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.doThrow(IOException.class).when(umlClassYamlMapper).write(ArgumentMatchers.anyString(), ArgumentMatchers.anyMap());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertFalse(umlClassManager.save("test.yaml"));
    }
}
