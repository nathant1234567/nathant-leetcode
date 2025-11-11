# Problem: Median of Two Sorted Arrays
## Link: https://leetcode.com/problems/median-of-two-sorted-arrays

## Topics: BinarySearch, Array, DivideAndConquer

### Approach (written by chatgpt, code is NOT lol):
- Use **binary search** on the smaller array to find a partition where:
    - All elements in the left half of both arrays are less than or equal to all elements in the right half.
- Let `i` be the partition index in `nums1`, and `j = (m + n + 1) / 2 - i` for `nums2`.
- Compare boundary elements:
    - `Aleft`, `Aright` from `nums1`
    - `Bleft`, `Bright` from `nums2`
- If partition is valid (`Aleft <= Bright` and `Bleft <= Aright`):
    - If total length is odd → median = `max(Aleft, Bleft)`
    - Else → median = `(max(Aleft, Bleft) + min(Aright, Bright)) / 2`
- Adjust search range:
    - If `Aleft > Bright`, move left
    - Else, move right

### Time Complexity:
- O(log(min(m, n)))

### Space Complexity:
- O(1)
