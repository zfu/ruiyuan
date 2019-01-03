/**
 * 
 Check if a given linked list has a cycle. Return true if it does, otherwise return false.
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
    public boolean hasCycle(ListNode head) {
      // write your solution here
      if (head == null || head.next == null) {
        return false;
      }
      ListNode slow = head;
      ListNode fast = head.next;
      while (slow != fast) {
        if (fast == null || fast.next == null) {
          return false;
        }
        slow = slow.next;
        fast = fast.next.next;
      }
      return true;
    }
  }
  