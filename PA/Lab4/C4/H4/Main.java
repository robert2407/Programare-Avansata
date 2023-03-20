package org.example;

import java.util.*;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        //tree-set pentru project
        Set<Project> projects = new TreeSet<>();
        int numberOfProjects = 3;
        for (int i = 0; i < numberOfProjects; i++) {
            String projectName = faker.app().name();    //nume proiecte random
            projects.add(new Project(projectName));
        }
        System.out.println("Proiecte ->");
        System.out.println(projects);

        List<Student> students = new ArrayList<>();
        int numberOfStudents = 3;
        for (int i = 0; i < numberOfStudents; i++) {
            String studentName = faker.name().fullName();       //nume oameni random
            Set<Project> admissibleProjects = new HashSet<>();
            int numberOfAdmissibleProjects = faker.random().nextInt(1, projects.size()/* 3 */); // un student poate avea de lan 1 la 3 proiecte asignate
            for (Project project : projects) {
                if (numberOfAdmissibleProjects == 0) {
                    break;
                }
                admissibleProjects.add(project);        //adaug pt fiecare student x proiecte  la admissibleProjects
                numberOfAdmissibleProjects--;
            }
            students.add(new Student(studentName, admissibleProjects));   //dupa ce am adaugat proiectele la admissibleProjects, le pun in student alaturi de numele acestuia
        }
        Collections.sort(students);         //sortam studentii asa cum cere in enunt
        System.out.println("Studenti ->");
        System.out.println(students);

        // media numarului de proiecte asignate celor 3 studenti
        double avgProjs = students.stream()
                .mapToInt(stds -> stds.getAdmissibleProjects().size())
                .average()
                .orElse(0);

        System.out.println("Studenti cu mai putine proiecte decat media ->");

        students.stream()
                .filter(stds -> stds.getAdmissibleProjects().size() < avgProjs)
                .forEach(System.out::println);      // afisez studentii pentru care avgProjs e mai mare decat nr lor de proiecte

        Problem problem = new Problem(students, projects);
        problem.cardinality();

        for (Student student : problem.getStudents()) {
            Project assignedProject = student.getAssignedProject();
            if (assignedProject != null) { // verific daca nu a fost facuta corect asignarea, caz in care cineva a ramas fara proiect
                System.out.println(student.getName() + " are proiectul: " + student.getAssignedProject().getName());
            } else {
                System.out.println(student.getName() + " nu are niciun proiect.");  //caz nefericit
            }
        }
    }
}
