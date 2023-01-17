package cpen221.mp1.sentimentanalysis;

import cpen221.mp1.ratemyprofessor.DataAnalyzer;
import cpen221.mp1.ratemyprofessor.Review;
import cpen221.mp1.datawrapper.DataWrapper;
import cpen221.mp1.ngrams.NGrams;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import javax.management.openmbean.OpenMBeanConstructorInfo;
public class SentimentAnalyzer {

    private Set<String> reviewText;
    private Map<String, Float> probability;
    private DataAnalyzer data;
    private HashMap<Float,Float[]> ratings;
    /**
     *
     * @param filename
     */
    public SentimentAnalyzer(String filename) {

        this.ratings = new HashMap<>();
        float total_ratings = 0.0f;
        try {
            this.data = new DataAnalyzer(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Review index: this.data.reviewList) {
            if(ratings.containsKey(index.score))
            {
                ratings.get(index.score)[0] += 1;
            }
            else {
                ratings.put(index.score, new Float[2]);
                ratings.get(index.score)[0] = 1.0f;
            };
            total_ratings++; 
        }

        for (Float key : ratings.keySet()) {
            float p = ratings.get(key)[0] / total_ratings;
            ratings.get(key)[1] = p;
        }

        
        this.probability = new HashMap<>();
        String[] x = this.data.masterString.split(" ");
        NGrams master = new NGrams(x);
        Float total =0f;
        for(Long y : master.getAllNGrams().get(0).values()){
            total += y;
        }
        for (String y: master.getAllNGrams().get(0).keySet()){
            this.probability.put(y, (Float) (master.getAllNGrams().get(0).get(y)/total) );
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

    public float bag_rating (float rating) {
        Map<String, Long> review_text = new HashMap<>();
        Float occurences = 0.0f;
        Long totalWords =0L;
        int counter = 0;
        for(Review reviews : data.reviewList) {
            if(reviews.score == rating) {
                counter ++;
                for (Long y : reviews.getReview().getAllNGrams().get(0).values()){
                    totalWords += y ;
                }
                for (String key :reviews.getReview().getAllNGrams().get(0).keySet()){
                    if(this.reviewText.contains(key)) {
                        if(review_text.containsKey(key) ) {
                            review_text.replace(key, review_text.get(key) + reviews.getReview().getAllNGrams().get(0).get(key));
                        }
                        else {
                            review_text.put(key,reviews.getReview().getAllNGrams().get(0).get(key));
                        }
                    }
                }
                
                //add into a hashmap with all occurences of the word
                //count total words of rating
            }
            //loop through each key(word) values and then multiple them together
            //divide by total number of owrds
        }  
        for(Long x : review_text.values()) {
            occurences += (float) x;
        }
        
        return (float) (occurences / pow(totalWords, counter));
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
        Set<Float> rating_bag = new HashSet<>();
        this.reviewText = Set.of(reviewText.split(" "));
    
        for(float i = 1.0f; i <= 5; i += 0.5f) {
            float pWord = 1f;
            float p_bag_rating = this.bag_rating(i); 
            float p_rating = this.ratings.get(i)[1];
            for(String word : this.probability.keySet()) {
                if(this.reviewText.contains(word)) {
                    pWord = pWord * this.probability.get(word);
                }
            }
            rating_bag.add((p_bag_rating * p_rating) / pWord);
        }    

        return Collections.max(rating_bag);
    }

}
