package projectzero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UMLClassYamlMapperTest {

    @Test
    void testReadsAndWritesClassesToFile(@TempDir Path tempDir) throws IOException {
        Path path = tempDir.resolve("animals.yaml");

        UMLClass dog = new UMLClass("dog");
        UMLClass cat = new UMLClass("cat");
        UMLClass bat = new UMLClass("bat");

        List<UMLClass> animals = Arrays.asList(dog, cat, bat);

        UMLClassYamlMapper umlClassYamlMapper = new UMLClassYamlMapper();
        umlClassYamlMapper.write(path.toString(), animals);

        List<String> animalNames = animals.stream().map(animal -> animal.getName()).collect(Collectors.toList());
        List<String> readAnimalNames = umlClassYamlMapper.read(path.toString()).stream().map(animal -> animal.getName()).collect(Collectors.toList());

        Assertions.assertArrayEquals(animalNames.toArray(), readAnimalNames.toArray());
    }
}
