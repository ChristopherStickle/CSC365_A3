package Application;

import Loader.Corpus;
import Loader.Graph;
import Loader.WebScraper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Gui extends JFrame {

    private JTextField textField1;
    private JButton SearchButton;
    private JButton ClearButton;
    private JTextArea textArea1;
    private javax.swing.JPanel jpanel;
    private JTable table1;

    public Gui(Graph g) {
        setContentPane(jpanel);
        setTitle("Graph Traverser");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        WebScraper ws = new WebScraper();

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input_url = textField1.getText();
                String[] words;
                try {
                    words = ws.scrape(input_url);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Clicking the "clear" button will reset the panel to default values
        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Enter a URL here...");
                textArea1.setText("");
            }
        });

        //Clicking in the text field will clear it for the user
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField1.setText("");
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