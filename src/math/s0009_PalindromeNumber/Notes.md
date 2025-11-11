# Problem: Palindrome Number
## Link: https://leetcode.com/problems/palindrome-number/
## Topics: Math

### Approach:
- A palindrome reads the same forward and backward.
- Negative numbers and numbers ending in `0` (except `0` itself) cannot be palindromes.
- Instead of reversing the entire number (which may cause overflow), reverse **half** of it:
    - Continuously extract digits from the end of `x` and build `reversedHalf`.
    - Stop when `x <= reversedHalf` (meaning half the digits are processed).
- For even-length numbers, `x == reversedHalf` indicates a palindrome.
- For odd-length numbers, remove the middle digit using `reversedHalf / 10` and compare again.

### Time Complexity:
- O(log(n))
    - Each iteration removes one or two digits from `x`.

### Space Complexity:
- O(1)
    - Only a few integer variables are used.