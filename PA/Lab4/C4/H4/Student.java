package org.example;
import java.util.Set;

public class Student implements Comparable<Student> {
    String name;
    Set<Project> admissibleProjects;
    Project assignedProject;

    public Student(String name, Set<Project> admissibleProjects) {
        this.name = name;
        this.admissibleProjects = admissibleProjects;
        this.assignedProject = null;
    }

    public Student(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Set<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project project) {
        this.assignedProject = project;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Student " + name +
                ", admissibleProjects " + admissibleProjects +
                ", assignedProject " + assignedProject +
                '}';
    }
}