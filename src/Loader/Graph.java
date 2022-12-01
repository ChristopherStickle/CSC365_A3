package Loader;

import java.io.Serializable;
import java.util.*;


public class Graph implements Serializable{
    static final long serialVersionUID = 1L;

    static class Node implements Comparable<Node>, Serializable {
        String name;
        Set<Edge> edges;
        double best;
        Node previous;
        String[] parsed_words;
        HashMap<String, Double> local_words = new HashMap<String, Double>();
        double magnitude; // magnitude of the HashMap used for cosSim
        public Node(String name) {
            this.name = name;
            edges = new HashSet<>();
        }
        public int compareTo(Node other) {
            return Double.compare(this.best, other.best);
        }
        public String toString(){ return this.name;} //toString

        public void setTFIDFandMagnitude(Corpus corpus){
            double tempMagnitude = 0; //will be assigned to magnitude when done
            for (String k : local_words.keySet()){ //for every word in the article
                //set TFIDF
                double docCount = local_words.get(k);//get the count
                double docLength = parsed_words.length;
                double wordIDF = corpus.global_dictionary_hm.get(k);
                double newScore = (docCount / docLength) * Math.log(wordIDF);
                if(Math.abs(newScore) > 100.0){ //catches odd cases when the tfidf of a word is infinity
                    newScore = 0; //set it to zero, just scrap it we have enough numbers
                }
                local_words.put(k, newScore);
                //set magnitude
                tempMagnitude = tempMagnitude + ( newScore * newScore ); //magnitude is increased by new entry squared
            }
            magnitude = Math.sqrt(tempMagnitude); //once magnitude is fully formed set it
        }
        public void mapCounts(){
            for (String word : parsed_words) {
                if (local_words.containsKey(word)) {
                    local_words.put(word, local_words.get(word) + 1);
                } else {
                    local_words.put(word, 1.0);
                }
            }
        }
    }
    /*
     * represents an edge between two nodes in the graph. All edges are bidirectional.
     *      This means that if two nodes, a and b, are connected a.edges will contain
     *      an edge with src = a and dst = b, and vice versa.
     */
    static class Edge implements Comparable<Edge>, Serializable {
        Node src;
        Node dst;
        double weight;
        Edge(Node src, Node dst) {
            this.src = src;
            this.dst = dst;
        }
        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }
        public String toString(){return src.name + " -> " + dst.name; }

        /*
         * setWeight can be called on an edge. The weight is the inverse similarity
         *      between the two nodes. The method computes the cosine similarity and
         *      returns 1 - cosSim(src, dst). All values are within (0,1] and the
         *      lower the value the closer the nodes are
         */
        public void setWeight() { // 1 - cosSim(src, dst)
            double numerator = 0; //double to create numerator for the cos sim

            for ( String k : src.local_words.keySet() ){ //for every key in src
                if ( dst.local_words.containsKey(k)){ //if the word is also in dst
                    //if both nodes share a word add to the numerator
                    numerator = numerator + ( src.local_words.get(k) * dst.local_words.get(k) );
                }
            }
            //set weight
            weight = 1 - ( numerator / ( src.magnitude * dst.magnitude) );
        }
    }
    Set<Node> nodes;

    public Graph() {
        nodes = new HashSet<>();
    }
    public boolean contains(String name) {
        return nodes.contains(getNode(name));
    }
    // add a node to the graph
    public void add(String name) {
        if (!contains(name)) {
            nodes.add(new Node(name));
        }
    }
    // add an edge to the graph
    public void addEdge(String src, String dst) {
        Node srcNode = getNode(src);
        Node dstNode = getNode(dst);

        if (srcNode == null) {
            srcNode = new Node(src);
            nodes.add(srcNode);
        }
        if (dstNode == null) {
            dstNode = new Node(dst);
            nodes.add(dstNode);
        }

        // if the edge doesn't exist add it
        for( Edge e : srcNode.edges) {
            if(e.dst == dstNode) {
                return;
            }
        }
        srcNode.edges.add(new Edge(srcNode, dstNode));
        for( Edge e : dstNode.edges) {
            if(e.src == srcNode) {
                return;
            }
        }
        dstNode.edges.add(new Edge(dstNode, srcNode));
    }
    // print the graph
    public void print() {
        for (Node n : nodes) {
            System.out.println();
            System.out.print(n.name+" is connected to: ");
            for (Edge e : n.edges) {
                System.out.print(e.dst.name+" ");
            }
        }
        System.out.println();
    }
    // get a Node from a page title
    public Node getNode(String name) {
        for (Node n : nodes) {
            if (n.name.equals(name)) {
                return n;
            }
        }
        return null;
    }
    //Dijkstra's algorithm
    public List<Node> findShortestPath(String src, String dst){
        Node start = getNode(src);
        Node end = getNode(dst);
        if(start == null || end == null)
            return null;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Node n : nodes)
        {
            if(n != start) {
                n.best = Double.MAX_VALUE;
                n.previous = null;
            }
            pq.add(n);
        }
        start.best = 0;

        while(!pq.isEmpty()){
            Node u = pq.poll();

            for(Edge e : u.edges){
                Node s = e.src, d = e.dst;
                double weight = s.best + e.weight;
                if(weight < d.best){
                    pq.remove(d);
                    d.best = weight;
                    d.previous = s;
                    pq.add(d);
                }
            }
        }

        List<Node> path = new ArrayList<>();
        for(Node n = end; n != start; n = n.previous)
            path.add(n);
        /*Collections.reverse(path);
        if (!path.get(0).name.equals(src)) {
            return null;
        }*/
        return path;
    }
    private void resetDistances()
    {
        for(Node n : nodes)
        {
            n.best = Double.MAX_VALUE;
            n.previous = null;
        }
    }
}