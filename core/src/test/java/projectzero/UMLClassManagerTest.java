package projectzero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
