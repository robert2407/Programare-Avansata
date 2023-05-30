package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Network network = new Network();

        //        Person robert = new Person("Robert", new Date(1990, 10, 15));
        //        Person marius = new Person("Marius", new Date(1995, 3, 25));
        //        Person cristi = new Person("Cristian", new Date(1985, 8, 5));

        // java: Date(int,int,int) in java.util.Date has been deprecated -> am transcris cu LocalDate localDate = LocalDate.of(2015, 3, 2); pt java 8, sa nu mai fie probleme

        Person robert = new Programmer("Robert", LocalDate.of(1990, 10, 15), "C++");
        Person marius = new Programmer("Marius", LocalDate.of(1995, 3, 25), "Java");
        Person cristi = new Designer("Cristian", LocalDate.of(1985, 8, 5), "Drawing Tablet");

        Company bit = new Company("Bitdefender");
        Company micro = new Company("Microsoft");

        bit.addEmployee(robert);
        micro.addEmployee(marius);
        micro.addEmployee(cristi);

        robert.addRelationship(bit, "Developer");
        robert.addRelationship(micro, "Partner");
        robert.addRelationship(marius, "Friend");   //relatie prieten cu Robert,Marius
        marius.addRelationship(micro, "Manager");
        cristi.addRelationship(micro, "Designer");

        network.addNode(robert);
        network.addNode(marius);
        network.addNode(cristi);
        network.addNode(bit);
        network.addNode(micro);

        System.out.println(robert.getName()+ " are: " + network.getImportance(robert) + " relatii cu celalte entitati.");

        List<List<Node>> blocks = network.findBlocks();

        if (blocks.isEmpty()) {
            System.out.println("No blocks found");
        } else {
            System.out.println("Blocks:");
            for (List<Node> block : blocks) {
                System.out.println(block);
            }
        }

        List<Node> disconnectingNodes = findDisconnectingNodes(network);

        if (disconnectingNodes.isEmpty()) {
            System.out.println("Fara astfel de noduri");
        } else {
            System.out.println("Noduri care daca sunt eliminate, reteaua este compromisa:");
            for (Node node : disconnectingNodes) {
                System.out.println(node.getName());
            }
        }

        network.printNetwork();

    }

    private static List<Node> findDisconnectingNodes(Network network) {
        List<Node> disconnectingNodes = new ArrayList<>();

        for (Node node : network.getNodes()) {
            if (network.isDisconnected(node)) {
                disconnectingNodes.add(node);
            }
        }

        return disconnectingNodes;
    }
}
