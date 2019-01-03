/**
 * 
 Given a linked list, check whether it is a palindrome.

Examples:

Input:   1 -> 2 -> 3 -> 2 -> 1 -> null

output: true.

Input:   1 -> 2 -> 3 -> null  

output: false.

Requirements:

Space complexity must be O(1)
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
    public boolean isPalindrome(ListNode head) {
      // Write your solution here
      ListNode slow = head, fast = head, prev = null;
          
          while(fast != null && fast.next != null) // Find Mid Node and reverse first half
          {
              fast = fast.next.next;
              
              ListNode nextNode = slow.next;
              slow.next = prev;
              prev = slow;
              slow = nextNode;
          }
          
          ListNode left = prev; // prev is a new head of first half reversed list
          ListNode right = fast == null ? slow : slow.next; // If List size is Even / Odd
          
          while(right != null)  // Size of both lists is same
          {
              if(left.value != right.value)
                  return false;
              
              left = left.next;
              right = right.next;
          }
          
          return true;
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
    public boolean isPalindrome(ListNode head) {
      // Write your solution here
      if (head == null) {
        return true;
      }
  
      ListNode reversed = reverse(head);
      while (head != null) {
        if (head.value != reversed.value) {
          return false;
        }
        head = head.next;
        reversed = reversed.next;
      }
      return true;
    }
    
    private ListNode reverse(ListNode head) {
      ListNode pre = null;
      
      while (head != null) {
        ListNode cur = new ListNode(head.value);
        cur.next = pre;
        pre = cur;
        head = head.next;
      }
      return pre;
    }
  }
  