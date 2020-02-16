package projectzero;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UMLClassYamlMapper {
    public List<UMLClass> read(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(fileName), new TypeReference<List<UMLClass>>() {
        });
    }

    public void write(String fileName, List<UMLClass> umlClassList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new File(fileName), umlClassList);
    }
}
