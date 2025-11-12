# Problem: Power Grid Maintenance
## Link: https://leetcode.com/problems/power-grid-maintenance/
## Topics: Array, HashTable, DepthFirstSearch, BreadthFirstSearch, UnionFind, Graph, Heap, OrderedSet

### Approach:
- Use a **Disjoint Set Union (DSU)** to group cities into connected components.
- Build a `gridMap` that maps each DSU root to a **TreeSet of online cities** within that component.
- Maintain a boolean array `online[]` marking which cities are currently online.
- Initially, all cities are online and belong to their respective DSU components.
- For each query:
    - **Type 1 (Check nearest online city):**
        - If the given city is online, return it immediately.
        - Otherwise, return the smallest online city in its component (TreeSet `.first()`), or `-1` if none.
    - **Type 2 (Take city offline):**
        - Mark the city offline and remove it from its component's TreeSet.
- Convert the results collected into an array and return it.

### Time Complexity:
- **Union-Find setup:** O(c α(c))
- **Per query:**
    - Type 1 lookup: O(log n) (TreeSet first / empty check)
    - Type 2 removal: O(log n)
- **Total:** O((c + q) log c)

### Space Complexity:
- **O(c)** for DSU, online array, and TreeSets.