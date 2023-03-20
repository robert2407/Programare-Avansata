package org.example;

import java.util.*;

public class Problem {
    List<Student> students;
    Set<Project> projects;
    Map<Student, Project> mapConectionProjStud;

    public Problem(List<Student> students, Set<Project> projects) {
        this.students = students;
        this.projects = projects;
        this.mapConectionProjStud = new HashMap<>();
    }
    public Set<Project> getProjects() {
        return projects;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void cardinality() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparingInt(student -> student.getAdmissibleProjects().size()));    //sortez crescator ca sa nu ramana nici cei cu doar 1 proiect posibil neasignati

        for (Student student : sortedStudents) {
            List<Project> admissibleProjects = new ArrayList<>(student.getAdmissibleProjects());
            Collections.sort(admissibleProjects);   //sortez crescator si proiectele
            for (Project project : admissibleProjects) {
                if (!mapConectionProjStud.containsValue(project)) { //daca nu are proiect asignat, il pun eu
                    mapConectionProjStud.put(student, project);
                    break;
                }
            }
        }
        for (Student student : students) {
            student.setAssignedProject(mapConectionProjStud.get(student));  //selectez proiectul fiecarui student pe rand
        }
    }
}
