package com.company;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean containsNode (String nodeName) {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeName.equals(nodeArray[i].getName())) {
                return true;
            }
        }
        return false;
    }

    public Node getNode(String nodeName) {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeName.equals(nodeArray[i].getName())) {
                return nodeArray[i];
            }
        }
        return null;
    }
}
