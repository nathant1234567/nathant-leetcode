/**
 * Problem: (2) Add Two Numbers
 * Link: https://leetcode.com/problems/two-sum
 *
 * Approach:
 *  -
 *
 * Time Complexity: O(m + n)
 * Space Complexity: O(m + n)
 */

package linkedlist.s0002_AddTwoNumbers;

import utils.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10; // calc carry for next iteration
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3))); // 342
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4))); // 465

        AddTwoNumbers solver = new AddTwoNumbers();
        ListNode result = solver.addTwoNumbers(l1, l2); // expected: 807 → 7→0→8

        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(" -> ");
            result = result.next;
        }
    }
}
