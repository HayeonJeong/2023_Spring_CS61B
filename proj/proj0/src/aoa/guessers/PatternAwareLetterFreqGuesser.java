package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
        //System.out.println(words);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        //System.out.println(words);

        int g_count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '-') {
                g_count++;
            }
        }

        List<String> new_words = new ArrayList<>(); // words 에서 적용 가능한 남은 단어들.
        List<Character> letter = new ArrayList<>(); // 어떤 letter가 적용되는지.

        int bar_count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '-') {
                bar_count++;
            }
        }
        if (bar_count == pattern.length()) {
            new_words = words;
        }

        else {
            for (int i = 0; i < words.size(); i++) {
                String c = words.get(i); // new_words에서 순서대로 꺼낸 단어 하나.
                if (pattern.length() == c.length()) {
                    int count = 0;

                    for (int j = 0; j < c.length(); j++) {
                        if (c.charAt(j) == pattern.charAt(j)) {
                            if (!new_words.contains(c)) {
                                count++;
                                //System.out.println(c + " " + j + " " + c.charAt(j) + " " + count);
                                if (count == g_count) {
                                    new_words.add(c);
                                }
                            }
                            if (!letter.contains(c.charAt(j))) {
                                letter.add(c.charAt(j));
                            }
                        }
                    }
                }
            }
        }
        System.out.println("new_words : " + pattern + " guesses: " + guesses);
        System.out.println("new_words : " + new_words);

        Map<Character, Integer> f_map = getFreqMapThatMatchesPattern(new_words, guesses);
        //System.out.println("f_map : " + f_map);
        //System.out.println("new_words : " + new_words);
        //System.out.println("letter: " + letter);

        char c = MyGetGuess(f_map);
        //System.out.println(c);

        return c;
    }

    /*
    public List<String> keepOnlyWordsThatMatchPattern(, String pattern) {

        return new_words;
    }
    */

    public Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> new_words, List<Character> guesses) {
        Map<Character, Integer> f_map = new TreeMap<Character, Integer>();

        int count = 0;
        for(char i = 0; i < 26; i++) {
            for (int j = 0; j < new_words.size(); j++) {
                //String c = this.words.get(j);
                for (int k = 0; k < new_words.get(j).length(); k++) {
                    if ((char)(i + 97) == new_words.get(j).charAt(k)) {
                        ++count;
                    }
                }
            }
            if (count != 0) {
                f_map.put((char) (i + 97), count);
            }
            count = 0;
        }
        for (int i = 0; i < guesses.size(); i++){
            f_map.remove(guesses.get(i));
        }

        return f_map;
    }

    public char MyGetGuess(Map<Character, Integer> f_map) {
        System.out.println(f_map);
        Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> E1, Map.Entry<Character, Integer> E2) {
                return E1.getValue().compareTo(E2.getValue());
            }
        };
        System.out.println(comparator);

        Map.Entry<Character, Integer> maxEntry = Collections.max(f_map.entrySet(), comparator);

        return maxEntry.getKey();
    }

    public char MyGetGuess2(Map<Character, Integer> f_map) {
        int max = 0;
        char c = '?';

        for (char key : f_map.keySet()) {
            Integer value = f_map.get(key);

            if (value > max) {
                max = value;
                if ((int)key < (int)c) {
                    System.out.println("c: " + c + "," + "key: " + key);
                    c = key;
                    System.out.println("key: " + key);
                }
            }
        }

        return c;
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}