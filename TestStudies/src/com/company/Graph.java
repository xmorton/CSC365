package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();
    private int savedCount = 0;
    private int startCount = 0;
    private Node[] nodeArray;
    private boolean[][] cores;

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    //Checks to see if the graph holds a node by a given name
    public boolean containsNode (String nodeName) {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        for (Node aNodeArray : nodeArray) {
            if (nodeName.equals(aNodeArray.getName())) {
                return true;
            }
        }
        return false;
    }

    //returns a node with a given name if it is in the graph if not returns null.
    public Node getNode(String nodeName) {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        for (Node aNodeArray : nodeArray) {
            if (nodeName.equals(aNodeArray.getName())) {
                return aNodeArray;
            }
        }
        return null;
    }

    //Returns the longest distance recorded in the graph
    public double getLongestCount() {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        double longest = 0.0;
        double tWeight;
        for (Node aNodeArray : nodeArray) {
            //Have to do this because all the weights in the graph are negated
            if (aNodeArray.getDistance().equals(0.0)) {
                tWeight = aNodeArray.getDistance();
            } else {
                tWeight = -aNodeArray.getDistance();
            }
            //if the nodes true distance is longer then the current longest replace longest with tWeight
            if (tWeight > longest) {
                longest = tWeight;
            }
        }
        return longest;
    }

    //Returns how many cores are needed for every job to start at the right time.
    public int getCoreCount() {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        int count = 0;
        for (Node aNodeArray : nodeArray) {
            if (aNodeArray.getName().contains(" Start")) {
                count++;
                for (Node bNodeArray : nodeArray) {
                    if (bNodeArray.getName().contains(" Start") && !bNodeArray.getName().equals(aNodeArray.getName())) {
                        //if two jobs start at the same time increase the core count
                        if (aNodeArray.getDistance().equals(bNodeArray.getDistance())) {
                            count++;
                        }
                    } else if (bNodeArray.getName().contains(" End")) {
                        //get the start node that corresponds to this end node
                        String[] nameSplit = bNodeArray.getName().split(" ");
                        String startName = nameSplit[0];
                        Node jobStart = this.getNode(startName + " Start");
                        //If a job starts before the current job but ends after that job is set to start, increase the core count
                        if (aNodeArray.getDistance() > bNodeArray.getDistance() && aNodeArray.getDistance() < jobStart.getDistance()) {
                            count++;
                        }
                    }
                }
            }
            //save only the highest core count.
            if (count > savedCount) {
                savedCount = count;
            }
            count = 0;
        }

        return savedCount;
    }

    //This returns an array of all the times that jobs start at.
    public double[] getStartTimes(int count) {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        double[] startTimes = new double[count];
        //sets all of the starting values to the max
        for (int h = 0; h < startTimes.length; h++) {
            //System.out.println(h + " " +startTimes[h]);
            startTimes[h] = Double.MAX_VALUE;
            //System.out.println(h + " " +startTimes[h]);
        }
        //The first start time is always 0.0
        startTimes[0] = 0.0;
        //System.out.println(0 + " " + startTimes[0]);
        for (Node aNodeArray : nodeArray) {
            //Don't look at the overall Start node.
            if (!aNodeArray.getName().equals("Start")) {
                //Check if it is a job start node
                if (aNodeArray.getName().contains(" Start")) {
                    for (int n = 0; n < startTimes.length; n++) {
                        double trueWeight;
                        //Have to do this because all if the weights in the graph are negated
                        if (aNodeArray.getDistance().equals(0.0)) {
                            trueWeight = aNodeArray.getDistance();
                        } else {
                            trueWeight = -aNodeArray.getDistance();
                        }
                        //Check if the start time is already in the array, if it is not add it to the array.
                        if (trueWeight == startTimes[n]) {
                            //System.out.println(trueWeight);
                            break;
                        } else if (startTimes[n] == Double.MAX_VALUE) {
                            startTimes[n] = trueWeight;
                            //Sort the array so that the max values are always last.
                            Arrays.sort(startTimes);
                            //System.out.println("Adding " + trueWeight);
                            break;
                        }
                    }
                }
            }
        }
        return startTimes;
    }

    //This returns how many job start times there are in the graph
    public int getStartCount() {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        for (Node aNodeArray : nodeArray) {
            if (aNodeArray.getName().contains(" Start"))
                startCount++;
        }
        return startCount;
    }

    //This sets what core each job runs in.
    public void setAllCores( int coreNumber, double[] startTimes, int jobStart) {
        nodeArray = nodes.toArray(new Node[nodes.size()]);
        //This table is the same size as the table that will be displayed, except that in the displayed table there is an extra column
        //stating the unique start times
        cores = new boolean[jobStart][coreNumber];
        //set all of the values to true
        for (int i = 0; i < cores.length; i++) {
            for (int j = 0; j < cores[i].length; j++) {
                cores[i][j] = true;
            }
        }
        //Start with the longest path.
        double longest = getLongestCount();
        double tWeight;
        List<Node> longestPath;
        Node longestNode = new Node("a");
        Node[] longestArray;
        int currentCore = 0;
        //Search the graph for the node with the longest path
        for (Node aNodeArray : nodeArray) {
            if (aNodeArray.getDistance().equals(0.0)) {
                tWeight = aNodeArray.getDistance();
            } else {
                tWeight = -aNodeArray.getDistance();
            }
            if (tWeight == longest) {
                longestNode = aNodeArray;
                break;
            }
        }
        longestPath = longestNode.getShortestPath();
        longestArray = longestPath.toArray(new Node[longestPath.size()]);

        //Set it so that all of the jobs in the longest path are in the first core
        for (Node aLongestArray : longestArray) {
            if (aLongestArray.getName().contains(" Start")) {
                aLongestArray.setCore(currentCore, coreNumber);
                aLongestArray.setChecked(true);
            }
        }
        //Set all of the times in the first core to false
        for (int i = 0; i < cores.length; i++) {
            cores[i][currentCore] = false;
            //System.out.println(cores[i].length);
            //System.out.println(coreNumber);
        }
        //i = the columns and j = rows
        for (int i = 0; i < coreNumber; i++) {
            for (int j = 0; j < cores.length; j++) {
                //Find the time that collates to the current row
                double currentStart = startTimes[j];
                double currentEnd = startTimes[j];
                //If cores[j][i] is already false than skip
                if (cores[j][i]) {
                    for (Node aNodeArray : nodeArray) {
                        //Checks if the node has already been placed or if it is the overall Start node
                        if (!aNodeArray.getName().equals("Start") && !aNodeArray.getChecked()) {
                            //Have to do this because the weights in the graph are negated
                            if (aNodeArray.getDistance().equals(0.0)) {
                                tWeight = aNodeArray.getDistance();
                            } else {
                                tWeight = -aNodeArray.getDistance();
                            }
                            //If the node is a job start node and it starts at the time stored in the current row
                            //the slot can not be taken
                            if (aNodeArray.getName().contains(" Start") && tWeight == currentStart && cores[j][i]) {
                                //Find the job end node for this start node
                                String[] nameSplit = aNodeArray.getName().split(" ");
                                String endName = nameSplit[0];
                                Node endJob = this.getNode(endName + " End");
                                //Set the nodes core to the current column number and set its checked value to true
                                aNodeArray.setCore(i, coreNumber);
                                aNodeArray.setChecked(true);
                                //Turn the slot in the table false
                                cores[j][i] = false;
                                //Find what slots are being taken up by the job's duration
                                for (int k = j + 1; k < cores.length; k++) {
                                    //No job takes 0 time so k = the next row down
                                    currentEnd = startTimes[k];
                                    //weights must be negated
                                    tWeight = -endJob.getDistance();
                                    //If the time the job need to be completed is greater than the next start time,
                                    // then that start times slot must be false
                                    if (tWeight > currentEnd) {
                                        cores[k][i] = false;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
