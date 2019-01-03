/**
 * 
 Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
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
    public ListNode insert(ListNode head, int value) {
      // Write your solution here
      if(head == null) {
        return new ListNode(value);
      }
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p = dummy;
      while (p.next != null && p.next.value < value) {
        p = p.next;
      }
      
      ListNode temp = p.next;
      p.next = new ListNode(value);
      p = p.next;
      p.next = temp;
      return dummy.next;
    }
  }
  