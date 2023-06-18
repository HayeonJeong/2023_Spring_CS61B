package adventure;

import common.IntList;
import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class PalindromeStage implements AdventureStage {

    private final In in;
    private final Map<String, AdventureStage> responses;

    public PalindromeStage(In in) {
        this.in = in;
        AdventureStage nextStage = new FillerStage(
                "Mm, tasty. Briefly, you wonder if free will is an illusion. You hear some people talking " +
                        "about a machine in Soda and decide to check it out.",
                Map.of("go", new MachineStage(in))
        );
        this.responses = new TreeMap<>(Map.of(
                "va cafe", nextStage,
                "hearst food court", nextStage
        ));
    }

    @Override
    public void playStage() {
        System.out.println("""
                The Woz is a cool name, much better than "Soda 430". Soda 606 is a neat, symmetric conference room,
                like its number. We could rename The Woz to be palindromic, but it sounds so cool! Why not change the
                room number instead?
                (Give a palindromic room number.)
                """);
        while (true) {
            String input = in.readLine();
            while (!AdventureUtils.isInt(input)) {
                System.out.println("Please enter a valid integer.");
                input = this.in.readLine();
                if (AdventureUtils.isInt(input)) { break; }
            }

            IntList numLst = digitsToIntList(input);
            System.out.println(numLst);
            IntList reversedLst = reverseList(numLst);
            System.out.println(reversedLst);

            if (numLst.equals(reversedLst)) {
                System.out.println("Wow, nice room number!");
                break;
            }

            System.out.println("That's not a palindrome! Try again.");
        }
    }

    @Override
    public String nextStagePrompt() {
        return "Hmm, you're getting hungry. Where do you want to go?";
    }

    @Override
    public Map<String, AdventureStage> getResponses() {
        return responses;
    }

    /** Returns a new IntList with the contents of the original IntList in reverse order.*/
    private static IntList reverseList(IntList l) {
        IntList reversed = new IntList(l.first, null);;
        while (l.rest != null) {
            l = l.rest;
            reversed = new IntList(l.first, reversed);

        }
        return reversed;
    }

    /**
     * Given an input string of digits, converts it into an IntList of single-digit ints.
     * For example, the string "606" is converted to 6 -> 0 -> 6.
     */
    private static IntList digitsToIntList(String s) {
        int[] a = new int[s.length()];
        System.out.println(a);
        for (int i = 0; i < s.length(); i++) {
            System.out.println(i);
            a[i] = Character.getNumericValue(s.charAt(i));
            //System.out.println(i + " " + a[s.length() - i]);
        }
        return IntList.of(a);
    }

}