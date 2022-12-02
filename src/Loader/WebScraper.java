package Loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WebScraper {

    // scrapes the body of a wiki page and returns it as a String[]
    public static String[] scrape(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            String[] words = doc
                    .select("div.mw-parser-output > p")
                    .text()
                    .toLowerCase()
                    .split("[^a-zA-Z0-9]+");
            return words;
        } catch (IOException ex) {
            System.out.println("Connection Error, Please try again...");
//            ex.printStackTrace();
        }
        return null;
    }
    // This method will write all the "see also" links on the page to a file
    public static void getLinks(String seed)
    {
        ArrayList<String> sub_links = new ArrayList<>();
        try
        {
            Document doc = Jsoup.connect(seed).get();
            Elements links = doc.select("div.mw-parser-output > p > a:not(sup)");
            for(Element link : links) {
                sub_links.add(link.attr("abs:href"));
                //System.out.println(link.attr("abs:href") + " added to sub_links");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("src/Loader/sublinks.txt");
            //clear the file
            myWriter.write("");
            for (String link : sub_links) {
                if( !link.contains("#") ) {
                    myWriter.write(link + "\n");
                    System.out.println("wrote :"+ link );
                }
            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
