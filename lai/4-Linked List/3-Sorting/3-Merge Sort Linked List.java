/**
 * 
 Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The merge sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6

 */

public class Solution {
  
    public ListNode sortList(ListNode head) {
      if (head == null || head.next == null)
        return head;
          
      // step 1. cut the list to two halves
      ListNode prev = null, slow = head, fast = head;
      
      while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
      }
      
      prev.next = null;
      
      // step 2. sort each half
      ListNode l1 = sortList(head);
      ListNode l2 = sortList(slow);
      
      // step 3. merge l1 and l2
      return merge(l1, l2);
    }
    
    ListNode merge(ListNode l1, ListNode l2) {
      ListNode l = new ListNode(0), p = l;
      
      while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
          p.next = l1;
          l1 = l1.next;
        } else {
          p.next = l2;
          l2 = l2.next;
        }
        p = p.next;
      }
      
      if (l1 != null)
        p.next = l1;
      
      if (l2 != null)
        p.next = l2;
      
      return l.next;
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
    public ListNode mergeSort(ListNode head) {
      // Write your solution here
      if (head == null || head.next == null) {
              return head;
          }
          ListNode slow = head;
          ListNode fast = head.next;
          while (fast != null && fast.next != null) {
              slow = slow.next;
              fast = fast.next.next;
          }
          ListNode middle = slow;
          ListNode next = middle.next;
          middle.next = null;
          return mergeList(mergeSort(head), mergeSort(next));
    }
    
    private ListNode mergeList(ListNode l1, ListNode l2) {
          if (l1 == null || l2 == null) {
              return l1 == null ? l2 : l1;
          }
          ListNode t = l1, prev = null, res;
          res = l1 = l1.value > l2.value ? l2 : l1;
          l2 = l1 == l2 ? t : l2;
          while (l1 != null && l2 != null) {
              if (l1.value <= l2.value) {
                  prev = l1;
                  l1 = l1.next;
              } else {
                  prev.next = l2;
                  prev = l2;
                  l2 = l1;
                  l1 = prev.next;
              }
          }
          prev.next = l2;
          return res;
      }
  }
  