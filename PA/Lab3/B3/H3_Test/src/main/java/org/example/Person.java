package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class Person implements Comparable<Person>, Node {
    String name;
    LocalDate birthDate;
    Map<Node, String> relationships;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.relationships = new HashMap<>();
    }

    public void addRelationship(Node node, String relationship) {
        this.relationships.put(node, relationship);
    }

    public Map<Node, String> getRelationships() {
        return this.relationships;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int compareTo(Person p) {
        return this.name.compareTo(p.getName());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}