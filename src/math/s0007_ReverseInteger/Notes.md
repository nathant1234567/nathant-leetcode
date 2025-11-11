# Problem: Reverse Integer
## Link: https://leetcode.com/problems/reverse-integer/
## Topics: Math

### Approach:
- Extract digits one by one using modulo and division (`pop = x % 10`, then `x /= 10`).
- Build the reversed number by `rev = rev * 10 + pop`.
- Before updating `rev`, check for **overflow** and **underflow** conditions:
    - If `rev > Integer.MAX_VALUE / 10` or (`rev == Integer.MAX_VALUE / 10 && pop > 7`), return `0`.
    - If `rev < Integer.MIN_VALUE / 10` or (`rev == Integer.MIN_VALUE / 10 && pop < -8`), return `0`.
- Continue until all digits are processed.
- Return the reversed integer if no overflow occurs.

### Time Complexity:
- O(log(n))
    - Each iteration removes one digit from `x`.

### Space Complexity:
- O(1)
    - Only constant variables are used.