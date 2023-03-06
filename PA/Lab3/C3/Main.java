package org.example;
import java.util.List;
import java.util.ArrayList;
interface Node {
    public String getName();
    @Override
    public String toString();
}
class Person implements Comparable<Person>, Node {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int compareTo(Person otherPerson) {
        return this.name.compareTo(otherPerson.getName());
    }

    public String getName() {
        return this.name;
    }
}
class Company implements Comparable<Company>, Node {
    private String name;
    private List<Person> angajati;

    public Company(String name) {
        this.name = name;
        this.angajati = new ArrayList();
    }

    public void addAngajati(Person employee) {
        this.angajati.add(employee);
    }

    public List<Person> getAngajati() {
        return this.angajati;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int compareTo(Company otherCompany) {
        return this.name.compareTo(otherCompany.getName());
    }

    public String getName() {
        return this.name;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList();
        Person robert = new Person("Robert");
        Person marius = new Person("Marius");
        Person cristi = new Person("Cristian");
        Company bit = new Company("Bitdefender");
        Company micro = new Company("Microsoft");

        nodes.add(robert);
        nodes.add(marius);
        nodes.add(cristi);
        nodes.add(bit);
        nodes.add(micro);

        bit.addAngajati(robert);
        micro.addAngajati(marius);
        micro.addAngajati(cristi);

        for (Node node : nodes) {
            System.out.println(node.getName());
        }

        System.out.println("Angajati la " + bit + ": " + bit.getAngajati());
        System.out.println("Angajati la " + micro + ": " + micro.getAngajati());
    }
}