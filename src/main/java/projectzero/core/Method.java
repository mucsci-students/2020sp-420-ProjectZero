package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.Objects;

public class Method {
    private final String name;

    public Method(@JsonProperty("name") String name) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method method = (Method) o;
        return Objects.equals(getName(), method.getName());
    }
}
