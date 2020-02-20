package projectzero.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UmlClassYamlMapper {
    public List<UmlClass> read(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(fileName), new TypeReference<List<UmlClass>>() {
        });
    }

    public void write(String fileName, List<UmlClass> umlClassList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new File(fileName), umlClassList);
    }
}
