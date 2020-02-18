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
        manager.addClass(umlclass);

        Assertions.assertFalse(manager.addClass(umlClassTwo));
    }

    @Test
    public void testAddReturnsTrue() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");

        Assertions.assertTrue(manager.addClass(umlClass));
        Assertions.assertEquals(1, manager.getClassList().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenEmpty() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        manager.deleteClass(umlClass);

        Assertions.assertFalse(manager.deleteClass(umlClass));
    }

    @Test
    public void testDeleteReturnsTrue() {
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        manager.addClass(umlClass);

        Assertions.assertTrue(manager.deleteClass(umlClass));
        Assertions.assertEquals(0, manager.getClassList().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenNotInList(){
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        UmlClass umlClassTwo = new UmlClass("project");

        manager.addClass(umlClass);

        Assertions.assertFalse(manager.deleteClass(umlClassTwo));
    }

    @Test
    public void testUMLClassSetName(){
        UmlClassManager manager = new UmlClassManager();
        UmlClass umlClass = new UmlClass("zero");
        umlClass.setName("project");

        Assertions.assertEquals("project", umlClass.getName());
    }

    @Test
    public void testLoadReturnsTrueOnSuccess(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.when(umlClassYamlMapper.read(ArgumentMatchers.anyString())).thenReturn(ArgumentMatchers.anyList());

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
        Mockito.doNothing().when(umlClassYamlMapper).write(ArgumentMatchers.anyString(), ArgumentMatchers.anyList());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertTrue(umlClassManager.save("test.yaml"));
    }

    @Test
    public void testSaveReturnsFalseOnFailure(@Mock UmlClassYamlMapper umlClassYamlMapper) throws IOException {
        Mockito.doThrow(IOException.class).when(umlClassYamlMapper).write(ArgumentMatchers.anyString(), ArgumentMatchers.anyList());

        UmlClassManager umlClassManager = new UmlClassManager(umlClassYamlMapper);

        Assertions.assertFalse(umlClassManager.save("test.yaml"));
    }
}
