/**
 * Created by Srikanth on 9/23/2016.
 */
public class BiGram {
    private String word;
    private String previousWord;
    private int count;
    private double goodTuringCount;
    private double probability;

    public BiGram() {
        word = ApplicationConstants.EmptyString;
        previousWord = ApplicationConstants.EmptyString;
        count = 0;
        probability = 0.0;
    }

    public BiGram(String word, String previousWord) {
        this.word = word;
        this.previousWord = previousWord;
    }

    public String getWord() {
        return word;
    }

    public String getPreviousWord() {
        return previousWord;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(){
        this.count++;
    }


    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getGoodTuringCount() {
        return goodTuringCount;
    }

    public void setGoodTuringCount(double goodTuringCount) {
        this.goodTuringCount = goodTuringCount;
    }
}
