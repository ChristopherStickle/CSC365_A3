package Application;

import Loader.Corpus;
import Loader.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {

    private JTextArea textArea1;
    private javax.swing.JPanel jpanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton searchButton;
    private JButton disjointSetButton;

    public Gui(Graph g) {
        setContentPane(jpanel);
        setTitle("Graph Traverser");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        ArrayList<String> urls = new ArrayList<String>();
        for(Graph.Node n : g.nodes){
            urls.add(n.getName());
        }
        urls.sort(String::compareToIgnoreCase);
        comboBox1.setModel(new DefaultComboBoxModel(urls.toArray()));
        comboBox2.setModel(new DefaultComboBoxModel(urls.toArray()));


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url1 = (String) comboBox1.getSelectedItem();
                String url2 = (String) comboBox2.getSelectedItem();
                List<Graph.Node> path = g.findShortestPath(url1, url2);

                if (path == null) {
                    textArea1.setText("No path found");
                } else {
                    textArea1.setText(path.toString());
                }
            }
        });
        // report disjoint sets when button is clicked
        disjointSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea1.setText(g.findDisjointSubgraphs().toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Loader.Corpus corpus;
        Loader.Graph g;
        // import Corpus object from file
        FileInputStream fil = new FileInputStream("src/corpus");
        ObjectInputStream ois = new ObjectInputStream(fil);
        corpus = (Corpus) ois.readObject();

        fil = new FileInputStream("src/Graph");
        ois = new ObjectInputStream(fil);
        g = (Graph) ois.readObject();


        Gui myFrame = new Gui(g);
    }
}