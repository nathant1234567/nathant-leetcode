package array.s2154_KeepMultiplyingFoundValuesByTwo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeepMultiplyingFoundValuesByTwo {

    public int findFinalValue(int[] nums, int original) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        while (map.containsValue(original)) {
            original *= 2;
        }
        return original;
    }

    public int findFinalValueOptimised(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        while (set.contains(original)) {
            original *= 2;
        }
        return  original;
    }

    public static void main(String[] args) {
        KeepMultiplyingFoundValuesByTwo solution = new KeepMultiplyingFoundValuesByTwo();
        System.out.println(solution.findFinalValue(new int[] {8,19,4,2,15,3}, 4));
    }
}
