package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        TreeMap<Character, List<Integer>> pMap = new TreeMap<>();
        List<String> leftWords = new ArrayList<>();

        // guesses에 있는 글짜가 pattern에 없으면 empty list
        // ex: ('o': [])
        // pattern에 있으면 전에처럼
        // ex: ('o' [1])
        for (int i = 0; i < guesses.size(); i++) {
            List<Integer> where = new ArrayList<>();
            Character c = guesses.get(i);
            pMap.put(c, where);
        }

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c != '-') {
                pMap.get(c).add(i);
            }
        }

        // for (int i = 0; i < pattern.length(); i++) {
        //     List<Integer> where = new ArrayList<>();
        //     char c = pattern.charAt(i);
        //     if (c != '-') {
        //         if (pMap.containsKey(c)) {
        //             pMap.get(c).add(i);
        //         } else {
        //             where.add(i);
        //             pMap.put(c, where);
        //         }
        //     }
        // }

        for (String w : words) {
            if (w.length() == pattern.length()) {
                if (check(w, pMap, guesses)) {
                    leftWords.add(w);
                }
            }
        }

        List<String> finalWords = new ArrayList<>(new HashSet<>(leftWords));

        System.out.println("pattern: " + pattern + " guesses: " + guesses);
        System.out.println("finalWords : " + finalWords);

        Map<Character, Integer> f_map = getFreqMapThatMatchesPattern(finalWords, guesses);
        System.out.println(f_map);

        char c = MyGetGuess(f_map);

        return c;
    }

    public boolean check(String w, TreeMap pMap, List<Character> guesses) {

        // word에 있으면 안되는 글자가 있는지 확인하기
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            List<Integer> positions = (List<Integer>) pMap.get(c);
            if (positions != null && !positions.contains(i)) {
                return false;
            }
        }

        // pMap에 저장되있는게 단어에 있는지 다 확인하기
        // ('o': [1]) 이면 word에 첫번째 글짜가 o인지 체크하기
        Set<Map.Entry<Character, List<Integer>> > pMapentries
                = pMap.entrySet();

        for (Map.Entry<Character, List<Integer>> entry : pMapentries) {
            Character pMapChar = entry.getKey();
            List<Integer> positions = entry.getValue();

            for (int i = 0; i < positions.size(); i++) {
                int position = positions.get(i);
                if (w.charAt(position) != pMapChar) {
                    return false;
                }
            }
        }

        // for (int i = 0; i < guesses.size(); i++) {
        //     Character c = guesses.get(i);
        //     if (!w.contains(String.valueOf(c))) {
        //         return false;
        //     }
        //     /*
        //     if (!pattern.contains(String.valueOf(c))) {
        //         if (w.contains(c)) {

        //         }
        //     }
        //     */
        // }

        return true;
    }


    public Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> finalWords, List<Character> guesses) {
        Map<Character, Integer> f_map = new TreeMap<>();

        for (int i = 0; i < finalWords.size(); i++) {
            String word = finalWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!guesses.contains(c)) {
                    if (!f_map.containsKey(c)) {
                        f_map.put(c, 1);
                    } else {
                        f_map.put(c, f_map.get(c) + 1);
                    }
                }
            }
        }
        return f_map;
    }

    public char MyGetGuess(Map<Character, Integer> f_map) {
        Map.Entry<Character, Integer> maxEntry = Collections.max(f_map.entrySet(),
                Comparator.comparing(Map.Entry::getValue));
        return maxEntry.getKey();
    }


    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
