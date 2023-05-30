package org.example;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {
    List<Node> nodes;

    public Network() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public List<List<Node>> findBlocks() {
        List<List<Node>> blocks = new ArrayList<>();
        Set<Node> visited = new HashSet<>();

        for (Node node : nodes) {
            if (!visited.contains(node)) {
                List<Node> block = new ArrayList<>();
                dfs(node, null, visited, block);
                blocks.add(block);
            }
        }

        return blocks;
    }

    private void dfs(Node currentNode, Node parent, Set<Node> visited, List<Node> block) {      // pentru blockuri
        visited.add(currentNode);
        block.add(currentNode);

        for (Node neighbor : getNeighbors(currentNode)) {
            if (neighbor == parent) {
                continue; // daca e parinte nodul urm ii dau skip pt a nu l vizita repetat
            }

            if (!visited.contains(neighbor)) {
                dfs(neighbor, currentNode, visited, block);
            }
        }
    }

    public boolean isDisconnected(Node node) {
        Set<Node> visited = new HashSet<>();
        Node startNode = this.nodes.get(0);
        depthFirstSearch(startNode, visited, node); //marchez nodurile vizitate
        return visited.size() != this.nodes.size(); // daca setul curent nu contine toate nodurile atunci poate fi deconectat
    }

    private void depthFirstSearch(Node current, Set<Node> visited, Node nodeToSkip) {       // pentru NetworkTest
        if (current == nodeToSkip) {
            return;
        }
        visited.add(current);
        for (Node neighbor : getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                depthFirstSearch(neighbor, visited, nodeToSkip);
            }
        }
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        if (node instanceof Person) {
            Person person = (Person) node;
            neighbors.addAll(person.getRelationships().keySet());
        } else if (node instanceof Company) {
            Company company = (Company) node;
            neighbors.addAll(company.getEmployees());
        }

        return neighbors;
    }

    public int getImportance(Node node1) {
        int imp = 0;
        for (Node node2 : getNodes() /*this.nodes*/) {  //parcurg nodurile din network
            if (!node1.equals(node2)) {  //nodurile nu sunt egales
                if (node1 instanceof Person && node2 instanceof Company) {   // node1 e persoana && node2 e companie
                    Person person = (Person) node1;
                    Company company = (Company) node2;
                    if (person.getRelationships().containsKey(company)) {
                        imp++;                                       //maresc importanta acelui nod
                    }
                } else if (node1 instanceof Company && node2 instanceof Person) {   // node1 e companie && node2 e persoana
                    Company company = (Company) node1;
                    Person person = (Person) node2;
                    if (company.getEmployees().contains(person)) {
                        imp++;                                       //maresc importanta acelui nod
                    }
                } else if (node1 instanceof Person && node2 instanceof Person) {     // node1 e persoana && node2 e persoana
                    Person person1 = (Person) node1;
                    Person person2 = (Person) node2;
                    if (person1.getRelationships().containsKey(person2)) {
                        imp++;                                       //maresc importanta acelui nod
                    }
                }
            }
        }
        return imp;
    }

    public void printNetwork() {
        List<Node> list = new ArrayList<>(this.nodes);  // fac un arraylist nou cu aceleasi elemente
        list.sort((node1, node2) -> Integer.compare(this.getImportance(node2), this.getImportance(node1))); //sortez elementele descrescator
//        Collections.sort(list, Collections.reverseOrder());

//        Collections.sort(list, new Comparator<Node>() {
//            public int compare(Node a, Node b) {
//                return a.getName().compareTo(b.getName());
//            }
//        });

        for (Node node : list) {    //parcurg elementele
            System.out.println(node.getName() + " " + this.getImportance(node) + " relationships");
            if (node instanceof Person) {       //daca e persoana(programator / designer)
                Person person = (Person) node;
                System.out.println("Relationships -> " + person.getName() + "  " + person.getRelationships());
            } else if (node instanceof Company) {       //daca e companie
                Company company = (Company) node;
                System.out.println("Employees for -> " + company.getName() + "  " + company.getEmployees());
            }
        }
    }
}
