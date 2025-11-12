package array.s3607_PowerGridMaintenance;

import java.util.*;

public class PowerGridMaintenance {
    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n + 1];
            // initially, every node is its own parent (aka. self root)
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                if (pa < pb) parent[pb] = pa;
                else parent[pa] = pb;
            }
        }
    }


    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);

        for (int[] conn : connections)  {
            dsu.union(conn[0], conn[1]);
        }

        Map<Integer, TreeSet<Integer>> gridMap = new HashMap<>();

        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            gridMap.putIfAbsent(root, new TreeSet<>());

        }

        boolean[] online = new boolean[c+1];
        Arrays.fill(online, true);

        for (int i = 1; i <= c; i++) {
            gridMap.get(dsu.find(i)).add(i);
        }

        List<Integer> resultList = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];
            int root = dsu.find(x);

            if (type == 1) {
                if (online[x]) {
                    resultList.add(x);
                } else {
                    TreeSet<Integer> set = gridMap.get(root);
                    if (set.isEmpty()) resultList.add(-1);
                    else resultList.add(set.first());
                }
            } else if (type == 2) {
                if (online[x]) {
                    online[x] = false;
                    gridMap.get(root).remove(x);
                }
            }
        }

        int[] result = new int [resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        PowerGridMaintenance solution = new PowerGridMaintenance();
        System.out.println(Arrays.toString(solution.processQueries(3, new int[][]{}, new int[][]{{1, 1}, {2, 1}, {1, 1}})));
    }
}
