package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Student st0 = new Student("S0", new HashSet<>(Arrays.asList(new Project("P0"), new Project("P1"), new Project("P2"))));
        Student st1 = new Student("S1", new HashSet<>(Arrays.asList(new Project("P0"), new Project("P1"))));
        Student st2 = new Student("S2", new HashSet<>(Arrays.asList(new Project("P0"))));

        List<Student> list = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            Student student = new Student("S" + i);
            list.add(student);
        }
        var student1 = list.toArray(new Student[0]);
        var student2 = list.toArray(new Student[1]);
        var student3 = list.toArray(new Student[2]);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

//        var students = IntStream.rangeClosed(0, 3)
//                .mapToObj(i -> new Student("S" + i) )
//                .toArray(Student[]::new);

        Student[] studs = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i) )
                .toArray(Student[]::new);

        for (int i: new int[]{0, 1, 2}) {
            System.out.println(studs[i]);
        }


        List<Student> students = new LinkedList<>();
        students.addAll( Arrays.asList(studs) );

        Collections.sort(students);
        System.out.println(students);

        Project p0 = new Project("P0");
        Project p1 = new Project("P1");
        Project p2 = new Project("P2");
        Set<Project> projects = new TreeSet<>(Arrays.asList(p0, p1, p2));

        System.out.println(projects);
    }
}
