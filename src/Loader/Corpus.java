package Loader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Corpus implements Serializable {

    HashMap<String, Double> global_dictionary_hm = new HashMap<>();

    public void setCountToIDF(int totalPages){
        for( String s : global_dictionary_hm.keySet() ){ //for every unique word
            double countOfWord = global_dictionary_hm.get(s); //get global count
            double idfScore = totalPages / countOfWord; // divide that by number of documents
            if(idfScore < 1){
                idfScore = ( totalPages/ ( totalPages - 1 ) ) ;
            }
            global_dictionary_hm.put(s, idfScore); // set tfidf score
//            System.out.println( "Key: " + s + " | Score: " + global_dictionary_eht.getScore(s));
        }
    }

    public void corpusCounts(String[] parsed_words){
        ArrayList<String> uniqueWords = new ArrayList();
        for ( String word : parsed_words){
            if(uniqueWords.contains(word)){

            }
            else {
                uniqueWords.add(word);
            }
        }
        for (String word : uniqueWords) {
                if (global_dictionary_hm.containsKey(word)) {
                    global_dictionary_hm.put(word, global_dictionary_hm.get(word) + 1);
                } else {
                    global_dictionary_hm.put(word, 1.0);
                }

        }
    }

}
