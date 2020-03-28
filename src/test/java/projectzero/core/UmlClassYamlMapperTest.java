package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UmlClassYamlMapperTest {
    @Test
    public void testReadAndWriteOnComplexUmlClassMap(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("temp.yaml");

        Map<String, UmlClass> umlClassMap = new HashMap<>();

        UmlClass animalClass = null;
        UmlClass dogClass = null;
        UmlClass catClass = null;
        UmlClass birdClass = null;

        try {
            animalClass = new UmlClass("animal");
            dogClass = new UmlClass("dog");
            catClass = new UmlClass("cat");
            birdClass = new UmlClass("bird");
        } catch (Exception e) {
            Assertions.fail("Constructors on UmlClass failed - " + e.getLocalizedMessage());
        }

        Relationship toDog = new Relationship(dogClass);
        Relationship toCat = new Relationship(catClass);
        Relationship toBird = new Relationship(birdClass);

        Assertions.assertTrue(animalClass.addRelationship(toDog));
        Assertions.assertTrue(animalClass.addRelationship(toCat));
        Assertions.assertTrue(animalClass.addRelationship(toBird));

        Assertions.assertTrue(dogClass.addRelationship(toCat));

        Field dogColorField = null;
        Field dogWeightField = null;

        Field catLengthField = null;
        Field catFluffinessField = null;

        Field birdWingSpanField = null;
        Field birdBeakSizeField = null;

        try {
            dogColorField = new Field.Builder().withName("color").withType("string").build();
            dogWeightField = new Field.Builder().withName("weight").withType("double").build();

            catLengthField = new Field.Builder().withName("length").withType("double").build();
            catFluffinessField = new Field.Builder().withName("fluffiness").withType("double").build();

            birdWingSpanField = new Field.Builder().withName("wingSpan").withType("double").build();
            birdBeakSizeField = new Field.Builder().withName("beakSize").withType("double").build();
        } catch (Exception e) {
            Assertions.fail("Field constructor failed - " + e.getLocalizedMessage());
        }

        Assertions.assertTrue(dogClass.addField(dogColorField));
        Assertions.assertTrue(dogClass.addField(dogWeightField));

        Assertions.assertTrue(catClass.addField(catLengthField));
        Assertions.assertTrue(catClass.addField(catFluffinessField));

        Assertions.assertTrue(birdClass.addField(birdWingSpanField));
        Assertions.assertTrue(birdClass.addField(birdBeakSizeField));

        Method dogBarkMethod = null;
        Method dogFetchMethod = null;

        Method catPurrMethod = null;
        Method catMeowMethod = null;

        Method birdFlyMethod = null;
        Method birdSquawkMethod = null;

        try {
            dogBarkMethod = new Method.Builder()
                    .withName("bark")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            dogFetchMethod = new Method.Builder()
                    .withName("fetch")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            catPurrMethod = new Method.Builder()
                    .withName("purr")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            catMeowMethod = new Method.Builder()
                    .withName("meow")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            birdFlyMethod = new Method.Builder()
                    .withName("fly")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            birdSquawkMethod = new Method.Builder()
                    .withName("squak")
                    .withType("void")
                    .withParameterTypes(new ArrayList<>())
                    .build();
        } catch (Exception e) {
            Assertions.fail("Method constructor failed - " + e.getLocalizedMessage());
        }

        Assertions.assertTrue(dogClass.addMethod(dogBarkMethod));
        Assertions.assertTrue(dogClass.addMethod(dogFetchMethod));

        Assertions.assertTrue(catClass.addMethod(catPurrMethod));
        Assertions.assertTrue(catClass.addMethod(catMeowMethod));

        Assertions.assertTrue(birdClass.addMethod(birdFlyMethod));
        Assertions.assertTrue(birdClass.addMethod(birdSquawkMethod));

        umlClassMap.put(animalClass.getName(), animalClass);
        umlClassMap.put(dogClass.getName(), dogClass);
        umlClassMap.put(catClass.getName(), catClass);
        umlClassMap.put(birdClass.getName(), birdClass);

        UmlClassYamlMapper umlClassYamlMapper = new UmlClassYamlMapper();

        Assertions.assertDoesNotThrow(() -> {
            umlClassYamlMapper.write(filePath.toString(), umlClassMap);
        });

        Assertions.assertDoesNotThrow(() -> {
            Map<String, UmlClass> readUmlClassMap = umlClassYamlMapper.read(filePath.toString());
            Assertions.assertEquals(umlClassMap, readUmlClassMap);
            Assertions.assertEquals(umlClassMap.get("dog").getFields(), readUmlClassMap.get("dog").getFields());
            Assertions.assertEquals(umlClassMap.get("dog").getMethods(), readUmlClassMap.get("dog").getMethods());
            Assertions.assertEquals(umlClassMap.get("dog").getRelationships(), readUmlClassMap.get("dog").getRelationships());
        });
    }
}
