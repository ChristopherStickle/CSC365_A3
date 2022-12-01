package Loader;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * This is the main method that is used to run the Loader.
         *
         **/
        /*
        Graph g = new Graph();
        WebScraper ws = new WebScraper();
        Scanner sc = new Scanner(new File("src/Loader/links.txt"));
        Corpus corpus = new Corpus();

        //loop through all the links in links.txt
        while(sc.hasNextLine()) {
            String url_src = sc.nextLine(); // get the source url
            String srcName = url_src.substring(30); // get the urlName of the source url
            g.add(srcName); // add the source url to the graph
            g.getNode(srcName).parsed_words = ws.scrape(url_src); // scrape the source url and add the words to the parsed_words array
            g.getNode(srcName).mapCounts(); // map the words to the local_words HashMap
            ws.getLinks(url_src); // get the links from the source url
            Scanner sc2 = new Scanner(new File("src/Loader/sublinks.txt")); // open the sublinks file
            // loop through the sublinks
            while(sc2.hasNextLine()) {
                String url_dest = sc2.nextLine(); // get the destination url
                String dstName = url_dest.substring(30); // get the urlName of the source url
                String[] words = ws.scrape(url_dest); // scrape the destination url
                if( words != null) {
                    g.add(dstName); // add the destination url to the graph
                    g.addEdge(srcName, dstName); // add an edge from the source url to the destination url
                    g.getNode(dstName).parsed_words = words; // scrape the destination url and add the words to the parsed_words array
                    g.getNode(dstName).mapCounts(); // map the words to the local_words HashMap
                }
            }
        }
        //build corpus
        for(Graph.Node node : g.nodes){//for every node we have
            corpus.corpusCounts(node.local_words.keySet()); //put your unique words into the corpus
        }
        corpus.setCountToIDF(g.nodes.size());// have corpus set counts to idf

        //set tfidf
        for (Graph.Node node : g.nodes){//for every node in the graph
            node.setTFIDFandMagnitude(corpus);//have the node set the tfidf value of all its words
        }

        //set edge weights
        for (Graph.Node node : g.nodes) {//for every word in the
            for (Graph.Edge edge : node.edges) {// for every edge the node has
                edge.setWeight();// have the edge set its weight
            }
        }

        g.print();

//        System.out.println("Total documents: " + g.nodes.size());
//        System.out.println("--------Pathing-------------");
//        System.out.println("Path from Mathematics to Consumer_choice: ");
//        List<Graph.Node> path = g.findShortestPath("Mathematics","Consumer_choice");
//        for (Graph.Node n:path) {
//            System.out.println(n.name);
//        }


        FileOutputStream fos = new FileOutputStream("src/Graph");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(g);

        FileOutputStream fos1 = new FileOutputStream("src/corpus");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(corpus);


         */

        Corpus corpus1 = new Corpus();
        Graph g1 = new Graph();

        FileInputStream fil = new FileInputStream("src/Graph");
        ObjectInputStream ois = new ObjectInputStream(fil);
        g1 = (Graph) ois.readObject();

        FileInputStream fil1 = new FileInputStream("src/corpus");
        ObjectInputStream ois1 = new ObjectInputStream(fil1);
        corpus1 = (Corpus) ois1.readObject();

//        g.print();

        /*
        for( Graph.Edge e : g1.getNode("Mathematics").edges){
            System.out.println( e + " with weight: " + e.weight );
        }

         */

        /*
        Graph.Edge e1 = new Graph.Edge(g1.getNode("Mathematics"), g1.getNode("Exclusive_or"));
        Graph.Edge e2 = new Graph.Edge(g1.getNode("Mathematics"), g1.getNode("Computational_complexity"));
        e1.setWeight();
        e2.setWeight();
        System.out.println(e1.weight);
        System.out.println(e2.weight);

        System.out.println(e1.compareTo(e2));
        System.out.println(e2.compareTo(e1));
         */

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

