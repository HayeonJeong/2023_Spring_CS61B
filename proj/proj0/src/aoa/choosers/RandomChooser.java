package aoa.choosers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.StdRandom;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("wordLength must be at least 1");
        }

        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (words.size() == 0) {
            throw new IllegalStateException("No words found of length " + wordLength + " in " + dictionaryFile);
        }

        int randomlyChosenWordNumber = StdRandom.uniform(words.size());
        chosenWord = words.get(randomlyChosenWordNumber);
        pattern = "-".repeat(wordLength);
    }


    @Override
    public int makeGuess(char letter) {
        int numOccurrences = 0;

        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == letter) {
                numOccurrences++;
                pattern = pattern.substring(0, i) + letter + pattern.substring(i + 1);
            }
        }

        return numOccurrences;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return chosenWord;
    }
}
