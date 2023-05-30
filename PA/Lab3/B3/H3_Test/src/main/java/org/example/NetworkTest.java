package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NetworkTest {
    private Network network;
    private List<Node> disconnectingNodes;

    @BeforeEach
    public void setup() {
        network = new Network();
        Person robert = new Programmer("Robert", LocalDate.of(1990, 10, 15), "C++");
        Person marius = new Programmer("Marius", LocalDate.of(1995, 3, 25), "Java");

        Company bit = new Company("Bitdefender");
        Company micro = new Company("Microsoft");

        bit.addEmployee(robert);
        micro.addEmployee(marius);

        robert.addRelationship(bit, "Developer");
        robert.addRelationship(micro, "Partner");

        network.addNode(robert);
        network.addNode(marius);
        network.addNode(bit);
        network.addNode(micro);

        disconnectingNodes = findDisconnectingNodes(network);
    }

    @Test
    public void testDisconnectingNodes() {
        if (disconnectingNodes.isEmpty()) {
            System.out.println("No disconnecting nodes found");
        } else {
            System.out.println("Disconnecting nodes:");
            for (Node node : disconnectingNodes) {
                System.out.println(node.getName());
            }
        }

        Assertions.assertEquals(0, disconnectingNodes.size(), "No disconnecting nodes should be found");
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
