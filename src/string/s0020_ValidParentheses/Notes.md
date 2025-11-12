# Problem: Valid Parentheses
## Link: https://leetcode.com/problems/valid-parentheses/
## Topics: String, Stack

### Approach:
- Use a stack to store opening parentheses `(`, `{`, `[` as you iterate through the string.
- When encountering a closing parenthesis:
    - Return false immediately if the stack is empty (no matching opening bracket).
    - Pop the top of the stack and check if it correctly matches the closing bracket.
- After processing all characters, ensure the stack is empty (all brackets matched correctly).

### Time Complexity:
- **O(n)** — Each character is pushed and popped at most once.

### Space Complexity:
- **O(n)** in the worst case — when all characters are opening brackets.