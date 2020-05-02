package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import projectzero.core.exceptions.InvalidNameException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class TestUmlClassManager {
    UmlClassManager manager;
    @BeforeEach
    void init(){
        manager = new UmlClassManager();
    }

    @Test
    void testYamlMapperConstructor(){
        UmlClassYamlMapper mapper = new UmlClassYamlMapper();
        manager = new UmlClassManager(mapper);

        try {
            Class curManager = manager.getClass();
            java.lang.reflect.Field yamlField = curManager
                            .getDeclaredField("umlClassYamlMapper");

            Assertions.assertNotNull(yamlField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddUmlClass(){
        try {
            manager.addUmlClass(new UmlClass("newClass"));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(manager.listUmlClasses().size() == 1);
    }

    @Test
    void testDeleteUmlClass(){
        try {
            manager.addUmlClass(new UmlClass("Class"));
            manager.deleteUmlClass("Class");
            Assertions.assertTrue(manager.getUmlClassMap().size() == 0);

        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteSpecificClass(){
        try{
            UmlClass classToBeDeleted = new UmlClass("Class2");
            classToBeDeleted.addField(new Field.Builder().withName("aField").withType("int").build());
            manager.addUmlClass(new UmlClass("Class"));
            manager.addUmlClass(classToBeDeleted);
            manager.addUmlClass(new UmlClass("Class3"));
            UmlClass returnClass = manager.deleteUmlClass("Class2");
            Assertions.assertTrue(manager.listUmlClasses().size() == 2);
            for(UmlClass umlClass: manager.listUmlClasses())
                Assertions.assertFalse(umlClass.getName().equals("Class2"));
            Assertions.assertTrue(returnClass.equals(classToBeDeleted));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteUmlClassNotInManager(){
        try {
            manager.addUmlClass(new UmlClass("Class"));
            manager.addUmlClass(new UmlClass("Class2"));
            UmlClass umlClass = manager.deleteUmlClass("Class3");
            Assertions.assertNull(umlClass);
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetUmlClassThatExists(){
        try {
            UmlClass classToBeAdded = new UmlClass("Class");
            manager.addUmlClass(classToBeAdded);
            UmlClass returnedUmlClass = manager.getUmlClass("Class");
            Assertions.assertTrue(returnedUmlClass.equals(classToBeAdded));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetUmlClassThatDoesNotExist(){
        UmlClass returnedUmlClass = manager.getUmlClass("Class");
        Assertions.assertNull(returnedUmlClass);
    }

    @Test
    void testUpdateUmlClass(){
        try {
            manager.addUmlClass(new UmlClass("Class"));
            Assertions.assertTrue(manager.listUmlClasses().size() == 1);
            Assertions.assertNotNull(manager.getUmlClass("Class"));

            UmlClass classUpdated = new UmlClass("ClassUpdated");
            classUpdated.addField(new Field.Builder().withName("UpdatedField")
                        .withType("UpdatedType").build());
            manager.updateUmlClass("Class",classUpdated);

            Assertions.assertNull(manager.getUmlClass("Class"));
            Assertions.assertNotNull(manager.getUmlClass("ClassUpdated"));
            Assertions.assertTrue(manager.listUmlClasses().size() == 1);
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSave(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("temp.yaml");
        try {
            manager.addUmlClass(new UmlClass("Class"));
            Assertions.assertDoesNotThrow(()->manager.save(filePath.toFile()));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoad(@TempDir Path tempDir)throws IOException{
        Path filePath = tempDir.resolve("temp.yaml");
        try {
            manager.addUmlClass(new UmlClass("Class"));
            Assertions.assertDoesNotThrow(()->manager.save(filePath.toFile()));
            Assertions.assertDoesNotThrow(()->manager.load(filePath.toFile()));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testExtensiveLoadAndSave(@TempDir Path tempDir){
        Path filePath = tempDir.resolve("temp.yaml");
        try {
            manager.addUmlClass(new UmlClass("Class"));
            UmlClass detailedUmlClass = new UmlClass("DetailedClass");
            detailedUmlClass.addField(new Field.Builder().withName("DetailedField")
                                .withType("DetailedType").build());
            detailedUmlClass.addMethod(new Method.Builder().withName("DetailedMethod")
                                .withParameterTypes(new ArrayList<String>())
                                .withType("DetailedType").build());
            manager.addUmlClass(detailedUmlClass);
            manager.addUmlClass(new UmlClass("anotherClass"));
            Assertions.assertDoesNotThrow(()->manager.save(filePath.toFile()));
            manager = new UmlClassManager();
            Assertions.assertDoesNotThrow(()->manager.load(filePath.toFile()));
            Assertions.assertTrue(manager.listUmlClasses().size() == 3);
            Assertions.assertTrue(manager.getUmlClass("DetailedClass").equals(detailedUmlClass));
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
    }
}
