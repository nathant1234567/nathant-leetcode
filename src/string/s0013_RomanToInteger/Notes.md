# Problem: Roman To Integer
## Link: https://leetcode.com/problems/roman-to-integer/
## Topics: HashTable, Math, String

### Approach:
- Create a hashmap mapping each Roman numeral character to its integer value.
- Loop through the string from left to right.
- For each character:
    - Compare its value to the next character's value.
    - If the next value is larger, subtract the current value (handles cases like IV, IX, XL, etc.).
    - Otherwise, add the current value.
- Return the accumulated sum.

### Time Complexity:
- **O(n)** — We iterate through the string once.

### Space Complexity:
- **O(1)** — The Roman numeral map has a fixed size (7 entries).
