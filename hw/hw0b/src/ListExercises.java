import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        int sum_result = 0;
        if (L.size() > 0) {
            for (int i = 0; i < L.size(); i++) {
                sum_result += L.get(i);
            }
        }

        return sum_result;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> new_list = new ArrayList<>();
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i) % 2 == 0) {
                new_list.add(L.get(i));
            }
        }

        return new_list;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> new_list = new ArrayList<>();
        for (int i = 0; i < L1.size(); i++) {
            for (int j = 0; j < L2.size(); j++) {
                if (L1.get(i) == L2.get(j)) {
                    new_list.add(L2.get(j));
                }
            }
        }

        return new_list;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (int i = 0; i < words.size(); i++) {    //배열 사이즈 알아보기 = size()
            String s = words.get(i);
            for (int j = 0; j < s.length(); j++) {  //String 사이즈 알아보기 = length()
                if (s.charAt(j) == c) {
                    count += 1;
                }
            }
        }
        return count;
    }
}
