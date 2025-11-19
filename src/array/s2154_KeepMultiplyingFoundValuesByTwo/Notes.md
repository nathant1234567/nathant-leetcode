# Problem: Keep Multiplying Found Values by Two
## Link: https://leetcode.com/problems/keep-multiplying-found-values-by-two/
## Topics: Array, HashSet, Sorting, Simulation


### Approach:
- Insert all numbers from the array into a HashSet for O(1) lookups.
- While the current value `original` exists in the set, multiply it by 2.
- Return the final value once it no longer appears in the set.

### Time Complexity:
- O(n): inserting all elements into the set is O(n)
- Lookup operations for doubling are O(1) each, so the loop is efficient.

### Space Complexity:
- O(n): HashSet stores up to all elements from the input.
