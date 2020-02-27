package projectzero.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UmlClassManager {
    private Map<String, UmlClass> umlClassMap;
    private UmlClassYamlMapper umlClassYamlMapper;

    public UmlClassManager() {
        this.umlClassMap = new HashMap<>();
        umlClassYamlMapper = new UmlClassYamlMapper();
    }

    public UmlClassManager(UmlClassYamlMapper umlClassYamlMapper) {
        this.umlClassMap = new HashMap<>();
        this.umlClassYamlMapper = umlClassYamlMapper;
    }

    public boolean addUmlClass(UmlClass umlClass) {
        if (umlClassMap.containsKey(umlClass.getName())) {
            return false;
        }

        umlClassMap.put(umlClass.getName(), umlClass);
        return true;
    }

    public boolean deleteUmlClass(String umlClassName) {
        if (!umlClassMap.containsKey(umlClassName)) {
            return false;
        }

        umlClassMap.remove(umlClassName);
        return true;
    }

    public List<UmlClass> listUmlClasses() {
        return new ArrayList<>(umlClassMap.values());
    }

    public boolean save(String path) {
        try {
            umlClassYamlMapper.write(path, this.umlClassMap);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    public boolean load(String path) {
        try {
            this.umlClassMap = umlClassYamlMapper.read(path);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

}
