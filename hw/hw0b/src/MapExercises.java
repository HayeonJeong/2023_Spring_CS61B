import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> lower_map = new TreeMap<>();

        for(char i='a'; i <= 'z'; i++) {
            lower_map.put(i, (int)i - 96);
            //System.out.println((char)i);
            //System.out.println(lower_map.get((char)i) - 96);
        }

        return lower_map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> integer_map = new TreeMap<>();

        for(int i = 0; i < nums.size(); i++) {
            int integer_new = (int) Math.pow(nums.get(i), 2);
            integer_map.put(nums.get(i), integer_new);
        }

        return integer_map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> words_map = new TreeMap<>();
        int count = 0;

        for(int i = 0; i < words.size(); i++) {
            String new_word = words.get(i);
            if (words_map.get(new_word) == null) {
                for (int j = 0; j < words.size(); j++) {
                    if (new_word == words.get(j)) {
                        count += 1;
                        //System.out.println(new_word);
                    }
                }
                words_map.put(new_word, count);
                count = 0;
            }
        }

        //map에 이미 들어있는지 확인해야.

        return words_map;
    }
}
