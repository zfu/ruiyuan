/**
 * 
 Merge K sorted lists into one big sorted list in ascending order.

Assumptions

ListOfLists is not null, and none of the lists is null.
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
    public ListNode merge(List<ListNode> listOfLists) {
      // Write your solution here/.
      if (listOfLists == null || listOfLists.size() == 0) {
        return null;
      }
      ListNode dummy = new ListNode(0);
      ListNode curr = dummy;
      PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(1, new Comparator<ListNode>(){
        @Override
        public int compare(ListNode n1, ListNode n2) {
          return n1.value - n2.value;
        }
      });
      
      for (int i = 0; i < listOfLists.size(); i++) {
        if (listOfLists.get(i) != null) {
          pq.offer(listOfLists.get(i));
        }
      }
      while (!pq.isEmpty()) {
        curr.next = pq.poll();
        curr = curr.next;
        if (curr.next != null) {
          pq.offer(curr.next);
        }
      }
      return dummy.next;
    }
  }

  
  public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) return null;
        return sort(lists, 0, lists.size()-1);
    }
    public ListNode sort(List<ListNode> lists, int start, int end) {
        if (start < end) {
            int mid = (start+end)/2;
            return merge(sort(lists, start, mid), sort(lists, mid+1, end));
        }
        return lists.get(start);
    }
    public ListNode merge(ListNode n1, ListNode n2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                cur.next = n1;
                n1 = n1.next;
            }
            else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        if (n1 != null) cur.next = n1;
        else cur.next = n2;
        return head.next;
    }
}


public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> n1.val-n2.val);
        for (ListNode n: lists) {
            if (n != null) heap.offer(n);
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;
            if (cur.next != null) heap.offer(cur.next);
        }
        return head.next;
    }
}