import java.io.*;
import java.util.*;

/**
 * Created by Srikanth on 9/23/2016.
 */
public class Corpus {

    private Hashtable<String, Integer> dictionary;
    private Hashtable<String, BiGram> biGramsCollection;
    private Hashtable<Integer, Integer> goodTuringFrequency;
    // Value of N
    private int wordForms;

    public Corpus(String fileLocation) {
        // Build all parameters of corpus
        try {
            dictionary = new Hashtable<>();
            biGramsCollection = new Hashtable<>();
            goodTuringFrequency = new Hashtable<>();
            Scanner scanner = new Scanner(new File(fileLocation));
            scanner.useDelimiter("[\\s]");
            String word;
            String previousWord = null;
            while (scanner.hasNext()) {

                word = scanner.next().trim();
                if (word.equals(ApplicationConstants.EmptyString))
                    continue;

                wordForms++;
                if (dictionary.containsKey(word))
                    dictionary.put(word, dictionary.get(word) + 1);
                else
                    dictionary.put(word, 1);

                if (previousWord != null) {
                    String key = previousWord + " " + word;
                    if (biGramsCollection.containsKey(key)) {
                        biGramsCollection.get(key).incrementCount();
                    } else {
                        BiGram biGram = new BiGram(word, previousWord);
                        biGram.incrementCount();
                        biGramsCollection.put(key, biGram);
                    }
                }
                previousWord = word;
            }
            // load good turing frequency
            for (BiGram bigram : biGramsCollection.values()) {
                if (goodTuringFrequency.containsKey(bigram.getCount()))
                    goodTuringFrequency.put(bigram.getCount(), goodTuringFrequency.get(bigram.getCount()) + 1);
                else
                    goodTuringFrequency.put(bigram.getCount(), 1);
            }

        }catch(FileNotFoundException fileException)
        {
            System.out.println("Input file name or path is wrong. Please provide correct filename in the argument");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Hashtable<String, Integer> getDictionary() {
        return dictionary;
    }

    public Hashtable<String, BiGram> getBiGramsCollection() {
        return biGramsCollection;
    }

    public int getWordForms() {
        return wordForms;
    }

    public Hashtable<Integer, Integer> getGoodTuringFrequency() {
        return goodTuringFrequency;
    }
}
