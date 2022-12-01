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
        /*Corpus corpus = new Corpus();
        Graph g = new Graph();

        FileInputStream fil = new FileInputStream("src/Graph");
        ObjectInputStream ois = new ObjectInputStream(fil);
        g = (Graph) ois.readObject();

        FileInputStream fil1 = new FileInputStream("src/corpus");
        ObjectInputStream ois1 = new ObjectInputStream(fil1);
        corpus = (Corpus) ois1.readObject();

        g.print();
*/

        //---------------TESTING------------------------------------------------
        Graph g = new Graph();
        g.add("1");
        g.add("2");
        g.add("3");
        g.add("4");
        g.add("5");
        g.add("6");


        g.addEdge("1","2",7);
        g.addEdge("1","3",9);
        g.addEdge("1","6",14);
        g.addEdge("2","3",10);
        g.addEdge("2","4",15);
        g.addEdge("3","6",2);
        g.addEdge("3","4",11);
        g.addEdge("5","6",9);
        g.addEdge("5","4",6);

        g.print();

        System.out.println("--------Pathing-------------");
        System.out.println("Path from 1 to 6: ");
        List<Graph.Node> path = g.findShortestPath("1","6");
        System.out.println(path);

        System.out.println("Path from 4 to 6: ");
        path = g.findShortestPath("4","6");
        System.out.println(path);

        System.out.println("Path from 2 to 5: ");
        path = g.findShortestPath("2","5");
        System.out.println(path);

        System.out.println("Path from 1 to 5: ");
        path = g.findShortestPath("1","5");
        System.out.println(path);

        System.out.println("Path from 3 to 5: ");
        path = g.findShortestPath("3","5");
        System.out.println(path);

        System.out.println("Path from 6 to 4: ");
        path = g.findShortestPath("6","4");
        System.out.println(path);

    }
}

