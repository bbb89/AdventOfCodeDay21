package com.lukasz;

import java.util.*;

public class Main {

    private static Map<String, String> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input: ");
        Map<String, String> rules = new HashMap<>();
        String line;


        while(!(line = scanner.nextLine()).isEmpty()) {
            String[] parts = line.split(" => ");

            rules.put(parts[0], parts[1]);
        }

        return rules;
    }

    public static void main(String[] args) {
        Map<String, String> rules = getInput();
        Solution solution = new Solution(rules, 5);
        System.out.println("There are " + solution.getSolution() + " pixels on after 5 iterations");
        Solution solution2 = new Solution(rules, 18);
        System.out.println("There are " + solution2.getSolution() + " pixels on after 18 iterations");
    }
}
