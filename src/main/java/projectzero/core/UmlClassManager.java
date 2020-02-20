package projectzero.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UmlClassManager {
    private List<UmlClass> classList;
    private UmlClassYamlMapper umlClassYamlMapper;

    public UmlClassManager() {
        this.classList = new ArrayList<>();
        umlClassYamlMapper = new UmlClassYamlMapper();
    }

    public UmlClassManager(UmlClassYamlMapper umlClassYamlMapper) {
        this.classList = new ArrayList<>();
        this.umlClassYamlMapper = umlClassYamlMapper;
    }

    public boolean addClass(UmlClass newClass) {
        for (UmlClass classObject : classList)
            //Found a duplicate in list already.
            if (newClass.getName().equals(classObject.getName())) {
                return false;
            }

        classList.add(newClass);
        return true;

    }

    public boolean deleteClass(UmlClass classToBeDeleted) {
        boolean deletedClass = false;
        if (!classList.isEmpty() || classToBeDeleted != null) {
            UmlClass tempClass = null;

            for (UmlClass classObject : classList) {
                if (classObject.getName().equals(classToBeDeleted.getName())) {
                    tempClass = classObject;
                    deletedClass = true;
                    break;
                }
            }
            if (deletedClass) {
                classList.remove(tempClass);
                return deletedClass;
            }
        }
        return deletedClass;
        //What happens if the classObject is not found?
    }


    public List<UmlClass> getClassList() { return classList;}

    public boolean save(String path) {
        try {
            umlClassYamlMapper.write(path, this.classList);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    public boolean load(String path) {
        try {
            this.classList = umlClassYamlMapper.read(path);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

}
