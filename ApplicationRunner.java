/**
 * Created by Srikanth on 9/23/2016.
 */
public class ApplicationRunner {

    public static void main(String[] args) {
        if(args.length == 0)
            System.out.print("Please mention the filename as part of the argument");

        Corpus corpus = new Corpus(args[0]);
        Sentence sentence = new Sentence("The president has relinquished his control of the company's board.");
        sentence.setCorpus(corpus);

        System.out.println("Analysis of S1 sentence:   ");
        System.out.println("========================");
        System.out.println();

        sentence.calculateWithoutSmoothing();
        System.out.println("Count of Bigrams without Smoothing");
        System.out.println("==================================");
        sentence.printBigramsCount(false);
        System.out.println("Probability of Bigrams without Smoothing");
        System.out.println("========================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence without Smoothing: " + sentence.getSentenceProbability());
        System.out.println();

        sentence.calculateAddOneSmoothing();
        System.out.println("Count of Bigrams with Add one Smoothing");
        System.out.println("=======================================");
        sentence.printBigramsCount(false);
        System.out.println("Probability of Bigrams with Add one Smoothing");
        System.out.println("=============================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence with Add one Smoothing: " + sentence.getSentenceProbability());
        System.out.println();

        sentence.calculateGoodTuringSmoothing();
        System.out.println("Count of Bigrams with Good Turing Smoothing");
        System.out.println("===========================================");
        sentence.printBigramsCount(true);
        System.out.println("Probability of Bigrams with Good Turing Smoothing");
        System.out.println("=================================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence with Good Turing Smoothing: " + sentence.getSentenceProbability());
        System.out.println();

        System.out.println("Analysis of S2 sentence: ");
        System.out.println("========================");
        sentence.setSentence("The chief executive officer said the last year revenue was good.");
        System.out.println();

        sentence.calculateWithoutSmoothing();
        System.out.println("Count of Bigrams without Smoothing");
        System.out.println("==================================");
        sentence.printBigramsCount(false);
        System.out.println("Probability of Bigrams without Smoothing");
        System.out.println("========================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence without Smoothing: " + sentence.getSentenceProbability());
        System.out.println();

        sentence.calculateAddOneSmoothing();
        System.out.println("Count of Bigrams with Add one Smoothing");
        System.out.println("=======================================");
        sentence.printBigramsCount(false);
        System.out.println("Probability of Bigrams with Add one Smoothing");
        System.out.println("========================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence with Add one Smoothing: " + sentence.getSentenceProbability());
        System.out.println();

        sentence.calculateGoodTuringSmoothing();
        System.out.println("Count of Bigrams with Good Turing Smoothing");
        System.out.println("===========================================");
        sentence.printBigramsCount(true);
        System.out.println("Probability of Bigrams with Good Turing Smoothing");
        System.out.println("========================================");
        sentence.printBigramsProbability();
        System.out.println("Probabiltiy of Sentence with Good Turing Smoothing: " + sentence.getSentenceProbability());
    }
}
