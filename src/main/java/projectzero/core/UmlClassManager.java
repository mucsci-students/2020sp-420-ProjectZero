package projectzero.core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UmlClassManager {
    private final ObservableMap<String, UmlClass> umlClassMap;
    private final UmlClassYamlMapper umlClassYamlMapper;

    /**
     * Default constructor that will initialize an empty obsersable HashMap
     *      and UmlClassYamlMapper object.
     */
    public UmlClassManager() {
        this.umlClassMap = FXCollections.observableHashMap();
        umlClassYamlMapper = new UmlClassYamlMapper();
    }

    /**
     * Constructor that will initialize an empty observable HashMap
     *      and assign the UmlClassManager's UmlClassYamlMapper object to
     *      the UmlClassYamlMapper provided.
     * @param umlClassYamlMapper UmlClassMapper object that will be
     *                              associated with this UmlClassManager.
     */
    public UmlClassManager(UmlClassYamlMapper umlClassYamlMapper) {
        this.umlClassMap = FXCollections.observableHashMap();
        this.umlClassYamlMapper = umlClassYamlMapper;
    }

    /**
     *  Add a new UmlClass
     * @param umlClass The new class that is going to be added
     * @return returns a UmlClass if there is a duplicate class otherwise, returns null
     */
    public UmlClass addUmlClass(UmlClass umlClass) {
        return umlClassMap.putIfAbsent(umlClass.getName(), umlClass);
    }

    /**
     *  Delete a UmlClass
     * @param umlClassName The Umlclass name that will be deleted
     * @return returns the class that is deleted and null if the UmlClass doesn't exist
     */
    public UmlClass deleteUmlClass(String umlClassName) {
        return umlClassMap.remove(umlClassName);
    }

    /**
     * Gets a specified UmlClass
     * @param umlClassName The UmlClass you would like to see
     * @return Returns the specified UmlClass or null if the class isn't found
     */
    public UmlClass getUmlClass(String umlClassName) {
        return umlClassMap.get(umlClassName);
    }

    /**
     * Replaces a UmlClass with another UmlClass
     * @param umlClassName The UmlClass that will be deleted
     * @param umlClass The UmlClass that will be used to update
     * @return Returns a UmlClass if there is a duplicate otherwise, returns null
     */
    public UmlClass updateUmlClass(String umlClassName, UmlClass umlClass) {
        deleteUmlClass(umlClassName);
        return addUmlClass(umlClass);
    }

    /**
     * List of the current UmlClasses
     * @return Returns a List of the UmlClasses
     */
    public List<UmlClass> listUmlClasses() {
        return new ArrayList<>(umlClassMap.values());
    }

    /**
     * Get the Observable List of current UmlClasses
     * @return Returns the Observer List
     */
    public ObservableMap<String, UmlClass> getUmlClassMap() {
        return this.umlClassMap;
    }

    /**
     * Saves the current project
     * @param file The file that the information will be saved to
     * @throws IOException Thrown when trying to save to a protected file or bad permissions
     */
    public void save(File file) throws IOException {
        umlClassYamlMapper.write(file, this.umlClassMap);
    }

    /**
     * Load an existing project
     * @param file The file used to load information
     * @throws IOException Thrown when trying to load a file not in the correct format
     */
    public void load(File file) throws IOException {
        this.umlClassMap.clear();
        this.umlClassMap.putAll(umlClassYamlMapper.read(file));
    }
}