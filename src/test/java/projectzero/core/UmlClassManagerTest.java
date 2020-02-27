package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class UmlClassManagerTest {

    @Test
    public void testAddReturnsFalseOnDuplicate() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlclass = new UmlClass("zero");
        UmlClass umlClassTwo = new UmlClass("zero");
        manager.addUmlClass(umlclass);

        Assertions.assertFalse(manager.addUmlClass(umlClassTwo));
    }

    @Test
    public void testAddReturnsTrue() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");

        Assertions.assertTrue(manager.addUmlClass(umlClass));
        Assertions.assertEquals(1, manager.listUmlClasses().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenEmpty() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        manager.deleteUmlClass(umlClass.getName());

        Assertions.assertFalse(manager.deleteUmlClass(umlClass.getName()));
    }

    @Test
    public void testDeleteReturnsTrue() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        manager.addUmlClass(umlClass);

        Assertions.assertTrue(manager.deleteUmlClass(umlClass.getName()));
        Assertions.assertEquals(0, manager.listUmlClasses().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenNotInList() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        UmlClass umlClassTwo = new UmlClass("project");

        manager.addUmlClass(umlClass);

        Assertions.assertFalse(manager.deleteUmlClass(umlClassTwo.getName()));
    }

    @Test
    public void testGetReturnsObjectOnExistingClass() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("class");
        manager.addUmlClass(umlClass);

        Assertions.assertEquals(umlClass, manager.getUmlClass("class"));
    }

    @Test
    public void testGetReturnsNullOnNonExistingClass() {
        UmlClassManager manager = new UmlClassManager();

        Assertions.assertEquals(null, manager.getUmlClass("notClass"));
    }

    @Test
    public void testUMLClassSetName() {
        UmlClassManager manager = new UmlClassManager();
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
