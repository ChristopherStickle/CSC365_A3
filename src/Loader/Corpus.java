package Loader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Corpus implements Serializable {

    HashMap<String, Double> global_dictionary_hm = new HashMap<>();

    public void setCountToIDF(int totalPages){
        for( String s : global_dictionary_hm.keySet() ){ //for every unique word
            double countOfWord = global_dictionary_hm.get(s); //get global count
            double idfScore = totalPages / countOfWord; // divide that by number of documents
            global_dictionary_hm.put(s, idfScore); // set tfidf score
//            System.out.println( "Key: " + s + " | Score: " + global_dictionary_eht.getScore(s));
        }
    }

    public void corpusCounts(Set<String> uniqueWords){
        for (String word : uniqueWords) {
            if (global_dictionary_hm.containsKey(word)) {
                global_dictionary_hm.put(word, ( global_dictionary_hm.get(word) + 1.0 ) );
            } else {
                global_dictionary_hm.put(word, 1.0);
            }
        }
    }

}
