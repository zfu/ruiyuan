/**
 * 
 Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The insertion sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
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
    public ListNode insertionSort(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      ListNode cur = head;
      while (cur != null) {
        ListNode next = cur.next;
        ListNode p = dummy;
        while (p.next != null && p.next.value < cur.value) {
          p = p.next;
        }
        cur.next = p.next;
        p.next = cur;
        cur = next;
      }
      return dummy.next;
    }
  }
  