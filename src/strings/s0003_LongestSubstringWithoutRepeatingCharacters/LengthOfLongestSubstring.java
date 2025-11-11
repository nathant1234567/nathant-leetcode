package strings.s0003_LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        // map storing chars and their latest indices in string
        Map<Character, Integer> visitedChars = new HashMap<>();

        // sliding window boundaries
        for (int right = 0, left = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // if char is repeated and is inside current window
            if (visitedChars.containsKey(currentChar) &&
                    visitedChars.get(currentChar) >= left) {
                // move left pointer right past last occurrence
                left = visitedChars.get(currentChar) + 1;
            }

            // update max length of current valid window
            maxLength = Math.max(maxLength, right - left + 1);

            // record current character most recent index
            visitedChars.put(currentChar, right);
        }
        return maxLength;
    }
}
