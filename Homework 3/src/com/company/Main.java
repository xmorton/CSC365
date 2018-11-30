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
            //Makes both the start and end node for each job in the file
            nodeStart = new Node(input[0] + " Start");
            nodeEnd = new Node(input[0] + " End");
            //Gets the weight for the job, then turns that weight into a double
            stringWeight = input[1];
            weight = Double.parseDouble(stringWeight);
            //Checks if the start or end node already exist in the graph, if so gets that node from the graph, if not adds the node.
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
            //adds the edge from the start node to the end nod with a negated weight.
            nodeStart.addDestination(nodeEnd, -weight);
            //adds the edge from the overall start node to the current job start node.
            start.addDestination(nodeStart, 0.0);
            for (int i = 2; i < input.length; i++) {
                if (graph.containsNode(input[i] + " Start")) {
                    postnode = graph.getNode(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                } else {
                    postnode = new Node(input[i] + " Start");
                    nodeEnd.addDestination(postnode, 0.0);
                    graph.addNode(postnode);
                }
            }

        }
        //computes the longest path, which is technically the shortest because all of the weights are negated
        graph = Dijkstra.calculateShortestPathFromSource(graph, start);
        nodes = graph.getNodes();
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        int coreCount = graph.getCoreCount();
        int startCount = graph.getStartCount() + 1;
        int jobStartCount = 0;
        //Get all the times when a job starts and turns it into an array named startTimes
        double[] startTimes = graph.getStartTimes(startCount);
        //Gets the longest time, this is the time when all jobs are done.
        double longestCount = graph.getLongestCount();
        //Adds the longest time to the startTimes array
        for (int y = 0; y < startTimes.length; y++) {
            if (startTimes[y] == Double.MAX_VALUE) {
                startTimes[y] = longestCount;
                jobStartCount = y + 1;
                Arrays.sort(startTimes);
                break;
            }
        }
        //This is the number of start times plus the end time.
        int realStartCount = jobStartCount;
        //Sets the core that the job will be ran in.
        graph.setAllCores(coreCount, startTimes, realStartCount);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(coreCount, realStartCount, startTimes, nodeArray);
            }
        });

    }

    /*
    Creates and shows the table. Takes the number of needed cores, the number of start times, the start times themselves,
    and the nodes of the graph
     */
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
