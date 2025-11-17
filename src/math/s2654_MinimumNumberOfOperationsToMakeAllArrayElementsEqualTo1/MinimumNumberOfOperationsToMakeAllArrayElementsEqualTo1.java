package math.s2654_MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1;

public class MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int minOperations(int[] nums) {
        int n = nums.length;

        int ones = 0;
        for (int x : nums) {
            if (x == 1) ones++;
        }

        if (ones > 0) {
            return n - ones;
        }

        int best = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i + 1; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    best = Math.min(best, j - i + 1);
                    break;
                }
            }
        }
        if (best == Integer.MAX_VALUE) return -1;
        return (best - 1) + (n - 1);
    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 solution = new MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1();
        System.out.println(solution.minOperations(new int[] {2, 6, 3, 4}));
    }
}
