/**
 * 
 Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null

Examples

L = null, is reordered to null
L = 1 -> null, is reordered to 1 -> null
L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
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
    public ListNode reorder(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null) {
        return head;
      }
      ListNode fast = head, slow = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      ListNode l2 = reverse(slow.next);
      slow.next = null;
      ListNode l1 = head;
      merge(l1, l2);
      return head;
    }
    
    private ListNode reverse(ListNode head) {
      ListNode curr = head, next = null, prev = null;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      return prev;
    }
    
    private void merge(ListNode l1, ListNode l2) {
       while (l1 != null && l2 != null) {
         ListNode temp1 = l1.next;
         ListNode temp2 = l2.next;
         l1.next = l2;
         l2.next = temp1;
         
         l1 = temp1;
         l2 = temp2;
       }
      
    }
  }
  