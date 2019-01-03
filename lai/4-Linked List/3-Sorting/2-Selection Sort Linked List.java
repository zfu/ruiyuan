/**
 * 
 Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The selectoin sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to 2 -> 3 -> 4 -> 5 -> 6
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
    public ListNode selectionSort(ListNode head) {
      // Write your solution here
      for(ListNode node1 = head; node1 != null; node1 = node1.next) {
              ListNode min = node1;
              for(ListNode node2 = node1; node2 != null; node2 = node2.next) {
                  if(min.value > node2.value) {
                      min=node2;
                  }
              }
              swap(node1, min);
          }
          return head;
    }
    
        private void swap(ListNode a, ListNode b) {
          int temp=a.value;
          a.value=b.value;
          b.value=temp;
      }
  }
  
  
      
  