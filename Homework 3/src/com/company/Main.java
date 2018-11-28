package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("C:\\Users\\xamor\\Documents\\GitHub\\CSC365\\Homework 3\\src\\com\\company\\input.txt");
        FileReader reader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String[] input;
        Node nodeStart;
        Node nodeEnd;
        String stringWeight;
        Node postnode;
        double weight;
        Node start = new Node("Start");
        Graph graph = new Graph();
        graph.addNode(start);
        Set<Node> nodes = new HashSet<>();
        while ((line = bufferedReader.readLine()) != null) {
            input = line.split(" ");
            nodeStart = new Node(input[0] + " Start");
            nodeEnd = new Node(input[0] + " End");
            stringWeight = input[1];
            weight = Double.parseDouble(stringWeight);
            if (!graph.containsNode(nodeStart.getName())) {
                graph.addNode(nodeStart);
            } else {
                nodeStart = graph.getNode(nodeStart.getName());
            }
            if (!graph.containsNode(nodeEnd.getName())) {
                graph.addNode(nodeEnd);

            } else {
                nodeEnd = graph.getNode(nodeEnd.getName());
            }
            nodeStart.addDestination(nodeEnd, -weight);
            start.addDestination(nodeStart, 0.0);
            System.out.println(graph.containsNode(input[0] + " Start"));
            System.out.println(graph.containsNode(input[0] + " End"));
            for (int i = 2; i < input.length; i++) {
                if (graph.containsNode(input[i] + " Start")) {
                    System.out.println("Prerec already exists");
                    postnode = graph.getNode(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                } else {
                    System.out.println("Prerec does not exist ");
                    postnode = new Node(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                    graph.addNode(postnode);
                }
            }

        }
        graph = Dijkstra.calculateShortestPathFromSource(graph, start);
        nodes = graph.getNodes();

        Scanner scan = new Scanner(System.in);
        String cmd = null;
        System.out.println("Enter node or say q");
        cmd = scan.nextLine();
        while (!cmd.equals("q")) {
            if (graph.containsNode(cmd)) {
                System.out.println("Graph contains the node " + cmd + " and the distance is " + graph.getNode(cmd).getDistance());
            } else {
                System.out.println("Graph does not contain the node " + cmd);
            }
            Node look = graph.getNode(cmd);
            look.printAdjacent();
            System.out.println("Enter node or say q");
            cmd = scan.nextLine();
        }
        /*
        Scanner scan = new Scanner(System.in);
        String cmd = null;
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);



        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);

        System.out.println("Enter node or say q");
        cmd = scan.next();
        while (!cmd.equals("q")) {
            if (cmd.equals("A")) {
                if (graph.containsNode("A"))
                System.out.println("This node's shortest path is " + nodeA.getDistance());
            } else if (cmd.equals("B")) {
                if (graph.containsNode("B"))
                System.out.println("This node's shortest path is " + nodeB.getDistance());
            } else if (cmd.equals("C")) {
                if (graph.containsNode("C"))
                System.out.println("This node's shortest path is " + nodeC.getDistance());
            } else if (cmd.equals("D")) {
                if (graph.containsNode("D"))
                System.out.println("This node's shortest path is " + nodeD.getDistance());
            } else if (cmd.equals("E")) {
                if (graph.containsNode(""))
                System.out.println("This node's shortest path is " + nodeE.getDistance());
            } else if (cmd.equals("F")) {
                if (graph.containsNode("F"))
                System.out.println("This node's shortest path is " + nodeF.getDistance());
            }
            System.out.println("Enter node or say q");
            cmd = scan.next();
        }
        */

    }


}
