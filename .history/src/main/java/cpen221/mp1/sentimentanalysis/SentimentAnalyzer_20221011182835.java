package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import cpen221.mp1.ratemyprofessor.Review;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class SentimentAnalyzer {

    /**
     *
     * @param filename
     */
    public SentimentAnalyzer(String filename) {

        HashMap<Integer, Float>

        DataAnalyzer data = null;
        try {
            data = new DataAnalyzer(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<>
        for(Review index: data.reviewList) {

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
