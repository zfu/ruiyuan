/**
 * 
 Given a sorted linked list, delete all duplicates such that each element appear only once.

Input: 1->1->2

Output: 1->2
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
    public ListNode removeDup(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null) {
        return head;
      }
      ListNode cur = head;
      while (cur != null && cur.next != null) {
        if (cur.value == cur.next.value) {
          cur.next = cur.next.next;
        } else {
          cur = cur.next;
        }
      }
      return head;
    }
  }
  