/**
 * 
 Given a list, rotate the list to the right by k places, where k is non-negative.

Input: 1->2->3->4->5->NULL, k = 2

Output: 4->5->1->2->3->NULL

Input: 1->2->3->4->5->NULL, k = 12

Output: 4->5->1->2->3->NULL
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
    public ListNode rotateKplace(ListNode head, int n) {
      // Write your solution here
      if (head == null || head.next == null || n == 0) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p1 = dummy, p2 = dummy;
      int len = 0;
      while (p1.next != null) {
        p1 = p1.next;
        len++;
      }
      n = len - n % len;
      while (n > 0) {
        p2 = p2.next;
        n--;
      }
      p1.next = dummy.next;
      dummy.next = p2.next;
      p2.next = null;
      
      return dummy.next;
    }
  }
  