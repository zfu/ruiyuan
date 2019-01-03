/**
 * 
 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Input:  1->2->3->3->4->4->5

Output: 1->2->5    

Input:  1->1->1

Output: NULL

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
      if (head == null) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p = dummy;
      ListNode cur = head;
      while(cur != null) {
        while (cur.next != null && cur.value == cur.next.value) {
          cur = cur.next;
        }
        if (p.next == cur) {
          p = p.next;
        } else {
          p.next = cur.next;
        }
        cur = cur.next;
      }
      return dummy.next;
    }
  }
  

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
      if (head == null) {
        return head;
      }
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode p = dummy;
      while (p.next != null && p.next.next != null) {
        if (p.next.value != p.next.next.value) {
          p = p.next;
        } else {
          ListNode next = p.next.next;
          if (next.next != null) {
            while (p.next.value == next.next.value) {
              next = next.next;
            }
          }
          p.next = next.next;
        }
      }
      return dummy.next;
    }
  }
  