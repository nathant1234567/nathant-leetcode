# Problem: Number of Substrings With Only 1s
## Link: https://leetcode.com/problems/number-of-substrings-with-only-1s/
## Topics: Math, String

### Approach:
- Traverse the string and maintain a counter for consecutive `'1'` characters.
- When encountering `'1'`, increment the counter and add it to the result (modulo `10^9+7`).
- When encountering `'0'`, reset the counter to zero.
- This works because each new `'1'` contributes substrings equal to the current streak of consecutive ones.

### Time Complexity:
- **O(n)** — single pass through the string.

### Space Complexity:
- **O(1)** — no extra space used other than few variables.