/**
 * 
 Given a linked list, reverse alternate nodes and append at the end.

Examples:

Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6

Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2

Input List:    1 -> 2 -> 3 -> 4 -> 5

Output List: 1 -> 3 -> 5 -> 4 -> 2
 */

 /**
  * 
  The idea is to maintain two linked lists, one list of all odd positioned nodes (1, 3, 5 in above example) and other list of all even positioned nodes (6, 4 and 2 in above example). Following are detailed steps.
1) Traverse the given linked list which is considered as odd list. Do following for every visited node.
……a) If the node is even node, remove it from odd list and add it to the front of even node list. Nodes are added at front to keep the reverse order.
2) Append the even node list at the end of odd node list
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
    public ListNode reverseAlternate(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null || head.next.next == null) {
        return head;
      }
      ListNode p = head;
      // even points to the beginning of the even list
      ListNode even = p.next;
      
      // remove the first even node
      p.next = p.next.next;
      // odd points to the next node in the odd list
      p = p.next;
      even.next = null;
      
      while (p != null && p.next != null) {
        // store the next node in the odd list
        ListNode temp = p.next.next;
        p.next.next = even;
        even = p.next;
        p.next = temp;
        
        if (temp != null) {
          p = temp;
        }
      }
      p.next = even;
      return head;
    }
  }
  