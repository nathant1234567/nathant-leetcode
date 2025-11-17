# Problem: Minimum Number of Operations to Make All Array Elements Equal to 1
## Link: https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/
## Topics: Array, Math, NumberTheory

### Approach:
- If the array already contains at least one `1`, then converting all elements to `1` is straightforward because `gcd(1, x) = 1`.  
  Each non-1 element requires exactly one operation.  
  Count how many elements equal `1`, and the answer is `n - count_of_ones`.

- If the array contains no `1`s, then we must create the first `1` using gcd operations.  
  A `1` can only be produced if the gcd of some subarray equals `1`.  
  Therefore, find the shortest subarray whose gcd is `1`.  
  Let its length be `L`.

- Reducing that subarray into a single `1` takes `L - 1` operations, because each gcd operation collapses the subarray by one element.

- Once the first `1` is created, turning the remaining `n - 1` elements into `1` takes exactly `n - 1` operations.

- Thus, when there is no initial `1`, the total number of operations is:
  **(L - 1) + (n - 1)**.

- If no subarray has gcd equal to `1`, then it is impossible to create a `1`, and the correct answer is `-1`.

### Time Complexity:
- **O(n² log A)**  
  We evaluate all subarrays and compute gcds.  
  Each gcd operation takes logarithmic time.

### Space Complexity:
- **O(1)**  
  Only constant extra space is used.
