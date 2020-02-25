package projectzero.core;

import java.util.Objects;

public class Method {
    private String name;

    public Method() {
    }

    public Method(String name) {
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
        Method method = (Method) o;
        return Objects.equals(getName(), method.getName());
    }
}
