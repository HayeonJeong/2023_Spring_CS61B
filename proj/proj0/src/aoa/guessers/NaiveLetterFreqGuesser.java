package aoa.guessers;

import aoa.utils.FileUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character, Integer> f_map = new TreeMap<Character, Integer>();
        //System.out.println(f_map);
        int count = 0;

        for (char i = 0; i < 26; i++) {
            for (int j = 0; j < this.words.size(); j++) {
                //String c = this.words.get(j);
                for (int k = 0; k < this.words.get(j).length(); k++) {
                    if ((char)(i + 97) == this.words.get(j).charAt(k)) {
                        ++count;
                    }
                }
            }
            if (count != 0) {
                f_map.put((char) (i + 97), count);
            }
            count = 0;
        }
        return f_map;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Map<Character, Integer> f_map = getFrequencyMap();
        if (f_map.size() == 0) {
            return '?';
        }
        for (int i = 0; i < guesses.size(); i++) {
            f_map.remove(guesses.get(i));
        }

        Comparator<Entry<Character, Integer>> comparator = new Comparator<Entry<Character, Integer>>() {
            public int compare(Entry<Character, Integer> E1, Entry<Character, Integer> E2) {
                return E1.getValue().compareTo(E2.getValue());
            }
        };

        Entry<Character, Integer> maxEntry = Collections.max(f_map.entrySet(), comparator);

        return maxEntry.getKey();
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
