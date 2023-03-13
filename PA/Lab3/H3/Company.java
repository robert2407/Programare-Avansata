package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company implements Comparable<Company>, Node {
    String name;
    List<Person> employees;
    Map<Node, String> relationships;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.relationships = new HashMap<>();
    }

    public void addEmployee(Person employee) {
        this.employees.add(employee);
    }

    public List<Person> getEmployees() {
        return this.employees;
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

    public int compareTo(Company c) {
        return this.name.compareTo(c.getName());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
