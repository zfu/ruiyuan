/**
 * 
 Delete the node at the given index for the given linked list.

Examples

[1, 2, 3], delete at 1 --> [1, 3]

[1, 2, 3], delete at 4 --> [1, 2, 3]

[1, 2, 3], delete at 0 --> [2, 3]


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
    public ListNode deleteNode(ListNode head, int index) {
      // Write your solution here
      if (head == null) {
        return head;
      }
      
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p = dummy;
      index--;
      while (p != null && index >= 0) {
        p = p.next;
        index--;
      }
      
      if (p != null && p.next != null) {
        p.next = p.next.next;
      }   
      return dummy.next;
    }
  }
  