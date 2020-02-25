package projectzero.core;

import java.util.Objects;

public class Relationship {

    private UmlClass to;

    public Relationship() {
    }

    public Relationship(UmlClass to) {
        this.to = to;
    }

    public UmlClass getTo() {
        return to;
    }

    public void setTo(UmlClass to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return Objects.equals(getTo(), that.getTo());
    }
}
