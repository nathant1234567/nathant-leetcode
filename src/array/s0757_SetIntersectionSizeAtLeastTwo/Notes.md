# Problem: Set Intersection Size At Least Two
## Link: https://leetcode.com/problems/set-intersection-size-at-least-two/
## Topics: Array, Greedy, Sorting

### Approach:
We use a greedy strategy based on sorting intervals by their end point (ascending).  
If two intervals end at the same value, we sort by starting point (descending).  
This ensures that when picking points, we put them as far to the right as possible, which maximizes compatibility with future intervals.

We maintain two points (p1, p2) representing the two largest chosen intersection points so far.

For each interval:
- If the interval starts after p2, we need to add two new points: `right - 1` and `right`.
- If the interval starts after p1 (but before or equal to p2), we add one new point: `right`.
- Otherwise, the interval already contains both p1 and p2, so no new points are added.

This greedy method ensures minimal points added while satisfying the requirement that every interval has at least two intersection points.

### Time Complexity:
- Sorting intervals takes **O(n log n)**.
- Linear scan afterward is **O(n)**.
- Overall: **O(n log n)**.

### Space Complexity:
- Only constant extra variables are used.
- **O(1)** 