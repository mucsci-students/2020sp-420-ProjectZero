package projectzero.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UmlClassYamlMapper {
    public Map<String, UmlClass> read(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(fileName), new TypeReference<Map<String, UmlClass>>() {
        });
    }

    public void write(String fileName, Map<String, UmlClass> umlClassMap) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new File(fileName), umlClassMap);
    }
}
