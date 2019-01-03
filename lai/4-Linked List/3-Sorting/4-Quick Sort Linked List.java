/**
 * 
 Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The quick sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
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
    public ListNode quickSort(ListNode head) {
      // Write your solution here
      if(head == null || head.next == null) {
              return head;
          }
          ListNode result = quickSortHelper(head, null);
          return result;
    }
    
    private ListNode quickSortHelper(ListNode head, ListNode tail) {
          if(head==null || head==tail) {
              return head;
          }
          ListNode pivot=partition(head, tail);
          quickSortHelper(head, pivot);
          quickSortHelper(pivot.next, tail);
          return head;
      }
    
    private ListNode partition(ListNode head, ListNode tail) {
          int pivot=head.value;
          ListNode i=head, j=head.next;
          while(j!=tail) {
              if(j.value<=pivot) {
                  i=i.next;
                  int temp=i.value;
                  i.value=j.value;
                  j.value=temp;
              }
              j=j.next;
          }
          head.value=i.value;
          i.value=pivot;
          return i;
      }
  }
  
      
      
      
      