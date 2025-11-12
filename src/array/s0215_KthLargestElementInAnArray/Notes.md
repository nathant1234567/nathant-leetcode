# Problem: Kth Largest Element in an Array
## Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
## Topics: Array, DivideandConquer, Sorting, Heap, Quickselect

### Approach:
- Use a **min-heap (priority queue)** to keep track of the top `k` largest elements.
- Iterate through each number in the array:
    - Add the number to the heap.
    - If the heap size exceeds `k`, remove the smallest element.
- After processing all numbers, the root of the heap (`peek()`) contains the **kth largest** element.
- This works because the heap always stores the largest `k` numbers seen so far.

### Time Complexity:
- **O(n log k)** — inserting into a heap of size `k`.

### Space Complexity:
- **O(k)** — the heap holds at most `k` elements.