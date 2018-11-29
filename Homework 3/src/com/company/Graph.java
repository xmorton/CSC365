package com.company;

import java.util.*;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
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

    public double getLongestCount() {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        double longest = 0.0;
        double tWeight;
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].getDistance().equals(0.0)) {
                tWeight = nodeArray[i].getDistance();
            } else  {
                tWeight = -nodeArray[i].getDistance();
            }
            if (tWeight > longest) {
                longest = tWeight;
            }
        }
        return longest;
    }

    public int getCoreCount() {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        int savedCount = 0;
        int count = 0;
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].getName().contains(" Start")) {
                count++;
                for (int j = 0; j < nodeArray.length; j++) {
                    if (nodeArray[j].getName().contains(" Start") && !nodeArray[j].getName().equals(nodeArray[i].getName())) {
                        if (nodeArray[i].getDistance().equals(nodeArray[j].getDistance())) {
                            count++;
                        }
                    } else if (nodeArray[j].getName().contains("End")) {
                        String[] nameSplit = nodeArray[j].getName().split(" ");
                        String startName = nameSplit[0];
                        Node jobStart = this.getNode(startName + " Start");
                        if (nodeArray[i].getDistance() > nodeArray[j].getDistance() && nodeArray[i].getDistance() < jobStart.getDistance() ) {
                            count++;
                        }
                    }
                }
            }
            if (count > savedCount) {
                savedCount = count;
            }
            count = 0;
        }
        return savedCount;
    }

    public double[] getStartTimes(int count) {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        double[] startTimes = new double[count];
        for (int h = 0; h < startTimes.length; h++) {
            //System.out.println(h + " " +startTimes[h]);
            startTimes[h] = Double.MAX_VALUE;
            //System.out.println(h + " " +startTimes[h]);
        }
        startTimes[0] = 0.0;
        //System.out.println(0 + " " + startTimes[0]);
        for (int m = 0; m < nodeArray.length; m++) {
            if (!nodeArray[m].getName().equals("Start")) {
                if (nodeArray[m].getName().contains(" Start")) {
                    for (int n = 0; n < startTimes.length; n++) {
                        double trueWeight;
                        if (nodeArray[m].getDistance().equals(0.0)) {
                            trueWeight = nodeArray[m].getDistance();
                        } else {
                            trueWeight = -nodeArray[m].getDistance();
                        }
                        if (trueWeight == startTimes[n]) {
                            //System.out.println(trueWeight);
                            break;
                        } else if (startTimes[n] == Double.MAX_VALUE) {
                            startTimes[n] = trueWeight;
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
    public int getStartCount() {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        int startCount = 0;
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].getName().contains(" Start"))
                startCount++;
        }
        return startCount;
    }

    public void setAllCores( int coreNumber) {
        Node[] nodeArray = nodes.toArray(new Node[nodes.size()]);
        double longest = getLongestCount();
        double tWeight;
        List<Node> longestPath;
        Node longestNode = new Node("a");
        Node[] longestArray;
        int currentCore = 0;
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].getDistance().equals(0.0)) {
                tWeight = nodeArray[i].getDistance();
            } else {
                tWeight = -nodeArray[i].getDistance();
            }
            if (tWeight == longest) {
                longestNode = nodeArray[i];
                break;
            }
        }
        longestPath = longestNode.getShortestPath();
        longestArray = longestPath.toArray(new Node[longestPath.size()]);
        for (int j = 0; j < longestArray.length; j++) {
            if (longestArray[j].getName().contains(" Start")) {
                longestArray[j].setCore(currentCore);
            }
        }


    }
}
