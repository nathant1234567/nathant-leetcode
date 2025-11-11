package string.s0005_LongestPalindromicSubstring;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String res = "";
        int resLen = 0;

        for (int i= 0; i < s.length(); i++) {
            // check odd length
            int l = i, r = i;

            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if ((r - l + 1) > resLen) {
                    res = s.substring(l, r + 1);
                    resLen = r - l + 1;
                }
                l -= 1;
                r += 1;
            }
            // check even length
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if ((r - l + 1) > resLen) {
                    res = s.substring(l, r + 1);
                    resLen = r - l + 1;
                }
                l -=1;
                r +=1;
            }
        }
        return res;
    }
}
