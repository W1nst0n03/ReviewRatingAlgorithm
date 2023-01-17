package cpen221.mp1.ngrams;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGrams {
    private final ArrayList<Long> uniqueCount = new ArrayList<>();
    private final ArrayList<String[]> sentenceList = new ArrayList<>();
    private final List<HashMap<String,Long>> NGramList = new ArrayList<>();


    /**
     * Create an NGrams object
     *
     * @param text all the text to analyze and create n-grams from;
     *             is not null and is not empty.
     */
    public NGrams(String[] text) {
        int longestSentence = 0;
        // For each String entry in the initialization array splits it into an array of words and stores each array
        // in the Arraylist sentenceList, additionally finds the length of the longest sentence
        for(String sentence: text){
            sentenceList.add(this.getWords(sentence));
            if(this.getWords(sentence).length > longestSentence){
                longestSentence = this.getWords(sentence).length;
            }
        }
        for(int x = 0; x < longestSentence; x++){
            HashMap<String,Long> nthGram = new HashMap<>();
            NGramList.add(nthGram);
            uniqueCount.add(0L);
        }
        for(int nGram = 0; nGram < longestSentence; nGram++){
            for (String[] strings : sentenceList) {
                for (int j = 0; j < strings.length - nGram; j++) {
                    // Creates the full ngram string of words separated by spaces
                    // starting at the jth word of the ith sentence
                    StringBuilder GramBuilder = new StringBuilder();
                    for (int getGram = 0; getGram <= nGram; getGram++) {
                        GramBuilder.append(strings[j + getGram]);
                        if (getGram < nGram) {
                            GramBuilder.append(" ");
                        }
                    }
                    String currentNgram = GramBuilder.toString();
                    // If the created ngram already exists inside the ngram map, iterates its count by 1
                    // Otherwise initializes it with a value of one and adds 1 to the nth entry of uniqueCount
                    if (NGramList.get(nGram).containsKey(currentNgram)) {
                        NGramList.get(nGram).replace(currentNgram, NGramList.get(nGram).get(currentNgram) + 1);
                    } else {
                        NGramList.get(nGram).put(currentNgram, 1L);
                        uniqueCount.set(nGram, uniqueCount.get(nGram) + 1L);
                    }
                }
            }
        }
    }


    /**
     * Obtain the total number of unique 1-grams,
     * 2-grams, ..., n-grams.
     *
     * Specifically, if there are m_i i-grams,
     * obtain sum_{i=1}^{n} m_i.
     *
     * @return the total number of 1-grams,
     * 2-grams, ..., n-grams
     * @param n is the upper bounds of n-grams that will be checked for
     */
    public long getTotalNGramCount(int n) {
        //Sums the entries of uniqueCount up to n and returns the total number of unique 1:n grams;
        long uniqueToN = 0;
        for(int i = 0; i < n && i < uniqueCount.size(); i++){
            uniqueToN += uniqueCount.get(i);
        }
        return uniqueToN;
    }

    /**
     * Get the n-grams, as a List, with the i-th entry being
     * all the (i+1)-grams and their counts.
     *
     * @return a list of n-grams and their associated counts,
     * with the i-th entry being all the (i+1)-grams and their counts
     */
    public List<HashMap<String, Long>> getAllNGrams() {
        return NGramList;
    }

    /**
     * Get the αll of the individual words in a string and input them into an array
     * @param text String to be divided into individual words
     * @return an array strings where each string is an individual word in the given string
     */
    private String[] getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(text);
        int start = wb.first();
        for (int end = wb.next(); end != BreakIterator.DONE; start = end, end = wb.next()) {
            String word = text.substring(start, end).toLowerCase();
            word = word.replaceAll("^\\s*\\p{Punct}+\\s*", "").replaceAll("\\s*\\p{Punct}+\\s*$", "");
            if (!word.equals(" ") && !word.equals("")) {
                words.add(word);
            }
        }
        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);
        return wordsArray;
    }
}

