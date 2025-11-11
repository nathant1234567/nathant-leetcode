
# Problem: Longest Palindromic Substring
## Link: https://leetcode.com/problems/longest-palindromic-substring

## Topics: TwoPointers, String, DynamicProgramming

### Approach:
- Iterate through each index `i` in the string as a potential palindrome center.
- Expand around `i` to check for:
    - **Odd-length palindromes:** center at a single character (`l = i`, `r = i`)
    - **Even-length palindromes:** center between two characters (`l = i`, `r = i + 1`)
- While characters at both ends are equal (`s.charAt(l) == s.charAt(r)`), expand outward.
- Update the result when a longer palindrome is found using `substring(l, r + 1)`.
- Continue until all possible centers are checked.

### Time Complexity: 
- O(n²)

### Space Complexity: 
- O(1)
