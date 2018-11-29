package com.company;

import java.io.*;
import java.util.*;
import javax.swing.*;

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
        Set<Node> nodes;
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
            //System.out.println(graph.containsNode(input[0] + " Start"));
            //System.out.println(graph.containsNode(input[0] + " End"));
            for (int i = 2; i < input.length; i++) {
                if (graph.containsNode(input[i] + " Start")) {
                    //System.out.println("Prerec already exists");
                    postnode = graph.getNode(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                } else {
                    //System.out.println("Prerec does not exist ");
                    postnode = new Node(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                    graph.addNode(postnode);
                }
            }

        }
        graph = Dijkstra.calculateShortestPathFromSource(graph, start);
        nodes = graph.getNodes();
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        int coreCount = graph.getCoreCount();
        int startCount = graph.getStartCount() + 1;
        int jobStartCount = 0;
        double[] startTimes = graph.getStartTimes(startCount);
        double longestCount = graph.getLongestCount();
        for (int y = 0; y < startTimes.length; y++) {
            if (startTimes[y] == Double.MAX_VALUE) {
                startTimes[y] = longestCount;
                jobStartCount = y + 1;
                Arrays.sort(startTimes);

                System.out.println(startTimes[y]);
                break;
            }
            System.out.println(startTimes[y]);
        }
        int realStartCount = jobStartCount;
        graph.setAllCores(coreCount);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(coreCount, realStartCount, startTimes, nodeArray);
            }
        });

    }
    private static void createAndShowGUI(int coreCount, int startCount, double[] startTimes, Node[] nodeArray) {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MyTable newContentPane = new MyTable(coreCount, startCount, startTimes, nodeArray);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
