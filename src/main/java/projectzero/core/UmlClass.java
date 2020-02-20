package projectzero.core;

import java.util.Objects;

public class UmlClass {
    private String name;

    public UmlClass() {
    }

    public UmlClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
