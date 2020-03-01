package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Relationship {

    private final UmlClass to;

    public Relationship(@JsonProperty("to") UmlClass to) {
        this.to = to;
    }

    public UmlClass getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return Objects.equals(getTo(), that.getTo());
    }
}
