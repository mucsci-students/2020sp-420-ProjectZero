package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.List;
import java.util.Objects;

public class Method {
    private final String name;
    private final String type;
    private final List<String> parameterTypes;

    private Method(@JsonProperty("name") String name, @JsonProperty("type") String type, @JsonProperty("parameterTypes") List<String> parameterTypes) throws InvalidNameException {
        this.name = name;
        this.type = type;
        this.parameterTypes = parameterTypes;
    }

    private Method(Method.Builder methodBuilder) {
        this.name = methodBuilder.name;
        this.type = methodBuilder.type;
        this.parameterTypes = methodBuilder.parameterTypes;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method method = (Method) o;
        return name.equals(method.name) &&
                type.equals(method.type) &&
                parameterTypes.equals(method.parameterTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, parameterTypes);
    }

    @Override
    public String toString() {
        String paramaters = String.join(", ", parameterTypes);
        return name + "(" + paramaters + "): " + type;
    }

    public static class Builder {
        private String name;
        private String type;
        private List<String> parameterTypes;

        public Method.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Method.Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Method.Builder withParameterTypes(List<String> parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }

        public Method build() throws InvalidNameException, NullPointerException {
            if (this.name == null || this.type == null || this.parameterTypes == null) {
                throw new NullPointerException();
            }

            if (!SourceVersion.isIdentifier(this.name)) {
                throw new InvalidNameException();
            }

            return new Method(this);
        }
    }
}
