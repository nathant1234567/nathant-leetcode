package arrays.s0004_MedianOfTwoSortedArrays;

import java.util.Arrays;

public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = (total + 1) / 2;    // use (total+1)/2 to handle odd/even clearly

        int l = 0, r = m;              // i ranges [0..m] (number of elements from nums1 on left)
        while (l <= r) {
            int i = (l + r) / 2;       // partition in nums1 (count of elements on left)
            int j = half - i;         // partition in nums2

            // Edge handling: use ±∞ for out-of-bound partitions
            int Aleft  = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
            int Aright = (i < m) ? nums1[i]     : Integer.MAX_VALUE;
            int Bleft  = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
            int Bright = (j < n) ? nums2[j]     : Integer.MAX_VALUE;

            // Check if correct partition found
            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 == 1) {
                    // if odd
                    return (double) Math.max(Aleft, Bleft);
                } else {
                    // if even
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
                // Adjust binary search
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays solution = new FindMedianSortedArrays();
        int[] one = {1, 2};
        int[] two = {3, 4};
        double result = solution.findMedianSortedArrays(one, two);
        System.out.println(result);
    }
}
