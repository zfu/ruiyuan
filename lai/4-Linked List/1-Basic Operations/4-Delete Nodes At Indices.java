/**
 * 
 Given a linked list and an sorted array of integers as the indices in the list. Delete all the nodes at the indices in the original list.

Examples

1 -> 2 -> 3 -> 4 -> NULL, indices = {0, 3, 5}, after deletion the list is 2 -> 3 -> NULL.

Assumptions

the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order.
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
    public ListNode deleteNodes(ListNode head, int[] indices) {
      // Write your solution here
      if (head == null) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p1 = dummy;
      ListNode p2 = head;
      int count = 0;
      for (int i = 0; i < indices.length; i++) {
        int index = indices[i] - count;
        while (p2 != null && index > 0) {
          p2 = p2.next;
          p1 = p1.next;
          index--;
          count++;
        }
        count++;
        if (p2 != null) {
          p1.next = p2.next;
          p2 = p1.next;
        }   
      }
      return dummy.next;
    }
  }
  