package Loader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Corpus implements Serializable {

    int total_number_of_words = 0;
    ArrayList<String> global_dictionary = new ArrayList<>();
    HashMap<String, Double> global_dictionary_hm = new HashMap<>();

    public void setCountToIDF(){
        for( String s : global_dictionary ){ //for every unique word
            double countOfWord = global_dictionary_hm.get(s); //get global count
            double idfScore = 100 / countOfWord; // divide that by number of documents
            global_dictionary_hm.put(s, idfScore); // set tfidf score
//            System.out.println( "Key: " + s + " | Score: " + global_dictionary_eht.getScore(s));
        }
    }

}
