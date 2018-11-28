package com.company;

import java.util.*;

public class Node {

    private String name;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    private Double distance = Double.MAX_VALUE;

    private Map<Node, Double> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDestination(Node destination, double distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void printAdjacent() {
        //System.out.println(Arrays.asList(adjacentNodes));
        Set<Node>print = adjacentNodes.keySet();
        Node[] nodeArray = print.toArray(new Node[print.size()]);
        for (int i = 0; i < nodeArray.length; i++) {
            System.out.println(nodeArray[i].getName() + " " + adjacentNodes.get(nodeArray[i]));
        }
    }
}
