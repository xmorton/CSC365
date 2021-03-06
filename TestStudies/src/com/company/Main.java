package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
	    Node feb3 = new Node("Feb 3");
	    graph.addNode(feb3);
	    Node mar4 = new Node("Mar 4");
	    graph.addNode(mar4);
	    Node mar5 = new Node("Mar 5");
	    graph.addNode(mar5);
	    Node mar6 = new Node("Mar 6");
	    graph.addNode(mar6);
        feb3.addDestination(mar4, 50);
        feb3.addDestination(mar5, 200);
        feb3.addDestination(mar6, 350);
	    Node api5 = new Node("Api 5");
	    graph.addNode(api5);
	    Node api6 = new Node("Api 6");
	    graph.addNode(api6);
	    Node api7 = new Node("Api 7");
	    graph.addNode(api7);
	    mar4.addDestination(api5, 250);
	    mar4.addDestination(api6, 100);
	    mar4.addDestination(api7, 250);
	    mar5.addDestination(api5, 200);
	    mar5.addDestination(api6, 50);
	    mar5.addDestination(api7, 200);
	    mar6.addDestination(api5, 280);
	    mar6.addDestination(api6, 0);
	    mar6.addDestination(api7, 150);
	    Node may5 = new Node("May 5");
	    graph.addNode(may5);
	    Node may6 = new Node("May 6");
	    graph.addNode(may6);
	    Node may7 = new Node("May 7");
	    graph.addNode(may7);
	    api5.addDestination(may5, 400);
        api5.addDestination(may6, 250);
        api5.addDestination(may7, 100);
        api6.addDestination(may5, 480);
        api6.addDestination(may6, 200);
        api6.addDestination(may7, 50);
        api7.addDestination(may5, 560);
        api7.addDestination(may6, 280);
        api7.addDestination(may7, 0);
	    Node jun4 = new Node("Jun 4");
	    graph.addNode(jun4);
	    Node jun5 = new Node("Jun 5");
	    graph.addNode(jun5);
	    Node jun6 = new Node("Jun 6");
	    graph.addNode(jun6);
	    may5.addDestination(jun4, 80);
        may5.addDestination(jun5, 100);
        may5.addDestination(jun6, 250);
        may6.addDestination(jun4, 160);
        may6.addDestination(jun5, 180);
        may6.addDestination(jun6, 200);
        may7.addDestination(jun5, 260);
        may7.addDestination(jun6, 280);
	    Node jul5 = new Node("Jul 5");
	    graph.addNode(jul5);
	    Node jul6 = new Node("Jul 6");
	    graph.addNode(jul6);
	    Node jul7 = new Node("Jul 7");
	    graph.addNode(jul7);
	    jun4.addDestination(jul5, 250);
        jun4.addDestination(jul6, 100);
        jun4.addDestination(jul7, 250);
        jun5.addDestination(jul5, 200);
        jun5.addDestination(jul6, 50);
        jun5.addDestination(jul7, 200);
        jun6.addDestination(jul5, 280);
        jun6.addDestination(jul6, 0);
        jun6.addDestination(jul7, 150);
	    Node aug4 = new Node("Aug 4");
	    graph.addNode(aug4);
	    Node aug5 = new Node("Aug 5");
	    graph.addNode(aug5);
	    Node aug6 = new Node("Aug 6");
	    graph.addNode(aug6);
	    jul5.addDestination(aug4, 280);
        jul5.addDestination(aug5, 300);
        jul5.addDestination(aug6, 450);
        jul6.addDestination(aug4, 360);
        jul6.addDestination(aug5, 380);
        jul6.addDestination(aug6, 400);
        jul7.addDestination(aug4, 440);
        jul7.addDestination(aug5, 460);
        jul7.addDestination(aug6, 480);
	    Node set3 = new Node("Set 3");
	    graph.addNode(set3);
	    aug4.addDestination(set3, 80);
	    graph = Dijkstra.calculateShortestPathFromSource(graph, feb3);
        List<Node> shortestPath = set3.getShortestPath();
        for (Node aNode : shortestPath) {
            System.out.println(aNode.getName());
        }
        System.out.println(set3.getName()+ " " + set3.getDistance());
    }
}
