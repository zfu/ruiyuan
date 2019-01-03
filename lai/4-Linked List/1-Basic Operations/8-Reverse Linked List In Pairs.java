/**
 * 
 Reverse pairs of elements in a singly-linked list.

Examples

L = null, after reverse is null
L = 1 -> null, after reverse is 1 -> null
L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
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
    public ListNode reverseInPairs(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null) {
        return head;
      }
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode curr = dummy;
      while (curr.next != null && curr.next.next != null) {
        ListNode p1 = curr.next;
        ListNode p2 = curr.next.next;
        curr.next = p2;
        p1.next = p2.next;
        p2.next = p1;
        curr = p1;
      }
      return dummy.next;
    }
  }
  