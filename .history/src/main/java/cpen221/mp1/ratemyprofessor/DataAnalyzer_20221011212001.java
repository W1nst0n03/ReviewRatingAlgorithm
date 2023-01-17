package cpen221.mp1.ratemyprofessor;

import cpen221.mp1.datawrapper.DataWrapper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataAnalyzer {

    public final ArrayList<Review> reviewList = new ArrayList<>();
    public String masterString = "";
    /**
     * Create an object to analyze a RateMyProfessor dataset
     * @param dataSourceFileName the name of the file that contains the data
     * @throws FileNotFoundException if the file does not exist or is not found
     */
    public DataAnalyzer(String dataSourceFileName) throws FileNotFoundException {
        DataWrapper dw = new DataWrapper(dataSourceFileName);
        dw.nextLine();
        
        while(dw.hasNextLine()){
            String currentReview = dw.nextLine();
            String[] x = currentReview.split(",",3);
            float tempScore = Float.parseFloat(x[0]);
            char tempGender = x[1].charAt(0);
            String tempReview = x[2];
            this.masterString += " " + currentReview;
            reviewList.add(new Review(tempScore,tempGender,tempReview));
        }
    }

    /**
     * Obtain a histogram with the number of occurrences of the
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH).
     * @param query the search term, which contains between one and three words
     * @return the histogram with the number of occurrences of the
     * query term in the RMP comments, categorized as men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public Map<String, Long> getHistogram(String query) {
        Map<String, Long> histogram = new HashMap<>();
        int queryLength = query.split(" ").length;
        histogram.put("ML",0L);
        histogram.put("WL",0L);
        histogram.put("MM",0L);
        histogram.put("WM",0L);
        histogram.put("MH",0L);
        histogram.put("WH",0L);
        for(Review review: reviewList){
            if(review.review.getAllNGrams().get(queryLength-1).containsKey(query)){
                if(review.gender == 'M'){
                    if(review.score <= 2){
                        histogram.replace("ML",histogram.get("ML")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    } else if(review.score <= 3.5) {
                        histogram.replace("MM",histogram.get("MM")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    } else{
                        histogram.replace("MH",histogram.get("MH")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    }
                } else if (review.gender == 'W') {
                    if(review.score <= 2){
                        histogram.replace("WL",histogram.get("WL")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    } else if(review.score <= 3.5) {
                        histogram.replace("WM",histogram.get("WM")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    } else{
                        histogram.replace("WH",histogram.get("WH")+review.review.getAllNGrams().get(queryLength-1).get(query));
                    }
                }
            }
        }
        return histogram;
    }

    /**
     * Display the histogram data as a chart
     * @param histogram with entries for men-low (ML),
     * women-low (WL), men-medium (MM), women-medium (WM),
     * men-high (MH), and women-high (WH)
     */
    public void showHistogramChart(Map<String, Long> histogram) {
        // TODO: This is an optional component but is
        //  instructive in that graphing may not be that hard!
        //  See the histogram package.
    }
}
