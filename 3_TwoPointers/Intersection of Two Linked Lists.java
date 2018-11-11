/**
 * 380. Intersection of Two Linked Lists 
 * 
 * Write a program to find the node at
 * which the intersection of two singly linked lists begins.
 * 
 * Example The following two linked lists:
 * 
 * A: a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1.
 * 
 * Challenge Your code should preferably run in O(n) time and use only O(1)
 * memory.
 * 
 * Notice If the two linked lists have no intersection at all, return null. The
 * linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 */

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */

public class Solution {
    /*
     * @param headA: the first list
     * 
     * @param headB: the second list
     * 
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // write your code here
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB;
            p2 = p2 != null ? p2.next : headA;
        }

        return p1;
    }
}

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = getListLength(headA);
        int lenB = getListLength(headB);

        ListNode pA = headA;
        ListNode pB = headB;

        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                pA = pA.next;
            }
        } else if (lenA < lenB) {
            for (int i = 0; i < lenB - lenA; i++) {
                pB = pB.next;
            }
        }

        while (pA != null && pB != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }

        return null;
    }

    private int getListLength(ListNode head) {
        int len = 0;
        ListNode p = head;

        while (p != null) {
            len++;
            p = p.next;
        }

        return len;
    }
}