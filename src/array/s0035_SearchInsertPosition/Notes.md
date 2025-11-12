# Problem: Search Insert Position
## Link: https://leetcode.com/problems/search-insert-position/
## Topics: Array, BinarySearch

### Approach:
- Use binary search to find the target’s position.
- Initialize two pointers: `left = 0` and `right = nums.length - 1`.
- While `left <= right`:
    - Compute `mid = left + (right - left) / 2` to avoid overflow.
    - If `nums[mid] == target`, return `mid`.
    - If `nums[mid] < target`, search the right half (`left = mid + 1`).
    - Otherwise search the left half (`right = mid - 1`).
- If the target is not found, `left` will be the correct insertion index.

### Time Complexity:
- **O(n log n)**

### Space Complexity:
- **O(1)**