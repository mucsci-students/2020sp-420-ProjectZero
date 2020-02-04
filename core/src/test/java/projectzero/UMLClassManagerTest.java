package projectzero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UMLClassManagerTest {

    @Test
    public void testAddReturnsFalseOnDuplicate() {
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlclass = new UMLClass("zero");
        UMLClass umlClassTwo = new UMLClass("zero");
        manager.addClass(umlclass);

        Assertions.assertFalse(manager.addClass(umlClassTwo));
    }

    @Test
    public void testAddReturnsTrue() {
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlClass = new UMLClass("zero");

        Assertions.assertTrue(manager.addClass(umlClass));
        Assertions.assertEquals(1, manager.getClassList().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenEmpty() {
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlClass = new UMLClass("zero");
        manager.deleteClass(umlClass);

        Assertions.assertFalse(manager.deleteClass(umlClass));
    }

    @Test
    public void testDeleteReturnsTrue() {
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlClass = new UMLClass("zero");
        manager.addClass(umlClass);

        Assertions.assertTrue(manager.deleteClass(umlClass));
        Assertions.assertEquals(0, manager.getClassList().size());
    }

    @Test
    public void testDeleteReturnsFalseWhenNotInList(){
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlClass = new UMLClass("zero");
        UMLClass umlClassTwo = new UMLClass("project");

        manager.addClass(umlClass);

        Assertions.assertFalse(manager.deleteClass(umlClassTwo));
    }

    @Test
    public void testUMLClassSetName(){
        UMLClassManager manager = new UMLClassManager();
        UMLClass umlClass = new UMLClass("zero");
        umlClass.setName("project");

        Assertions.assertEquals("project", umlClass.getName());
    }

    @Test
    public void testLoadReturnsTrueOnSuccess(@Mock UMLClassYamlMapper umlClassYamlMapper) throws IOException {
        when(umlClassYamlMapper.read(anyString())).thenReturn(anyList());

        UMLClassManager umlClassManager = new UMLClassManager(umlClassYamlMapper);

        Assertions.assertTrue(umlClassManager.load("test.yaml"));
    }

    @Test
    public void testLoadReturnsFalseOnFailure(@Mock UMLClassYamlMapper umlClassYamlMapper) throws IOException {
        doThrow(IOException.class).when(umlClassYamlMapper).read(anyString());

        UMLClassManager umlClassManager = new UMLClassManager(umlClassYamlMapper);

        Assertions.assertFalse(umlClassManager.load("test.yaml"));
    }

    @Test
    public void testSaveReturnsTrueOnSuccess(@Mock UMLClassYamlMapper umlClassYamlMapper) throws IOException {
        doNothing().when(umlClassYamlMapper).write(anyString(), anyList());

        UMLClassManager umlClassManager = new UMLClassManager(umlClassYamlMapper);

        Assertions.assertTrue(umlClassManager.save("test.yaml"));
    }

    @Test
    public void testSaveReturnsFalseOnFailure(@Mock UMLClassYamlMapper umlClassYamlMapper) throws IOException {
        doThrow(IOException.class).when(umlClassYamlMapper).write(anyString(), anyList());

        UMLClassManager umlClassManager = new UMLClassManager(umlClassYamlMapper);

        Assertions.assertFalse(umlClassManager.save("test.yaml"));
    }
}
