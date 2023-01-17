package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.*;
import cpen221.mp1.cities.DataAnalyzer;
import cpen221.mp1.ratemyprofessor.*;
import cpen221.*;
import cpen221.mp1.ratemyprofessor.d;

import java.io.*;
import java.util.*;
public class SentimentAnalyzer {

    /**
     *
     * @param filename
     */
    public SentimentAnalyzer(String filename) {

        HashMap<Integer, Float> ratings = new HashMap<>();

        DataAnalyzer data = null;
        try {
            data = new DataAnalyzer(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        for(Review index: data.reviewList) {
            if(ratings.containsKey(index.score))
            {
                ratings.put(index.score, ratings.get(index.score) + 1);
            }
            else {

            };
            index.score
        }

        Map<String, Integer> probability = new HashMap<>();
        for(Review index : data.reviewList){
            
        }

        // TODO: Implement this constructor and write the spec
        // You may assume that the file is in the format
        // of the RateMyProfessor data with each line containing
        // a rating, a second column (gender or similar) that you
        // can ignore for this task, and the text of the review.
        // The file whose name is provided here is expected to
        // contain the **training data** with which you build
        // your prediction model.
    }

    /**
     *
     * @param reviewText
     * @return
     */
    public float getPredictedRating(String reviewText) {
        // TODO: Implement this method for predicting
        // the rating given the text of a review using
        // the simple Bayesian approach outlined in the
        // MP statement. Also write the specification for
        // the method.
        return 0;
    }

}
