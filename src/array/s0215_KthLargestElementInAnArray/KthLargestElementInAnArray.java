package array.s0215_KthLargestElementInAnArray;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) heap.poll();
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray solution = new KthLargestElementInAnArray();
        System.out.println(solution.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
    }
}
