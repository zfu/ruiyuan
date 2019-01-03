/**
 * 
 Check if a given linked list has a cycle. Return the node where the cycle starts. Return null if there is no cycle
 */

 /**
  * 
画一个简图：

a: length of non-loop 非环直线的长度
b: length of loop     环的长度
x: the point slow and fast meet in the loop 快慢指针在环内相遇的位置

--------oooo
        o  o
        ooxo
(a+x)*2 = a-1+kb+x --> a = kb-1-x, slow needs to go kb-x longer to finish the loop.
so if head wants to go to the start point of the loop, it would pass a, the same for slow. After head went a, slow went kb-x-1. However, a = kb-x-1, so head is slow.next at the loop, which is the start point of the loop.

slow在fast在环里的k处相遇，fast比slow走了两倍的路程，假设非环路和环路长度分别为a和b，且fast已经在环里多走了k圈，所以：
(a+x)*2 = a-1+kb+x --> a = kb-1-x
此时，slow还要走kb-x才能走完整个环。
而让head此时重新从起点出发，以和slow相同的速度，需要走非环路的直线长度a，才能到达环的起点。
那么，head到达环的时候，走了a = kb-1-x：是slow在环内走到环的起点的路程-1。
也就是说，slow.next = head，就是第二个while循环结束的条件。
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
    public ListNode cycleNode(ListNode head) {
      // write your solution here
      if (head == null || head.next == null) {
        return null;
      }
      ListNode fast = head.next, slow = head;
      while (fast != slow) {
        if (fast == null || fast.next == null) {
          return null;
        }
        slow = slow.next;
        fast = fast.next.next;
      }
      while (head != slow.next) {
        slow = slow.next;
        head = head.next;
      }
      return head;
    }
  }
  