package projectzero.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public UmlClass addUmlClass(UmlClass umlClass) {
        return umlClassMap.putIfAbsent(umlClass.getName(), umlClass);
    }

    public UmlClass deleteUmlClass(String umlClassName) {
        return umlClassMap.remove(umlClassName);
    }

    public UmlClass getUmlClass(String umlClassName) {
        return umlClassMap.get(umlClassName);
    }

    public UmlClass updateUmlClass(String umlClassName, UmlClass umlClass) {
        umlClassMap.remove(umlClassName);
        return this.addUmlClass(umlClass);
    }

    public List<UmlClass> listUmlClasses() {
        return new ArrayList<>(umlClassMap.values());
    }

    public void save(String path) throws IOException {
        umlClassYamlMapper.write(path, this.umlClassMap);
    }

    public void load(String path) throws IOException {
        this.umlClassMap = umlClassYamlMapper.read(path);
    }
}

