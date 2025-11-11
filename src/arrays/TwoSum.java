/**
 * Problem: Two Sum
 * Link: https://leetcode.com/problems/two-sum
 *
 * Approach:
 *  - Use a HashMap to find complements of numbers in one pass.
 *
 * Time Complexity: O(n) (well n^2 super worst case)
 * Space Complexity: O(n)
 */

package arrays;

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indices = new HashMap<>(); // stores indices of elements in map

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;

            // check if the complement already exists in the map
            if (indices.containsKey(diff)) {
                // return that pair if it does
                return new int[] {indices.get(diff), i};
            }

            // otherwise add the current num and index to map
            indices.put(num, i);
        }

        // fallback if no match found
        return new int[0];
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
