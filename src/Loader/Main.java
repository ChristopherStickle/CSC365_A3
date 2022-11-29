package Loader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * This is the main method that is used to run the Loader.
         *
         **/
        Graph g = new Graph();
        WebScraper ws = new WebScraper();
        Scanner sc = new Scanner("src/Loader/links.txt");

        while(sc.hasNextLine()) { //loop through all the links in links.txt
            String url_src = sc.nextLine(); // get the source url
            g.add(url_src.substring(30)); // add the source url to the graph
            g.getNode(url_src).parsed_words = ws.scrape(url_src); // scrape the source url and add the words to the parsed_words array
            ws.getLinks(url_src); // get the links from the source url
            Scanner sc2 = new Scanner("src/Loader/sublinks.txt"); // open the sublinks file
            while(sc2.hasNextLine()) { // loop through the sublinks
                String url_dest = sc2.nextLine(); // get the destination url
                g.add(url_dest.substring(30)); // add the destination url to the graph
                g.addEdge(url_src.substring(30), url_dest.substring(30)); // add an edge from the source url to the destination url
                g.getNode(url_dest).parsed_words = ws.scrape(sc2.nextLine()); // scrape the destination url and add the words to the parsed_words array
            }
        }





        //---------------TESTING------------------------------------------------
        /*Graph g = new Graph();
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

        g.addEdge("B","D");
        g.addEdge("E","G");
        g.addEdge("A","C");
        g.addEdge("H","I");
        g.addEdge("A","B");
        g.addEdge("E","F");
        g.addEdge("B","C");

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
        }*/
    }
}

