package projectzero.core;

import javax.lang.model.SourceVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UmlClass {
    private String name;
    private Map<String, Method> methodMap;

    public UmlClass() {
        this.methodMap = new HashMap<>();
    }

    public UmlClass(String name) {
        this.methodMap = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }

    public boolean addMethod(Method method) {
        if (!SourceVersion.isIdentifier(method.getName()) || methodMap.containsKey(method.getName())) {
            return false;
        }

        methodMap.put(method.getName(), method);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
