package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.Objects;

public class Field {
    private final String name;
    private final String type;

    private Field(@JsonProperty("name") String name, @JsonProperty("type") String type) throws InvalidNameException {
        this.name = name;
        this.type = type;
    }

    private Field(Field.Builder fieldBuilder) {
        this.name = fieldBuilder.name;
        this.type = fieldBuilder.type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return name.equals(field.name) &&
                type.equals(field.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name + ": " + type;
    }

    public static class Builder {
        private String name;
        private String type;

        public Field.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Field.Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Field build() throws InvalidNameException, NullPointerException {
            if (this.name == null || this.type == null) {
                throw new NullPointerException();
            }

            if (!SourceVersion.isIdentifier(this.name)) {
                throw new InvalidNameException();
            }

            return new Field(this);
        }
    }
}
