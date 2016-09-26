/**
 * Created by Srikanth on 9/23/2016.
 */
public class Sentence {
    private String sentence;
    private BiGram[] bigramSet;
    private Corpus corpus;
    private double sentenceProbability;

    public Sentence() {
        this.sentence = ApplicationConstants.EmptyString;
        sentenceProbability = 1.00;
    }

    public Sentence(String sentence) {
        this.sentence = sentence;
        loadBigramSet();
        sentenceProbability = 1.00;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
        loadBigramSet();
    }

    public double getSentenceProbability() {
        return sentenceProbability;
    }

    public void loadBigramSet() {
        if (sentence.length() > 0) {
            String[] sentenceWords = sentence.split(" ");
            bigramSet = new BiGram[sentenceWords.length * sentenceWords.length];
            int index = 0;
            for (String prev : sentenceWords) {
                for (String current : sentenceWords) {
                    bigramSet[index++] = new BiGram(current, prev);
                }
            }
        }
    }

    public void setCorpus(Corpus corpus) {
        this.corpus = corpus;
    }

    public void calculateWithoutSmoothing() {
        // load all the counts of bigrams
        for (BiGram bigram : bigramSet) {
            String key = bigram.getPreviousWord() + " " + bigram.getWord();
            int count = corpus.getBiGramsCollection().containsKey(key) ?
                    corpus.getBiGramsCollection().get(key).getCount() : 0;
            bigram.setCount(count);
            if (corpus.getDictionary().containsKey(bigram.getPreviousWord()))
                bigram.setProbability((double) count / corpus.getDictionary().get(bigram.getPreviousWord()));
            else
                bigram.setProbability(0.0);
        }

        calculateSentenceProbabiltiy();
    }

    public void calculateAddOneSmoothing() {
        int vocabularyCount = corpus.getDictionary().size();

        // load all the counts of bigrams
        for (BiGram bigram : bigramSet) {
            String key = bigram.getPreviousWord() + " " + bigram.getWord();
            int count = corpus.getBiGramsCollection().containsKey(key) ? corpus.getBiGramsCollection().get(key).getCount() + 1 : 1;
            bigram.setCount(count);
            int wordCount = corpus.getDictionary().containsKey(bigram.getPreviousWord()) ?
                    corpus.getDictionary().get(bigram.getPreviousWord()) : 0;

            bigram.setProbability((double) count / (wordCount + vocabularyCount));
        }

        calculateSentenceProbabiltiy();
    }

    public void calculateGoodTuringSmoothing() {
        for (BiGram bigram : bigramSet) {
            String key = bigram.getPreviousWord() + " " + bigram.getWord();
            int count = corpus.getBiGramsCollection().containsKey(key) ?
                    corpus.getBiGramsCollection().get(key).getCount() : 0;
            double goodTuringCount = 0;
            if (count == 0) {
                goodTuringCount = corpus.getGoodTuringFrequency().containsKey(1) ? corpus.getGoodTuringFrequency().get(1) : 0;
            } else {
                int numerator = corpus.getGoodTuringFrequency().containsKey(count + 1) ?
                        corpus.getGoodTuringFrequency().get(count + 1) : 0;
                int denominator = corpus.getGoodTuringFrequency().containsKey(count) ? corpus.getGoodTuringFrequency().get(count) : 0;

                goodTuringCount = numerator == 0 || denominator == 0 ? 0 : (double) (count + 1) * numerator / denominator;
            }
            bigram.setGoodTuringCount(goodTuringCount);
            bigram.setProbability((double) goodTuringCount / corpus.getWordForms());
        }

        calculateSentenceProbabiltiy();
    }

    public void printBigramsCount(boolean isGoodTuring) {
        String[] sentenceWords = sentence.split(" ");
        for (String s : sentenceWords)
            System.out.format("%32s", s);

        System.out.println();

        for (int i = 0; i < bigramSet.length; ) {
            for (int j = i; j < i + sentenceWords.length; j++)
                System.out.format("%32s", isGoodTuring ? bigramSet[j].getGoodTuringCount() : bigramSet[j].getCount());

            System.out.println();
            System.out.println();
            i += sentenceWords.length;
        }

    }

    public void printBigramsProbability() {
        String[] sentenceWords = sentence.split(" ");

        for (String s : sentenceWords)
            System.out.format("%32s", s);

        System.out.println();

        for (int i = 0; i < bigramSet.length; ) {

            for (int j = i; j < i + sentenceWords.length; j++)
                System.out.format("%32s", bigramSet[j].getProbability());

            System.out.println();
            System.out.println();
            i += sentenceWords.length;
        }
    }

    public void printWordsInSentence() {
        String[] sentenceWords = sentence.split(" ");

        for (int i = 0; i < sentenceWords.length; i++)
            System.out.print(i + " - " + sentenceWords[i] + "       ");

        System.out.println();
        System.out.println();
    }

    private void calculateSentenceProbabiltiy() {
        String[] sentenceWords = sentence.split(" ");

        sentenceProbability = 1.0;

        for (int i = 1; i < sentenceWords.length; i++) {
            for (int j = 0; j < bigramSet.length; j++) {
                if (sentenceWords[i].equals(bigramSet[j].getWord()) &&
                        sentenceWords[i - 1].equals(bigramSet[j].getPreviousWord()))
                    sentenceProbability *= bigramSet[j].getProbability();
            }
        }

        sentenceProbability *= ((double)corpus.getDictionary().get(sentenceWords[0]) / corpus.getWordForms());
    }

}
