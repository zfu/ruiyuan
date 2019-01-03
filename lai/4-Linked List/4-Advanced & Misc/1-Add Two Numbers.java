/**
 * 
 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.  

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
          ListNode dummy = new ListNode(0);
          ListNode curr = dummy;
          
          int carry = 0;
          while (l1 != null || l2 != null || carry > 0) {
              int a = l1 != null ? l1.value : 0;
              int b = l2 != null ? l2.value : 0;
              int sum = a + b + carry;
              
              carry = sum / 10;
              curr.next = new ListNode(sum % 10);
              l1 = l1 != null ? l1.next : null;
              l2 = l2 != null ? l2.next : null;
              curr = curr.next;
          }
          
          return dummy.next;
      }
  }
  