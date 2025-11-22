# Problem: Longest Common Prefix
## Link: https://leetcode.com/problems/longest-common-prefix/
## Topics: String

### Approach:
- Start by assuming the first string is the prefix.
- Iterate through the remaining strings and check if each begins with the current prefix.
- If not, repeatedly shorten the prefix by removing the last character until it matches or becomes empty.
- Return the remaining prefix.

### Time Complexity:
- **O(n * m)** —  
  where `n` = number of strings,  
  and `m` = length of the shortest string.

### Space Complexity:
- **O(1)** —  
  only variables for prefix tracking are used.