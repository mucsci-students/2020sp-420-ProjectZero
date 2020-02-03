package projectzero;

import java.util.LinkedList;

public class UMLClassManager {
    private LinkedList<UMLClass> classList;

    public UMLClassManager() {
        classList = new LinkedList<UMLClass>();
    }

    public boolean addClass(UMLClass newClass) {
        for (UMLClass classObject : classList) {
            //Found a duplicate in list already.
            if (newClass.getName().equals(classObject.getName())) {
                return false;
            }
        }
        classList.add(newClass);
        return true;

    }

    public boolean deleteClass(UMLClass classToBeDeleted) {
        boolean deleatedClass = false;
        if (!classList.isEmpty() || classToBeDeleted != null) {
            UMLClass tempClass = null;

            for (UMLClass classObject : classList) {
                if (classObject.getName().equals(classToBeDeleted.getName())) {
                    tempClass = classObject;
                    deleatedClass = true;
                    break;
                }
            }
            if (deleatedClass) {
                classList.remove(tempClass);
                return deleatedClass;
            }
        }
        return deleatedClass;
        //What happens if the classObject is not found?
    }

    public void saveWork(String path) {

    }

    public void loadWork(String path) {

    }

    public LinkedList<UMLClass> getClassList() {
        return classList;
    }
}
