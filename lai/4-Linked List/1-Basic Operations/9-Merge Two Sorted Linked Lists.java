/**
 * 
 Merge two sorted lists into one large sorted list.

Examples

L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
L1 = null, L2 = null, merge L1 and L2 to null

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
    public ListNode merge(ListNode one, ListNode two) {
      // Write your solution here
      ListNode dummy = new ListNode(0);
      ListNode p = dummy;
      while (one != null && two != null) {
        if (one.value < two.value) {
          p.next = one;
          one = one.next;
        } else {
          p.next = two;
          two = two.next;
        }
        p = p.next;
      }
      if (one != null) {
        p.next = one;
      }
      if (two != null) {
        p.next = two;
      }
      return dummy.next;
    }
  }
  