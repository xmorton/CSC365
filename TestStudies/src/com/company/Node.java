package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;

    private int core;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    private Double distance = Double.MAX_VALUE;

    private Map<Node, Double> adjacentNodes = new HashMap<>();

    private boolean checked = false;

    //node constructor
    public Node(String name) {
        this.name = name;
        core = -2;
    }

    //adds an edge to a specified node
    public void addDestination(Node destination, double distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    //sets the core that the job will be ran in
    public void setCore(int coreNumber, int numberCore) {
        //If the core number that is being set is greater than the number of possible cores, do not set the core
        if (coreNumber <= numberCore) {
            core = coreNumber;
        } else {
            core = -2;
        }
    }

    public int getCore() {
        return core;
    }


    public void setChecked (boolean check) {
        checked = check;
    }

    public boolean getChecked() {
        return checked;
    }



    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
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

    //This is only used during the Dijkstra calculation.
    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

}
