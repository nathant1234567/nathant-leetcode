package string.s0013_RomanToInteger;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int n = s.length();

        // Loop through each character in the string
        for (int i = 0; i < n; i++) {
            // Get the integer value of the current Roman numeral
            int currentVal = romanMap.get(s.charAt(i));

            // Check if the next Roman numeral is larger (for subtraction cases)
            if (i < n - 1 && currentVal < romanMap.get(s.charAt(i + 1))) {
                result -= currentVal;
            } else {
                result += currentVal;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RomanToInteger solution = new RomanToInteger();
        System.out.println(solution.romanToInt("VVVII"));
    }
}
