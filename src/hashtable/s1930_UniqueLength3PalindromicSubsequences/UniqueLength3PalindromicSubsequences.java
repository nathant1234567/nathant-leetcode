package hashtable.s1930_UniqueLength3PalindromicSubsequences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueLength3PalindromicSubsequences {
    public int neetCodeMethod(String s) {
        Set<String> res = new HashSet<>();
        Set<Character> left = new HashSet<>();
        Map<Character, Integer> right = new HashMap<>();
        for (char ch : s.toCharArray()) {
            right.merge(ch, 1, Integer::sum);
        }

        for (char m : s.toCharArray()) {
            right.put(m, right.get(m) - 1);
            for (char c : left) {
                if (right.getOrDefault(c, 0) > 0) {
                    res.add("" + c + m + c);
                }
            }
            left.add(m);
        }
        return res.size();
    }

    public int moreEfficientMethod(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        for (int i = 0; i < 26; i++) {
            first[i] = -1;
            last[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        int count = 0;

        for (int c = 0; c < 26; c++) {
            if (first[c] != -1 && last[c] - first[c] > 1) {
                boolean[] seen = new boolean[26];

                for (int i = first[c] + 1; i < last[c]; i++) {
                    seen[s.charAt(i) - 'a'] = true;
                }

                for (boolean b : seen) {
                    if (b) count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        UniqueLength3PalindromicSubsequences solution = new UniqueLength3PalindromicSubsequences();
        System.out.println(solution.neetCodeMethod("aabca"));
        System.out.println(solution.moreEfficientMethod("aabca"));
    }
}

