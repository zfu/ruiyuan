/**
 * 
 * 103. Linked List Cycle II Given a linked list, return the node where the
 * cycle begins.
 * 
 * If there is no cycle, return null.
 * 
 * Example Given -21->10->4->5, tail connects to node index 1，return 10
 * Explanation： The last node 5 points to the node whose index is 1, which is
 * 10, so the entrance of the ring is 10
 * 
 * Challenge Follow up:
 * 
 * Can you solve it without using extra space?
 */

 /**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        } 
        
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}