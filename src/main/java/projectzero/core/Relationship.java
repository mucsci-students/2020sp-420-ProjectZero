package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Relationship {

    private final UmlClass to;
    private final Relationship.Type type;

    private Relationship(@JsonProperty("to") UmlClass to, @JsonProperty("type") Relationship.Type type) {
        this.to = to;
        this.type = type;
    }

    private Relationship(Relationship.Builder builder) {
        this.to = builder.to;
        this.type = builder.type;
    }

    public UmlClass getTo() {
        return to;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return to.equals(that.to) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, type);
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "to=" + to.getName() +
                ", type=" + type +
                '}';
    }

    public enum Type {
        AGGREGATION,
        COMPOSITION,
        GENERALIZATION
    }

    public static class Builder {
        private UmlClass to;
        private Relationship.Type type;

        public Relationship.Builder withTo(UmlClass to) {
            this.to = to;
            return this;
        }

        public Relationship.Builder withType(Relationship.Type type) {
            this.type = type;
            return this;
        }

        public Relationship build() throws NullPointerException {
            if (this.to == null || this.type == null) {
                throw new NullPointerException();
            }

            return new Relationship(this);
        }
    }
}
