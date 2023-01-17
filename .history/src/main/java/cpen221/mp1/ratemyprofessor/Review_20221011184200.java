package cpen221.mp1.ratemyprofessor;

import cpen221.mp1.ngrams.NGrams;

public class Review {
    public float score;
    char gender;
    NGrams review;

    public Review(float score, char gender, String review){
        this.score = score;
        this.gender = gender;
        String[] sentenceArray = review.split("\\.");
        this.review = new NGrams(sentenceArray);
    }
}
