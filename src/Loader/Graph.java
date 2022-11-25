package Loader;

import java.util.*;

public class Graph {
    static class Node implements Comparable<Node> {
        String name;
        Set<Edge> edges;
        double best;
        Node previous;
        public Node(String name) {
            this.name = name;
            edges = new HashSet<>();
        }
        public int compareTo(Node other) {
            return Double.compare(best, other.best);
        }
    }
    static class Edge implements Comparable<Edge> {
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
        public void setWeight(Node src, Node dst) {
            /*Do Sim stuff here*/
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
    public void add(String src, String dst) {
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
        srcNode.edges.add(new Edge(srcNode, dstNode));
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

        resetDistances();
        start.best = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);

        while(!pq.isEmpty()){
            Node u = pq.poll();
            if(u.equals(end))
                break;
            for(Edge e : u.edges){
                Node v = e.dst;
                double weight = e.weight;
                if(v.best > u.best + weight){
                    pq.remove(v);
                    v.best = u.best + weight;
                    v.previous = u;
                    pq.add(v);
                }
            }
        }

        List<Node> path = new ArrayList<>();
        for(Node n = getNode(dst); n != null; n = n.previous)
            path.add(n);
        Collections.reverse(path);
        if (!path.get(0).name.equals(src)) {
            return null;
        }
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