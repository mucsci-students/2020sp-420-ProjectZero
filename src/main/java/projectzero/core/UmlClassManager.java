package projectzero.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UmlClassManager {
    private ObservableMap<String, UmlClass> umlClassMap;
    private UmlClassYamlMapper umlClassYamlMapper;

    public UmlClassManager() {
        this.umlClassMap = FXCollections.observableHashMap();
        umlClassYamlMapper = new UmlClassYamlMapper();
    }

    public UmlClassManager(UmlClassYamlMapper umlClassYamlMapper) {
        this.umlClassMap = FXCollections.observableHashMap();
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
        return umlClassMap.put(umlClassName, umlClass);
    }

    public List<UmlClass> listUmlClasses() {
        return new ArrayList<>(umlClassMap.values());
    }

    public ObservableMap<String, UmlClass> getUmlClassMap() {
        return this.umlClassMap;
    }

    public void save(File file) throws IOException {
        umlClassYamlMapper.write(file, this.umlClassMap);
    }

    public void load(File file) throws IOException {
        this.umlClassMap.clear();
        this.umlClassMap.putAll(umlClassYamlMapper.read(file));
    }
}

