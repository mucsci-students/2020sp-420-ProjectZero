package projectzero.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UmlClass {
    private String name;
    private Map<String, Relationship> relationshipMap;

    public UmlClass() {
        this.relationshipMap = new HashMap<>();
    }

    public UmlClass(String name) {
        this.name = name;
        this.relationshipMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Relationship> getRelationshipMap() {
        return relationshipMap;
    }

    public void setRelationshipMap(Map<String, Relationship> relationshipMap) {
        this.relationshipMap = relationshipMap;
    }

    public boolean addRelationship(Relationship relationship) {
        if (this.relationshipMap.containsKey(relationship.getTo().getName()) || relationship.getTo().getRelationshipMap().containsKey(this.getName())) {
            return false;
        }

        this.relationshipMap.put(relationship.getTo().getName(), relationship);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName()) &&
                Objects.equals(getRelationshipMap(), umlClass.getRelationshipMap());
    }
}
