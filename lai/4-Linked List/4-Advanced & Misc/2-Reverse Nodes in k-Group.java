/**
 * 
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is. You may not alter the values in the nodes, only nodes itself may be changed.

Example

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
    public ListNode reverseKGroup(ListNode head, int k) {
          if (head == null) return null;
          ListNode dummy = new ListNode(0);
          dummy.next = head;
          ListNode pre = dummy;
          int i = 1;
          while (head != null) {
              if (i%k != 0) {
                  head = head.next;
              } else {
                  pre = reverse(pre, head.next);
                  head = pre.next;
              }
              i++;
          }
          return dummy.next;
      }
      
      private ListNode reverse(ListNode pre, ListNode after) {
          ListNode cur = pre.next, next = pre.next.next;
          while (next != after) {
              cur.next = next.next;
              next.next = pre.next;
              pre.next = next;
              next = cur.next;
          }
          return cur;
      }
  }
  