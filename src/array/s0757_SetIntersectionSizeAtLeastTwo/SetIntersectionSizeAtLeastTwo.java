package array.s0757_SetIntersectionSizeAtLeastTwo;

import java.util.Arrays;

public class SetIntersectionSizeAtLeastTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        int res = 0;
        int p1 = -1, p2 = -1;
        Arrays.sort(intervals, (a, b) ->
                a[1] == b[1] ? Integer.compare(b[0], a[0])
                             : Integer.compare(a[1], b[1])
        );

        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (p2 < left) {
                res += 2;
                p1 = right -1;
                p2 = right;
            } else if (p1 < left) {
                res++;
                if (p2 == right) {
                    p1 = right - 1;
                } else {
                    p1 = p2;
                    p2 = right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SetIntersectionSizeAtLeastTwo solution = new SetIntersectionSizeAtLeastTwo();
        System.out.println(solution.intersectionSizeTwo(new int[][] {{1,3},{3,7},{8,9}}));
    }
}
