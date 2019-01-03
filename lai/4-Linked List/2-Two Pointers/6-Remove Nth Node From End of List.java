/**
 * 
 Given a linked list, remove the nth node from the end of list and return its head.

Assumptions
If n is not valid, you do not need to do anything to the original list.
Try to do this in one pass.

Examples

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
 */

 /**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
      // Write your solution here
      if (head == null || n <= 0) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode fast = dummy, slow = dummy;
      for (int i = 1; i <= n + 1; i++) {
        if (fast == null) {
          return head;
        }
        fast = fast.next;
      }
      
      while (fast != null) {
        fast = fast.next;
        slow = slow.next;
      }
      slow.next = slow.next.next;
      return dummy.next;
    }
  }
  