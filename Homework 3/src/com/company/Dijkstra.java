package com.company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    /*
    Starts for the given source and finds the shortest path for all of the nodes in the graph.
     */
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0.0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        //Adds the source node to the unsettledNodes.
        unsettledNodes.add(source);

        //continues till all nodes are settled
        while (unsettledNodes.size() != 0) {
            //Get the node with the lowest distance from the unsettled nodes then remove that node for the unsettled nodes
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            //Get all of the nodes tthat are adjacent to the current node and get their weights then calculate their shortest distance
            //and add them to the unsettled nodes
            for (Map.Entry< Node, Double> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Double edgeWeight = adjacencyPair.getValue();
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
            }
            //settle the current node
            settledNodes.add(currentNode);
        }
        return graph;
    }

    //Gets the node with the lowest distance in the unsettled node
    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        double lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    //Calculates the new shortest distance and updates the nodes shortest path
    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Double edgeWeigh, Node sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
