package Loader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**
         * This is the main method that is used to run the Loader.
         *
         **/

        WebScraper ws = new WebScraper();
        //ws.getLinks("https://en.wikipedia.org/wiki/Computer_science");

        Graph g = new Graph();
        g.add("A");
        g.add("B");
        g.add("C");
        g.add("D");
        g.add("E");
        g.add("F");
        g.add("G");
        g.add("H");
        g.add("I");
        g.add("J");

        g.add("B","D");
        g.add("E","G");
        g.add("A","C");
        g.add("H","I");
        g.add("A","B");
        g.add("E","F");
        g.add("B","C");

        g.print();

        System.out.println("--------Pathing-------------");
        System.out.println("Path from C to D: ");
        List<Graph.Node> path = g.findShortestPath("C","D");
        for (Graph.Node n:path) {
            System.out.println(n.name);
        }
        System.out.println("Path from A to A: ");
        path = g.findShortestPath("A","A");
        for (Graph.Node n:path) {
            System.out.println(n.name);
        }
        System.out.println("Path from H to I: ");
        path = g.findShortestPath("H","I");
        for (Graph.Node n:path) {
            System.out.println(n.name);
        }
        System.out.println("Path from J to I: ");
        path = g.findShortestPath("J","I");
        //should result in a NULL Pointer Exception as path SHOULD be null
        for (Graph.Node n:path) {
            System.out.println(n.name);
        }
    }
}

